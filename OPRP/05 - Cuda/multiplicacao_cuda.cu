#include <cuda.h>
#include <stdio.h>
#include <math.h>
#include "toolsv3.h"

#define T 1024 // numero max de threads por bloco

__global__ void matMult (int *da, int *db, int *dc, int *C_dev, int *Cb_dev, int *La_dev) {
  int i = (blockIdx.x * blockDim.x) + threadIdx.x;
  int j = (blockIdx.y * blockDim.y) + threadIdx.y;

  if(i<*La_dev && j<*Cb_dev){
	  int soma=0;
      for(int ii=0; ii< *C_dev ; ii++){
        soma += da[i*(*C_dev)+ii] * db[ii*(*Cb_dev)+j];
      }
    dc[i*(*Cb_dev)+j] = soma;
  }
}

__global__ void printIndex (void) {
   int i = blockIdx.x * blockDim.x + threadIdx.x;
   int j = blockIdx.y * blockDim.y + threadIdx.y;
   printf ("[%d][%d]\t(x)\t%d\t%d\t%d\t(y)\t%d\t%d\t%d\n",i,j, threadIdx.x, blockIdx.x, blockDim.x,threadIdx.y, blockIdx.y, blockDim.y);
}

__global__ void dirtyMem (int *da) {
   int i = blockIdx.x * blockDim.x + threadIdx.x;

   da[i] = 0;
}

__host__ void initvet(int *host_a, mymatriz mat_a) {
  int linha_a =mat_a.lin, coluna_a=mat_a.col;
  for (int i=0; i < linha_a; i++) {
    for (int j=0; j < coluna_a; j++) {
     host_a[i*coluna_a+j] = mat_a.matriz[i][j];
    }
  }
}

__host__ void printMat (int *mat, int lin, int col){
	for (int j=0; j < lin && j<15; j++)
	printf("\t(%d)", j);
	printf("\n\n");
	for (int i=0; i < lin && i<15; i++) {
		printf("(%d)", i);
		for (int j=0; j < col && j<15; j++){
			printf("\t%d", mat[i*col+j]);
		}
		printf("\n\n\n");
	}
}

