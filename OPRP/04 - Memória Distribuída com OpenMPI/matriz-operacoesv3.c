#include "matriz-operacoesv3.h"

int malocar_mat (mymatriz *matriz) {
	int **newMatriz = NULL;

	newMatriz = (int **) malloc(matriz->lin*sizeof(int *));
	if (!newMatriz) {
		printf("ERROR: Out of memory\n");
		return 1;
	}
	for (int i =0; i < matriz->lin; i++) {
		newMatriz[i] = (int *) malloc(sizeof(int)*matriz->col);
		if (!newMatriz) {
			printf("ERROR: Out of memory\n");
			return 1;
		}
	}

	matriz->matriz = newMatriz;
	return 0;
}

int mat_gerar(mymatriz *matriz, int valor){
	srand( (unsigned)time(NULL) );

	for (int i=0; i < matriz->lin; i++)
	for (int j=0; j < matriz->col; j++)
	if (valor == -9999)
	matriz->matriz[i][j] = rand() % 10;
	else
	matriz->matriz[i][j] = valor;

	return 0;
}

int mat_imprimir (mymatriz *matriz){
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
	// %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%%\n \
	// 	WARNING: Impressão truncada em 15x15! \n \
	// 	WARNING: Último elemento matriz[%d][%d] = %d \n \
	// %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%% %%%%%%%%%%%%\n", \
	matriz->lin-1, matriz->col-1, matriz->matriz[matriz->lin-1][matriz->col-1]);
	return 0;
}

int mat_liberar (mymatriz *matriz) {
	for (int i =0; i < matriz->lin; i++) {
		free(matriz->matriz[i]);
	}
	free(matriz->matriz);
	return 0;
}

mymatriz *matrix_create(int lin, int col)
{
 int i;
 int *matriz;
 mymatriz *m = (mymatriz *) malloc(sizeof(mymatriz));

 m->lin = lin;
 m->col = col;
 m->matriz = (int **) malloc(sizeof(int *) * lin);
 matriz = (int *) malloc(sizeof(int) * lin * col);
 for (i = 0; i < lin; i++) {
		m->matriz[i] = &matriz[col * i];
 }
 return m;
}

int mat_zerar (mymatriz *matriz){
	return mat_gerar(matriz,0);
}

int mat_comparar (mymatriz *mat_a, mymatriz *mat_b){
	for (int j =0; j < mat_a->col; j++)
	for (int i=0; i < mat_a->lin; i++) {
		for (int j=0; j < mat_a->col; j++){
			if (mat_a->matriz[i][j] != mat_b->matriz[i][j]) {
				printf("O elemento [%d,%d] é diferente nas matrizes analisadas!", i,j);
				return 1;
			}
		}
	}
	printf("\tVERIFICADO: Matrizes identicas\n");
	return 0;
}

mymatriz *mat_somar (mymatriz *mat_a, mymatriz *mat_b, int tipo) {
	mymatriz *mat_c = NULL;

	if ((mat_a->lin != mat_b-> lin) || (mat_a->col != mat_b->col)){
		printf ("Erro: Matrizes incompatíveis!\n");
		exit(1);
	}

	mat_c = (mymatriz *) malloc (sizeof(mymatriz));
	mat_c->lin = mat_a->lin;
	mat_c->col = mat_a->col;

	if (malocar_mat(mat_c)) {	printf ("ERROR: Out of memory\n"); }

	if (tipo == 1) {
		for (int i=0; i < mat_c->lin; i++)
		for (int j=0; j < mat_c->col; j++)
		mat_c->matriz[i][j] = mat_a->matriz[i][j]+mat_b->matriz[i][j];
	} else {
		for (int j=0; j < mat_c->col; j++)
		for (int i=0; i < mat_c->lin; i++)
		mat_c->matriz[i][j] = mat_a->matriz[i][j]+mat_b->matriz[i][j];
	}
	return mat_c;
}

void mat_explode(mymatriz *m)
{
   free((void *) m->matriz[0]);
   free((void *) m->matriz);
   free((void *) m);
}

