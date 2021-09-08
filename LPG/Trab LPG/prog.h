#ifndef NOME
#define NOME 

typedef struct{
	int indice,num;
}matriz_compc;

typedef struct{
	char nome[50];
	int linhas,colunas;
	matriz_compc** m;
}matriz_info;

matriz_info abrir_arquivo();

int** visualisar_matriz(matriz_info info);

int acha_na_compactada(matriz_compc* compc, int c);

void modificar_matriz(matriz_info* info,int l,int c, int new);

void imprimi_matriz(matriz_info info);

void salvar_matriz(matriz_info info);

//int le_valor(matriz_info info, int i, int j);

void criar_matriz0(matriz_info* p,int l, int c);

matriz_info soma_matriz(matriz_info m1,matriz_info m2);

void debug_struct(matriz_info info);

matriz_info subtrai_matriz(matriz_info m1, matriz_info m2);

matriz_info multplica_matriz(matriz_info m1, matriz_info m2);

#endif
