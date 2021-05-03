#include <math.h>
#include "matriz-operacoesv3.h"


void print_submatriz(matriz_bloco_t* mat){

	int i,j;

	int lin_comeco = mat->bloco->lin_inicio;
	int lin_fim = mat->bloco->lin_fim;

	int col_comeco = mat->bloco->col_inicio;
	int col_fim = mat->bloco->col_fim;

	for(i = lin_comeco; i < lin_fim; ++i){
		for(j = col_comeco; j < col_fim; ++j){
			int aux = mat->matriz->matriz[i][j]; //n lembro se vai diretão
			printf("|%d|\t", aux);
		}
		printf("\n");
	}
	return;
}

// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MATRIZ %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

mymatriz* msomar (mymatriz* mat_a, mymatriz* mat_b, int tipo) {

	mymatriz* mat_c = NULL;

	if ((mat_a->lin != mat_b-> lin) || (mat_a->col != mat_b->col)){
		printf ("Não dá pra fazer a operação.\n");
		exit(1);
	}

	mat_c = (mymatriz*) malloc (sizeof(mymatriz));
	
	mat_c->lin = mat_a->lin;
	mat_c->col = mat_a->col;

	if (malocar(mat_c)) {
		printf ("ERROR: Out of memory\n");
	}
//prestar atenção no final das chaves
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


mymatriz* mmultiplicar (mymatriz* mat_a, mymatriz* mat_b, int tipo) {
	mymatriz* mat_c = NULL;
	if (mat_a->col != mat_b->lin){
		printf ("Erro: Matrizes incompatíveis, deu ruim!\n");
		exit(1);
	}

	mat_c = (mymatriz*) malloc (sizeof(mymatriz));
	mat_c->lin = mat_a->lin;
	mat_c->col = mat_b->col;

	if (malocar(mat_c)) {
		printf ("Error: Out of memory\n");
	}

	for (int i = 0; i < mat_c->lin; ++i){
		for (int j = 0; j < mat_c->col; ++j){
			mat_c->matriz[i][j] = 0; //preencher com os 0's
		}
			
	}
	

	if (tipo == 1) { //se ela é tipo 1
		for (int i = 0; i < mat_c->lin; ++i){
			for (int j = 0; j < mat_c->col; ++j){
				for (int k = 0; k < mat_a->col; ++k){
					mat_c->matriz[i][j] += (mat_a->matriz[i][k] * mat_b->matriz[k][j]); //mult
				}
			}		
		}		
	} else { //se n
		for (int j = 0; j < mat_c->col; ++j){
			for (int i = 0; i < mat_c->lin; ++i){
				for (int k = 0; k < mat_a->col; ++k){
					mat_c->matriz[i][j] += (mat_a->matriz[i][k] * mat_b->matriz[k][j]);
				}
			}
		}
	}
	return mat_c;
}

int mmsubmatriz (matriz_bloco_t* mat_suba, matriz_bloco_t* mat_subb, matriz_bloco_t* mat_subc) {
	int a_lin_comeco = mat_suba->bloco->lin_inicio;
	int a_col_comeco = mat_suba->bloco->col_inicio;
	int b_lin_comeco = mat_subb->bloco->lin_inicio;
	int b_col_comeco = mat_subb->bloco->col_inicio;
	int diff = mat_subb->bloco->lin_fim - mat_subb->bloco->lin_inicio;

	for( int i = 0, ia = a_lin_comeco, ib = b_lin_comeco; i < mat_subc->matriz->lin; ++i, ++ia, ++ib) {
		for( int j = 0, ja = a_col_comeco, jb = b_col_comeco; j < mat_subc->matriz->col; ++j, ++ja, ++jb) {
			for( int k = a_col_comeco; k < a_col_comeco+diff; ++k){
				mat_subc->matriz->matriz[i][j] += mat_suba->matriz->matriz[ia][k] * mat_subb->matriz->matriz[k][jb];
			}
		}
	}
	printf("MULTIPLICAÇÃO DE SUBMATRIZ COMPLETA\n");
	print_submatriz(mat_subc);
	return 1;

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
matriz_bloco_t** csubmatrizv2(int mat_lin, int mat_col, int divisor) {
	int** m = (int**) malloc( mat_lin * sizeof( int*));
	for(int i = 0; i < mat_lin; ++i) {
		m[i] = (int*) malloc( mat_col * sizeof(int));
	}

	matriz_bloco_t** submatriz = ( matriz_bloco_t**) malloc( divisor * sizeof( matriz_bloco_t*));
	if( !submatriz){
		printf("ERROR: Out of memory\n");
		return NULL;
	}
	for(int i = 0; i < divisor; ++i) {
		submatriz[i] = ( matriz_bloco_t*) malloc( sizeof( matriz_bloco_t));
		if(!submatriz[i]){
			printf("ERROR: Out of memory\n");
			return NULL;
		}
		mymatriz* matriz = (mymatriz*) malloc( sizeof(mymatriz));
		matriz->lin = mat_lin;
		matriz->col = mat_col;
		matriz->matriz = m;
		submatriz[i]->matriz = matriz;
		submatriz[i]->bloco = (bloco_t*) malloc( sizeof( bloco_t));
		submatriz[i]->bloco->lin_fim = mat_lin;
		submatriz[i]->bloco->col_fim = mat_col;
		if(!submatriz[i]->bloco) {
			printf("ERROR: Out of memory\n");
			return NULL;
		}
	}
	return submatriz;
}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

matriz_bloco_t** particionar_matriz(int** matriz, int mat_lin, int mat_col, int orientacao, int divisor) {

	int lin_inicio = 0;
	int col_inicio = 0;
	int corte_da_matriz, resto;

	//orientação vertical = 0, horizontal = 1

	if( orientacao == 0) { 
		corte_da_matriz = mat_lin / divisor;
		resto = mat_lin % divisor;
	} else { 
		corte_da_matriz = mat_col / divisor;
		resto = mat_col % divisor;
	}
	
	matriz_bloco_t** submatriz = csubmatrizv2(mat_lin, mat_col, divisor);
	
	for( int i = 0; i < divisor; ++i) {

		submatriz[i]->bloco->lin_inicio = lin_inicio;
		submatriz[i]->bloco->col_inicio = col_inicio;
		
		if(orientacao){ //corte_da_matriz horizontal
			submatriz[i]->bloco->lin_fim = lin_inicio + corte_da_matriz;
			submatriz[i]->bloco->col_fim = mat_col;
			lin_inicio += corte_da_matriz;
			
			if( i == ( divisor - 1)){
				submatriz[i]->bloco->lin_fim += resto; 
			}
				
		} else {
			submatriz[i]->bloco->col_fim = col_inicio + corte_da_matriz;
			submatriz[i]->bloco->lin_fim = mat_lin;
			col_inicio += corte_da_matriz;
			if( i == ( divisor - 1)){
				submatriz[i]->bloco->col_fim += resto;
			}
				
		}
	
		submatriz[i]->matriz->matriz = matriz;
	}

	return submatriz;
}