mymatriz *mat_multiplicar (mymatriz *mat_a, mymatriz *mat_b, int tipo) {
	mymatriz *mat_c = NULL;
	if (mat_a->col != mat_b->lin){
		printf ("Erro: Matrizes incompatíveis!\n");
		exit(1);
	}
	mat_c = (mymatriz *) malloc (sizeof(mymatriz));
	mat_c->lin = mat_a->lin;
	mat_c->col = mat_b->col;
	if (malocar_mat(mat_c)) {	printf ("ERROR: Out of memory\n"); }
	if (tipo == 0) {
		for (int i=0; i <mat_a->lin; i++){
			for (int j=0; j <mat_b->col; j++){
				mat_c->matriz[i][j]=0;
				for (int k=0; k < mat_b->lin; k++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
	}else if (tipo == 1) {
		for (int j=0; j <mat_b->col; j++){
				for (int i=0; i <mat_a->lin; i++){
				mat_c->matriz[i][j]=0;
				for (int k=0; k < mat_b->lin; k++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
	}else if (tipo == 2) {
		for(int i=0;i<mat_c->lin;i++){
			for(int j=0;j<mat_c->col;j++){
				mat_c->matriz[i][j]=0;
			}
		}
		for (int i=0; i <mat_a->lin; i++){
			for (int k=0; k < mat_b->lin; k++){
				for (int j=0; j <mat_b->col; j++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
	}else if (tipo == 3) {
		for(int i=0;i<mat_c->lin;i++){
			for(int j=0;j<mat_c->col;j++){
				mat_c->matriz[i][j]=0;
			}
		}
		for (int k=0; k < mat_b->lin; k++){
			for (int i=0; i <mat_a->lin; i++){
				for (int j=0; j <mat_b->col; j++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
	}else if (tipo == 4) {
		for(int i=0;i<mat_c->lin;i++){
			for(int j=0;j<mat_c->col;j++){
				mat_c->matriz[i][j]=0;
			}
		}
		for (int k=0; k < mat_b->lin; k++){
			for (int j=0; j <mat_b->col; j++){
				for (int i=0; i <mat_a->lin; i++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}
	}else if (tipo == 5) {
		for(int i=0;i<mat_c->lin;i++){
			for(int j=0;j<mat_c->col;j++){
				mat_c->matriz[i][j]=0;
			}
		}
		for (int j=0; j <mat_b->col; j++){
			for (int k=0; k < mat_b->lin; k++){
					for (int i=0; i <mat_a->lin; i++){
					mat_c->matriz[i][j] += mat_a->matriz[i][k]*mat_b->matriz[k][j];
				}
			}
		}

	}
	return mat_c;
}

int comparar_matrizes(int **matriz_a, int lin_a, int col_a, int **matriz_b, int lin_b, int col_b){
	if(lin_a != lin_b || col_a != col_b){
		printf("Matrizes diferentes !\n" );
		return -1;
	}
	for(int i=0; i<lin_a; i++){
		for(int j=0;j<col_a;j++){
			if(matriz_a[i][j] != matriz_b[i][j]){
				printf("Matrizes diferentes !\n" );
				return -1;
			}
		}
	}
	printf("Matrizes identicas !\n" );
	return 0;
}

void matrizPrint(int **matriz, int lin, int col){
  for(int i=0;i<lin;i++){
    for(int j=0;j<col;j++){
      printf("%d\t",matriz[i][j]);
			if(j>12){
				printf("...\n");
				break;
			}
			if(j==col-1)
				printf("\n");
    }
		if(i>12){
			printf("...\n");
			break;
		}
  }
  printf("\n");
}

void matrizPrint2(int **matriz, int lin, int col, int linReal, int colReal){
  printf("   ");
  for(int i=0;i<col;i++){
    printf("%2d(%d) :",i,i+colReal);
  }
  printf("\n");
  for(int i=0;i<col;i++){
    printf("%2s","-----");
  }
  printf("\n");
  for(int i=0;i<lin;i++){
    printf("%d(%d): ",i,i+linReal);
    for(int j=0;j<col;j++){
      printf("%2d |",matriz[i][j]);
    }
    printf("\n");
  }
  printf("\n");
}

int roundup(int a, int b){
	int ret = (double) a/b;
	if(a%b>0){
		ret++;
	}
	return ret;
}

matriz_bloco_t **particionar_matriz (int **matriz, int mat_lin, int mat_col, int orientacao, int divisor){
	if ( divisor > mat_lin*mat_col){
		printf("Erro: nao pode dividir a matriz em mais que %d partes (lin %d x col %d)\n", mat_lin*mat_col, mat_lin,mat_col );
		exit(1);
	}

	bloco_t *blocos = malloc(sizeof(bloco_t) * divisor);
	if (!blocos) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}
	if(orientacao == 0 ){
			for(int i=0, aux=0; i< divisor;i++){
				int intervaloColuna = (double) mat_col/ divisor;
				if(intervaloColuna==0){
					intervaloColuna=1;
				}
				(blocos+i)->col_inicio = aux*intervaloColuna;
				(blocos+i)->col_fim = (aux+1)*intervaloColuna-1;
				if(divisor<mat_col && i == divisor-1){
					(blocos+i)->col_fim = mat_col-1;
				}
				if((blocos+i)->col_inicio >= mat_col -1){
					(blocos+i)->col_inicio = mat_col-1;
				}
				if((blocos+i)->col_fim >= mat_col -1){
					(blocos+i)->col_fim = mat_col-1;
					aux=0;
				}else{
					aux++;
				}
			}
		int qtdBlocosColuna[mat_col];
		for(int i=0; i<mat_col;i++){
			qtdBlocosColuna[i]=0;
		}
		for(int i=0;i<divisor;i++){
			qtdBlocosColuna[(blocos+i)->col_inicio]++;
		}
		int max_div=0;
		for(int i=0;i<divisor;i++){
			if(qtdBlocosColuna[(blocos+i)->col_inicio]>max_div){
				max_div=qtdBlocosColuna[(blocos+i)->col_inicio];
			}
		}
		int intervaloLinha = (double) mat_lin/ max_div;
		if(intervaloLinha==0){
			intervaloLinha=1;
		}
		for(int i=0, aux=0;i<divisor;i++){
			(blocos+i)->lin_inicio=aux*intervaloLinha;
			(blocos+i)->lin_fim=(aux+1)*intervaloLinha-1;
			if((blocos+i)->lin_fim>mat_lin-1){
				(blocos+i)->lin_fim=mat_lin-1;
			}
			if((blocos+i)->lin_inicio>mat_lin-1){
				(blocos+i)->lin_inicio=mat_lin-1;
			}
			if(qtdBlocosColuna[(blocos+i)->col_inicio] - aux -1<=0){
				(blocos+i)->lin_fim = mat_lin-1;
			}
			if((blocos+i)->col_fim == mat_col-1){
				aux++;
			}
		}
	}else{
		for(int i=0, aux=0; i< divisor;i++){
			int intervaloLinha = (double) mat_lin/ divisor;
			if(intervaloLinha==0){
				intervaloLinha=1;
			}
			(blocos+i)->lin_inicio = aux*intervaloLinha;
			(blocos+i)->lin_fim = (aux+1)*intervaloLinha-1;
			if(aux+1 == divisor){
				(blocos+i)->lin_fim = mat_lin-1;
			}
			if((blocos+i)->lin_inicio >= mat_lin -1){
				(blocos+i)->lin_inicio = mat_lin-1;
			}
			if(divisor<mat_lin && i == divisor-1){
				(blocos+i)->lin_fim = mat_lin-1;
			}
			if((blocos+i)->lin_fim >= mat_lin -1){
				(blocos+i)->lin_fim = mat_lin-1;
				aux=0;
			}else{
				aux++;
			}
		}
	int qtdBlocosLinha[mat_lin];

	for(int i=0; i<mat_lin;i++){
		qtdBlocosLinha[i]=0;
	}

	for(int i=0;i<divisor;i++){
		qtdBlocosLinha[(blocos+i)->lin_inicio]++;
	}

	int max_div=0;

	for(int i=0;i<divisor;i++){
		if(qtdBlocosLinha[(blocos+i)->lin_inicio]>max_div){
			max_div=qtdBlocosLinha[(blocos+i)->lin_inicio];
		}
	}

	int intervaloColuna = (double) mat_col/ max_div;
	if(intervaloColuna==0){
		intervaloColuna=1;
	}

	for(int i=0, aux=0;i<divisor;i++){

		(blocos+i)->col_inicio=aux*intervaloColuna;
		(blocos+i)->col_fim=(aux+1)*intervaloColuna-1;

		if((blocos+i)->col_fim>mat_col-1){
			(blocos+i)->col_fim=mat_col-1;
		}

		if((blocos+i)->col_inicio>mat_col-1){
			(blocos+i)->col_inicio=mat_col-1;
		}

		if(qtdBlocosLinha[(blocos+i)->lin_inicio] - aux -1<=0){
			(blocos+i)->col_fim = mat_col-1;
		}

		if((blocos+i)->lin_fim == mat_lin-1){
			aux++;
		}


	}
}

	matriz_bloco_t **matrizBlocos = malloc(sizeof(matriz_bloco_t* ) * divisor);

	if (!matrizBlocos) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}

	for(int i=0;i<divisor;i++){
		int qtdLinhas =(blocos+i)->lin_fim - (blocos+i)->lin_inicio+1;
		int qtdColunas =(blocos+i)->col_fim - (blocos+i)->col_inicio+1;

		matrizBlocos[i] = malloc(sizeof(matriz_bloco_t ));
		if (!matrizBlocos[i]) {
			printf("ERROR: Out of memory\n");
			exit(1);
		}

		matrizBlocos[i]->matriz =  malloc(sizeof(int* )* qtdLinhas);
		if (!matrizBlocos[i]->matriz) {
			printf("ERROR: Out of memory\n");
			exit(1);
		}

		for(int j=0;j< qtdLinhas; j++ ){
			matrizBlocos[i]->matriz[j]= malloc(sizeof(int) * qtdColunas);

			for(int k=0;k<qtdColunas;k++){
			matrizBlocos[i]->matriz[j][k] = matriz[(blocos[i].lin_inicio+j)][(blocos[i].col_inicio+k)];

			}

		}
		matrizBlocos[i]->bloco=(blocos+i);

	}
	return matrizBlocos;

}

void somando_subbloco(int ***resultante, matriz_bloco_t *matrizResultados){

	for( int j=0; j<= matrizResultados->bloco->lin_fim-matrizResultados->bloco->lin_inicio;j++){

	 for( int k=0; k<= matrizResultados->bloco->col_fim- matrizResultados->bloco->col_inicio;k++){

		 resultante[0][j+matrizResultados->bloco->lin_inicio][k+matrizResultados->bloco->col_inicio] += matrizResultados->matriz[j][k];

	 }

 }

}

matriz_bloco_t *mallocarBloco(int Alin_inicio, int Alin_fim, int Bcol_inicio, int Bcol_fim){

	matriz_bloco_t *matrizResultados = malloc(sizeof(matriz_bloco_t));

	if (!matrizResultados) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}

	matrizResultados->bloco = malloc(sizeof(bloco_t));
	if (!matrizResultados->bloco) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}

	matrizResultados->bloco->lin_inicio=Alin_inicio;
	matrizResultados->bloco->lin_fim=Alin_fim;
	matrizResultados->bloco->col_inicio=Bcol_inicio;
	matrizResultados->bloco->col_fim=Bcol_fim;

	matrizResultados->matriz = malloc(sizeof(int *)*(Alin_fim-Alin_inicio+1));
	if (!matrizResultados->matriz) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}

	for(int k=0;k<=Alin_fim-Alin_inicio;k++){
			matrizResultados->matriz[k]=malloc(sizeof(int)*(Bcol_fim-Bcol_inicio+1));
	}

	return matrizResultados;
}


void *funcaoThread(void *arg){

	thread_arg *td_arg = (thread_arg*) arg;

	int divisor = td_arg->divisor;

	pthread_mutex_lock(&mutex);
	matriz_bloco_t *matrizBlocoA = td_arg->matrizBlocoA;
	matriz_bloco_t **matrizBlocoB = td_arg->matrizBlocoB;
	td_arg->matrizResultados = malloc(sizeof(matriz_bloco_t* )*(divisor));
	pthread_mutex_unlock(&mutex);

	int cont =0;

	  for(int j=0; j<divisor;j++){

	      int Aci=matrizBlocoA->bloco->col_inicio, Acf=matrizBlocoA->bloco->col_fim, Bli=matrizBlocoB[j]->bloco->lin_inicio, Blf=matrizBlocoB[j]->bloco->lin_fim;

	        if(Acf >= Bli && Aci <= Blf){

					pthread_mutex_lock(&mutex);
					td_arg->matrizResultados[cont] = mallocarBloco(matrizBlocoA->bloco->lin_inicio,matrizBlocoA->bloco->lin_fim,matrizBlocoB[j]->bloco->col_inicio, matrizBlocoB[j]->bloco->col_fim);
					pthread_mutex_unlock(&mutex);

	        mat_mult_sub (matrizBlocoA, matrizBlocoB[j], td_arg->matrizResultados[cont] );

	        cont++;
	      }

	  }
		td_arg->cont=cont;
		td_arg->matrizResultados = realloc(td_arg->matrizResultados, sizeof(matriz_bloco_t* )*(cont));

return NULL;
}

int mult_mat_submatrizes (mymatriz *mat_a, mymatriz *mat_b, int direcao, int divisor,int ***result){

	matriz_bloco_t **matrizBlocoA = particionar_matriz(mat_a->matriz, mat_a->lin, mat_a->col, direcao, divisor);
	if(direcao ==0)
	direcao=1;
	else
	direcao =0;

	matriz_bloco_t **matrizBlocoB = particionar_matriz(mat_b->matriz, mat_b->lin, mat_b->col, direcao, divisor);
	int **resultante = malloc(sizeof(int* )*mat_a->lin);
	if (!resultante) {
		printf("ERROR: Out of memory\n");
		exit(1);
	}
	for(int i=0;i<mat_a->lin;i++){

		resultante[i]= malloc(sizeof(int) *mat_b->col);
		for(int j=0; j<mat_b->col;j++)
			resultante[i][j]=0;
	}

pthread_t threads[divisor];

if (pthread_mutex_init(&mutex, NULL) != 0)
    {
        printf("\n mutex falhou na inicializacao\n");
        exit(-1);
    }

thread_arg *td_args = malloc(sizeof(thread_arg ) * divisor);

	for(int i=0; i<divisor;i++){

		td_args[i].matrizBlocoA = matrizBlocoA[i];
		td_args[i].matrizBlocoB = matrizBlocoB;
		td_args[i].divisor = divisor;

		pthread_create(&(threads[i]), NULL, &funcaoThread, &(td_args[i]));

	}

	for(int i=0; i<divisor;i++){

		pthread_join(threads[i], NULL);
	}

	pthread_mutex_destroy(&mutex);

	for(int i=0;i<divisor;i++){

		for(int j=0;j<td_args[i].cont;j++){
		somando_subbloco(&resultante, td_args[i].matrizResultados[j]);

}

	}

	matrizPrint(resultante, mat_a->lin,mat_b->col);
	*result = resultante;

return 0;

}



int mat_mult_sub (matriz_bloco_t *mat_suba, matriz_bloco_t *mat_subb, matriz_bloco_t *mat_subc){
		for (int i=0; i<= mat_suba->bloco->lin_fim-mat_suba->bloco->lin_inicio; i++){
				for(int j=0; j<=mat_subb->bloco->col_fim-mat_subb->bloco->col_inicio; j++){
						int aux=0;
			mat_subc->matriz[i][j]=0;

if(mat_subb->bloco->lin_inicio>=mat_suba->bloco->col_inicio){//ISSO AQUI TEM QUE TIRRARARRRRR :%gambiarra%
					for(int k=0;k<=mat_suba->bloco->col_fim-mat_suba->bloco->col_inicio;k++){

						if((k+mat_suba->bloco->col_inicio== mat_subb->bloco->lin_inicio+aux) && (k+mat_suba->bloco->col_inicio<= mat_subb->bloco->lin_fim) ){
							mat_subc->matriz[i][j]+= mat_suba->matriz[i][k] * mat_subb->matriz[aux][j];
							aux++;
						}
				}
}else{
						for(int k=0;k<=mat_subb->bloco->lin_fim-mat_subb->bloco->lin_inicio;k++){

							if((k+mat_subb->bloco->lin_inicio== mat_suba->bloco->col_inicio+aux) && (k+mat_subb->bloco->lin_inicio<= mat_suba->bloco->col_fim) ){
								mat_subc->matriz[i][j]+= mat_suba->matriz[i][aux] * mat_subb->matriz[k][j];
								aux++;
							}
}
					}

				}
			}

	return 0;
}

int mult_mat_submatrizes_seq (mymatriz *mat_a, mymatriz *mat_b, int direcao, int divisor,int ***result){

	matriz_bloco_t **matrizBlocoA = particionar_matriz(mat_a->matriz, mat_a->lin, mat_a->col, direcao, divisor);
	if(direcao ==0)
	direcao=1;
	else
	direcao =0;

	matriz_bloco_t **matrizBlocoB = particionar_matriz(mat_b->matriz, mat_b->lin, mat_b->col, direcao, divisor);

	matriz_bloco_t **matrizResultados = malloc(sizeof(matriz_bloco_t* )*(divisor*divisor));
	if (!matrizResultados) {
	  printf("ERROR: Out of memory\n");
	  exit(1);
	}

	int cont=0;
	for(int i=0; i<divisor;i++){

	  for(int j=0; j<divisor;j++){
	      int Aci=matrizBlocoA[i]->bloco->col_inicio, Acf=matrizBlocoA[i]->bloco->col_fim, Bli=matrizBlocoB[j]->bloco->lin_inicio, Blf=matrizBlocoB[j]->bloco->lin_fim;

	        if(Acf >= Bli && Aci <= Blf){
	        matrizResultados[cont] = malloc(sizeof(matriz_bloco_t));
	        if (!matrizResultados[cont]) {
	    			printf("ERROR: Out of memory\n");
	    			exit(1);
	    		}

	        matrizResultados[cont]->bloco = malloc(sizeof(bloco_t));
	        if (!matrizResultados[cont]->bloco) {
	    			printf("ERROR: Out of memory\n");
	    			exit(1);
	    		}

	        matrizResultados[cont]->bloco->lin_inicio=matrizBlocoA[i]->bloco->lin_inicio;
	        matrizResultados[cont]->bloco->lin_fim=matrizBlocoA[i]->bloco->lin_fim;
	        matrizResultados[cont]->bloco->col_inicio=matrizBlocoB[j]->bloco->col_inicio;
	        matrizResultados[cont]->bloco->col_fim=matrizBlocoB[j]->bloco->col_fim;

	        matrizResultados[cont]->matriz = malloc(sizeof(int *)*(matrizBlocoA[i]->bloco->lin_fim-matrizBlocoA[i]->bloco->lin_inicio+1));
	        if (!matrizResultados[cont]->matriz) {
	    			printf("ERROR: Out of memory\n");
	    			exit(1);
	    		}

					for(int k=0;k<=matrizResultados[cont]->bloco->lin_fim-matrizResultados[cont]->bloco->lin_inicio;k++){
	    				matrizResultados[cont]->matriz[k]=malloc(sizeof(int)*(matrizBlocoB[j]->bloco->col_fim-matrizBlocoB[j]->bloco->col_inicio+1));
	    		}
	        mat_mult_sub (matrizBlocoA[i], matrizBlocoB[j], matrizResultados[cont]);
	        cont++;
	      }
	  }
	}


	matrizResultados = realloc(matrizResultados, sizeof(matriz_bloco_t* )*(cont));
	int **resultante = malloc(sizeof(int* )*mat_a->lin);
	if (!resultante) {
	  printf("ERROR: Out of memory\n");
	  exit(1);
	}

	for(int i=0;i<mat_a->lin;i++){

	  resultante[i]= malloc(sizeof(int) *mat_b->col);
	  for(int j=0; j<mat_b->col;j++)
	    resultante[i][j]=0;
	}

	for(int i=0;i<cont;i++){

	    for( int j=0; j<= matrizResultados[i]->bloco->lin_fim-matrizResultados[i]->bloco->lin_inicio;j++){

	      for( int k=0; k<= matrizResultados[i]->bloco->col_fim- matrizResultados[i]->bloco->col_inicio;k++){

	        resultante[j+matrizResultados[i]->bloco->lin_inicio][k+matrizResultados[i]->bloco->col_inicio] += matrizResultados[i]->matriz[j][k];

	      }

	    }

	}
	matrizPrint(resultante, mat_a->lin,mat_b->col);
	*result = resultante;

return 0;

}
int escrever_matriz_arquivo(int **matriz, int lin, int col, int iteracao, int tipo){

	char filename[100];

  sprintf (filename, "%dx%d-tipo%d.map", lin, col, tipo);

  FILE *infile = fopen(filename, "w+");

  fprintf(infile,"#Linha#Coluna#\n");
  fprintf(infile,"%d#%d#\n", lin, col);
  for (int i=0; i < lin; i++) {
    for (int j=0; j < col; j++){
      fprintf(infile,"%d,", matriz[i][j]);
    }
    fprintf(infile,"\n");
  }
	fclose(infile);
  return 0;
}

mymatriz mat_multiplica_OpenMP(mymatriz *mat_a, mymatriz *mat_b){

	int i, j, k;
	mymatriz mat_c;
	mat_c.matriz = NULL;
  mat_c.lin = mat_a->lin;
  mat_c.col = mat_b->col;
  if (malocar_mat(&mat_c)) {
    printf ("ERROR: Out of memory\n");
  }

	for (i = 0; i < mat_c.lin; ++i) {
			for (j = 0; j < mat_c.col; ++j) {
				mat_c.matriz[i][j] = 0;
			}
	}

	#pragma omp parallel for private(i,j,k) shared(mat_a,mat_b,mat_c) num_threads(16)
    for (i = 0; i < mat_c.lin; ++i) {
        for (j = 0; j < mat_c.col; ++j) {
            for (k = 0; k < mat_a->col; ++k) {
                mat_c.matriz[i][j] += mat_a->matriz[i][k] * mat_b->matriz[k][j];
            }
        }
    }

	return mat_c;
}


void values(mymatriz *m1, mymatriz *m2){

	for (int i = 0; i < m1->lin; i++) {
		 for (int j = 0; j < m1->col; j++) {
				m2->matriz[i][j] = m1->matriz[i][j];
		 }
	}
}

void mat_encherRandom(mymatriz *m)
{
  srand(time(NULL));

   int i, j;
   for (i = 0; i < m->lin; i++) {
      for (j = 0; j < m->col; j++) {
         m->matriz[i][j] = rand() % 50;
      }
   }
}

void mat_printar(mymatriz *m)
{

   int i, j;
   for (i = 0; i < m->lin; i++) {
      for (j = 0; j < m->col; j++) {
         printf("%d ", m->matriz[i][j]);
      }
      printf("\n");
   }
   fflush(stdout);
}

void multiplicacao_normal(mymatriz *A, mymatriz *B, mymatriz *C, int start)
{
  int i, j, k;

  int count = C->col;

  for (i = start; i < A->lin; i++) {
     for (j = 0; j < B->col; j++) {
        int sum = 0.0;
        for (k = 0; k < count; k++) {
           sum += A->matriz[i][k] * B->matriz[k][j];
        }
        C->matriz[i][j] = sum;
     }
  }
}