__host__ mymatriz *mmultiplicar (mymatriz *mat_a, mymatriz *mat_b) {
	mymatriz *mat_c = NULL;
	if (mat_a->col != mat_b->lin){
		printf ("Erro: Matrizes são incompatíveis!\n");
		exit(1);
	}
	mat_c = (mymatriz *) malloc (sizeof(mymatriz));
	mat_c->lin = mat_a->lin;
	mat_c->col = mat_b->col;
	if (malocar(mat_c)) {	printf ("ERROR: Out of memory\n"); }
		//a(linhas) x b(colunas)
		printf("Multiplicando com ijk\n" );
		for (int i=0; i <mat_a->lin; i++){
			for (int j=0; j <mat_b->col; j++){
				mat_c->matriz[i][j]=0;
				for (int k=0; k < mat_b->lin; k++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
    return mat_c;
}

__host__ int mcomparar (mymatriz *mat_a, int *vet_c, int col){
	for (int j =0; j < mat_a->col; j++)
	for (int i=0; i < mat_a->lin; i++) {
		for (int j=0; j < mat_a->col; j++){
			if (mat_a->matriz[i][j] != vet_c[i*col+j]) {
				printf("O elemento [%d,%d] é diferente nas matrizes analisadas!", i,j);
				return 1;
			}
		}
	}
	printf("\tVERIFICADO: Matrizes são idênticas\n");
	return 0;
}

__host__ int mimprimir (mymatriz *matriz){
	int linha, coluna;
	linha = matriz->lin;
	coluna = matriz->col;
	if (linha > 15) {
		linha = 15;
	}
	if (coluna > 15) {
		coluna = 15;
	}
	for (int j =0; j < coluna; j++)
	printf("\t(%d)", j);
	printf("\n");
	for (int i=0; i < linha; i++) {
		printf("(%d)", i);
		for (int j=0; j < coluna; j++){
			printf("\t%d", matriz->matriz[i][j]);
		}
		printf("\n");
	}
	printf("\n \
	matriz->lin-1, matriz->col-1, matriz->matriz[matriz->lin-1][matriz->col-1]);
	return 0;
}

int main(int argc, char const *argv[]) {
  mymatriz mat_a, mat_b;
  FILE *fmat;
  int nr_line;
  int *vet_line = NULL;
  int L, C, Ca, Lb;

  if (argc != 3){
		printf ("ERRO: Numero de parametros %s <matriz_a> <matriz_b>\n", argv[0]);
		exit (1);
	}

  fmat = fopen(argv[1],"r");

  if (fmat == NULL) {
      printf("Error: Na abertura dos arquivos.");
      exit(1);
  }

  extrai_parametros_matriz(fmat, &L, &Ca, &vet_line, &nr_line);
  mat_a.matriz = NULL;
  mat_a.lin = L;
  mat_a.col = Ca;
  if (malocar(&mat_a)) {
    printf ("ERROR: Out of memory\n");
  }
  filein_matriz (mat_a.matriz, L, Ca, fmat, vet_line, nr_line);
  free (vet_line);
  fclose(fmat);

  fmat = fopen(argv[2],"r");
  if (fmat == NULL) {
    printf("Error: Na abertura dos arquivos.");
    exit(1);
  }

  extrai_parametros_matriz(fmat, &Lb, &C, &vet_line, &nr_line);
  mat_b.matriz = NULL;
  mat_b.lin = Lb;
  mat_b.col = C;
  if (malocar(&mat_b)) {
      printf ("ERROR: Ficamos sem memória galera ;-;\n");
  }
  filein_matriz (mat_b.matriz, Lb, C, fmat, vet_line, nr_line);

  free (vet_line);
  fclose(fmat);

  if(Ca != Lb){
    printf("Error: Matrizes são incompativeis\n");
    exit(1);
  }

  printf("Multiplicacao sequencial\n");
  double tempo_s=wtime();
  mymatriz *mult_sequencial = mmultiplicar(&mat_a, &mat_b);
  tempo_s = wtime()-tempo_s;

  int *a, *b, *c;
  int *devA, *devB, *devC;
  int tamanhoA, tamanhoB, tamanhoC;

  tamanhoA = L * Ca * sizeof(int);
  tamanhoB = Lb * C * sizeof(int);
  tamanhoC = L * C * sizeof(int);

  cudaError_t error_c;
  error_c = cudaMallocHost((void **) &a, tamanhoA);

  if(error_c != cudaSuccess){ printf("GPUassert: %s\n", cudaGetErrorString(error_c)); }
  error_c=cudaMallocHost((void **) &b, tamanhoB);
  if(error_c != cudaSuccess){ printf("GPUassert: %s\n", cudaGetErrorString(error_c));}
  error_c=cudaMallocHost((void **) &c, tamanhoC);
  if(error_c != cudaSuccess){ printf("GPUassert: %s\n", cudaGetErrorString(error_c));}

  initvet(a, mat_a);
  initvet(b, mat_b);

  printf ("\t ### Valores Lidos de arquivo na CPU ###\n");
  printf ("\t ### Matriz (a) ### \n");
  printMat(a, L, Ca);
  printf ("\t ### Matriz (b) ### \n");
  printMat(b,Lb, C);

  cudaMalloc ((void **) &devA, tamanhoA);
  cudaMalloc ((void **) &devB, tamanhoB);
  cudaMalloc ((void **) &devC, tamanhoC);

  dim3 dimBlock (1, 1);
  dim3 dimThreads(L, Ca);

  if(L*Ca > T){
	dimThreads.x=(int) ceil(sqrt(T));
    dimThreads.y=(int) ceil(sqrt(T));
    dimBlock.x= (int) ceil(double(L)/sqrt(T));//sqrt(1024)
    dimBlock.y= (int) ceil(double(Ca)/sqrt(T));//sqrt(1024) pois 32*32=1024 e precisamos L*C < 1024
  }

  dirtyMem<<<dimBlock, dimThreads>>>(devA);

	dimThreads.x=Lb;
  dimThreads.y=C;
  dimBlock.x= 1;
  dimBlock.y= 1;

  if(Lb*C > T){
	  dimThreads.x=(int) ceil(sqrt(T));
    dimThreads.y=(int) ceil(sqrt(T));
    dimBlock.x= (int) ceil(double(Lb)/sqrt(T));//sqrt(1024)
    dimBlock.y= (int) ceil(double(C)/sqrt(T));//sqrt(1024) pois 32*32=1024 e precisamos L*C < 1024
  }

dirtyMem<<<dimBlock, dimThreads>>>(devB);

dimThreads.x=L;
dimThreads.y=C;
dimBlock.x= 1;
dimBlock.y= 1;

if(L*C > T){
	dimThreads.x=(int) ceil(sqrt(T));
  dimThreads.y=(int) ceil(sqrt(T));
  dimBlock.x= (int) ceil(double(L)/sqrt(T));
  dimBlock.y= (int) ceil(double(C)/sqrt(T));
}
dirtyMem<<<dimBlock, dimThreads>>>(devC);

cudaMemcpy (devA, a, tamanhoA, cudaMemcpyHostToDevice);
cudaMemcpy (devB, b, tamanhoB, cudaMemcpyHostToDevice);

dimThreads.x=L;
dimThreads.y=C;
dimBlock.x= 1;
dimBlock.y= 1;

  if(L*C > T){
	dimThreads.x=(int) ceil(sqrt(T));
	dimThreads.y=(int) ceil(sqrt(T));
	dimBlock.x= (int) ceil(double(L)/sqrt(T));
	dimBlock.y= (int) ceil(double(C)/sqrt(T));
  }

  int *C_dev, *Cb_dev, *La_dev;//c_dev = Ca (matrizA) e Cb_dev=C (matrizb), La_dev = L (matrixA)
  cudaMalloc((void **) &C_dev, sizeof(int));
  cudaMalloc((void **) &Cb_dev, sizeof(int));
  cudaMalloc((void **) &La_dev, sizeof(int));
  cudaMemcpy (C_dev, &Ca, sizeof(int), cudaMemcpyHostToDevice);
  cudaMemcpy(Cb_dev, &C, sizeof(int), cudaMemcpyHostToDevice);
  cudaMemcpy(La_dev, &L, sizeof(int), cudaMemcpyHostToDevice);

  printf("Mult CUDA\n");
  double tempo_c = wtime();
  matMult<<< dimBlock, dimThreads>>>(devA, devB, devC, C_dev, Cb_dev, La_dev);

  cudaDeviceSynchronize();

  cudaMemcpy (c, devC, tamanhoC, cudaMemcpyDeviceToHost);
  tempo_c = wtime()- tempo_c;

  printf ("\t ### [CUDA] Matriz (c) ### \n");
  printMat(c, L, C);
  printf("\n\t ### [sequencial] Matriz ###\n");
  mimprimir(mult_sequencial);

  printf("Comparando as matrizes:\n");
  mcomparar(mult_sequencial, c, C);
  printf("\nBlocos(%d,%d) threads(%d,%d)\n", dimBlock.x, dimBlock.y, dimThreads.x, dimThreads.y);
  printf("Tempo de execucao sequencial: %.3f\n", tempo_s);
  printf("Tempo de execucao CUDA: %.3f\n", tempo_c);
  printf("SpeedUp sequencial/GPU: %.3f\n", tempo_s/tempo_c);

  cudaFree(devA);
  cudaFree(devB);
  cudaFree(devC);
  cudaFreeHost(a);
  cudaFreeHost(b);
  cudaFreeHost(c);

  return 0;
}
