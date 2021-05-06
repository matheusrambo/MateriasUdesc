#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "toolsv3.h"
#include <omp.h>
#include "mpi.h"

#define MASTER 0
#define MSG_TAG 0

int main(int argc, char **argv) {

  mymatriz matrizA, matrizB;
  FILE *fmat;
  int numeroLinhas;
  int *linhas_vetor = NULL;
  int N,M,La,Lb,n,task_id,num_task;
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &task_id);
  MPI_Comm_size(MPI_COMM_WORLD, &num_task);

  if (argc != 4){
		printf ("Num de parametros %s <matriz_a> <matriz_b> <qtd_divisoes>\n", argv[0]);
		exit (1);
	}

  fmat = fopen(argv[1],"r");
  if (fmat == NULL) {
    printf("Na abertura dos arquivos.");
    exit(1);
  }
  extrai_parametros_matriz(fmat, &N, &La, &linhas_vetor, &numeroLinhas);
  matrizA.matriz = NULL;
  matrizA.lin = N;
  matrizA.col = La;
  n = N;

  if (malocar_mat(&matrizA)) {
    printf ("Acabou a memória jovem gafanhoto, lamento muito!\n");
  }

  filein_matriz (matrizA.matriz, N, La, fmat, linhas_vetor, numeroLinhas);
  free (linhas_vetor);
  fclose(fmat);

  fmat = fopen(argv[2],"r");

  if (fmat == NULL) {
    printf("Erro na abertura dos arquivos, lamento muito jovem gafanhoto!");
    exit(1);
  }
  extrai_parametros_matriz(fmat, &Lb, &M, &linhas_vetor, &numeroLinhas);

  matrizB.matriz = NULL;
  matrizB.lin = Lb;
  matrizB.col = M;

  if (malocar_mat(&matrizB)) {
    printf ("Putz, sem memória novamente amigos!\n");
  }

  filein_matriz (matrizB.matriz, Lb, M, fmat, linhas_vetor, numeroLinhas);
  free (linhas_vetor);
  fclose(fmat);
  mymatriz *matA_mestre = NULL;
  mymatriz *matC_mestre = NULL;
  mymatriz *matA_escravo  = NULL;
  mymatriz *matC_escravo = NULL;
  mymatriz *B = matrix_create(n, n);

  int linha = n / num_task;
  int resto = n % num_task;

  matA_mestre = matrix_create(n, n);
  matC_mestre = matrix_create(n, n);
  matA_escravo = matrix_create(linha, n);
  matC_escravo = matrix_create(linha, n);

  int divisor =atoi(argv[3]), direcao = 1;

  if(direcao ==0)
  printf("Divisões %d; A: vert e B: horiz\n",divisor);
  else
  printf("Divisões %d; A: horiz e B: vert\n",divisor);

  printf("Matriz - A!\n");
  matrizPrint(matrizA.matriz, matrizA.lin, matrizA.col);

  printf("Matriz - B!\n");
  matrizPrint(matrizB.matriz, matrizB.lin, matrizB.col);

  int ***matriz_resultante_sequencial_blocos= malloc(sizeof(int **) *2), ***matriz_resultante_threads_blocos= malloc(sizeof(int **) *2);
  mymatriz **resultante_padrao = malloc(sizeof(mymatriz*)*2);

  double tempoMedioSequencial=0, tempoMedioSequencialBloco=0, tempoMedioMultiThreadBloco=0;

  printf("mult seq\n");
  for(int i=0;i<2;i++){
    double time_i=wtime();
    resultante_padrao[i] = mat_multiplicar (&matrizA, &matrizB, 0);
    tempoMedioSequencial+=wtime()-time_i;
    printf("Tempo da funcao = %f\n\n" , wtime()-time_i);
    matrizPrint(resultante_padrao[i]->matriz,resultante_padrao[i]->lin, resultante_padrao[i]->col);
    escrever_matriz_arquivo(resultante_padrao[i]->matriz, resultante_padrao[i]->lin, resultante_padrao[i]->col,i, 0);

  }
  printf("mult seq em bloco\n");

  for( int i=0;i<2;i++){
      double time_i=wtime();
      mult_mat_submatrizes_seq (&matrizA, &matrizB, direcao, divisor, &matriz_resultante_sequencial_blocos[i]);
      printf("Tempo da funcao = %f\n" , wtime()-time_i);
      tempoMedioSequencialBloco += wtime()-time_i;
      escrever_matriz_arquivo(matriz_resultante_sequencial_blocos[i], matrizA.lin, matrizB.col, i, 1);
  }
  printf("mult thread bloco\n");

  for(int i=0;i<2; i++){
      double time_i=wtime();
      mult_mat_submatrizes (&matrizA, &matrizB, direcao, divisor, &matriz_resultante_threads_blocos[i]);
      tempoMedioMultiThreadBloco+=wtime()-time_i;
      printf("Tempo  func = %f\n" , wtime()-time_i);
      escrever_matriz_arquivo(matriz_resultante_threads_blocos[i], matrizA.lin, matrizB.col, i, 2);
  }
  printf("mult open MP\n");
  double time_i=wtime();
  mymatriz mat_openMP = mat_multiplica_OpenMP(&matrizA, &matrizB);

  double time_f=wtime();
  double tempoOPENMP = time_f - time_i;

  printf("Tempo da funcao = %f\n" , tempoOPENMP);
  matrizPrint(mat_openMP.matriz, mat_openMP.lin, mat_openMP.col);
  escrever_matriz_arquivo(mat_openMP.matriz, mat_openMP.lin, mat_openMP.col, 1, 3);

  printf("mult open MPI\n");
  double ini_mpi=0, fim_mpi=0;
    if (task_id == MASTER) {
      /*???*/
        valores(&matrizA, matA_mestre);
        valores(&matrizB, B);
        ini_mpi = MPI_Wtime();
    }

    MPI_Bcast(&B->matriz[0][0], B->lin * B->col, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatter(&matA_mestre->matriz[0][0], linha*n, MPI_INT, &matA_escravo->matriz[0][0], linha*n, MPI_INT, MASTER, MPI_COMM_WORLD);

    if (task_id == MASTER) {
      if (resto != 0) {
        multiplicacao_normal(matA_mestre, B, matC_mestre, n-resto);
      }
    }
    multiplicacao_normal(matA_escravo, B, matC_escravo, 0);
    MPI_Gather(&matC_escravo->matriz[0][0], linha*n, MPI_INT, &matC_mestre->matriz[0][0], linha*n, MPI_INT, MASTER, MPI_COMM_WORLD);
    if (task_id == MASTER) {
      fim_mpi = MPI_Wtime();
      /*???*/
    }
    MPI_Finalize();

    double tempoMPI = fim_mpi-ini_mpi;
    printf("Tempo da funcao = %f\n" , tempoMPI);
    matrizPrint(matC_mestre->matriz, matC_mestre->lin, matC_mestre->col);
    escrever_matriz_arquivo(matC_mestre->matriz, matC_mestre->lin, matC_mestre->col, 1, 4);

    printf("Tempo Seq : %f\n", tempoMedioSequencial/2);
    printf("Tempo Seq com Bloco : %f\n", tempoMedioSequencialBloco/2);

    printf("Tempo Thread em Bloco : %f\n", tempoMedioMultiThreadBloco/2);

    printf("Tempo mat open mp : %f\n", tempoOPENMP);
    printf("Tempo mat mpi : %f\n", tempoMPI);


    //-------------------------------------------------
    free(resultante_padrao);                        
    //-------------------------------------------------
    free(matriz_resultante_sequencial_blocos);      
    free(matriz_resultante_threads_blocos);         
    //-------------------------------------------------
    free(B);                                        
    //-------------------------------------------------
    free(matA_escravo);
    free(matA_mestre);
    //-------------------------------------------------
    free(matC_escravo);
    free(matC_mestre);
    //-------------------------------------------------
    return 0;
}
