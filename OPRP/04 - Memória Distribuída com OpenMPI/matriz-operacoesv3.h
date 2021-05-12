#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <assert.h>
#include <time.h>
#include <math.h>
#include <pthread.h>

#ifndef SOME_HEADER_FILE_H

#define SOME_HEADER_FILE_H

typedef struct {
  int lin_inicio;
  int lin_fim;
  int col_inicio;
  int col_fim;
} bloco_t;

typedef struct {
  int **matriz;
  bloco_t *bloco;
} matriz_bloco_t;

typedef struct {
  int **matriz;
  int lin;
  int col;
} mymatriz;
#endif

pthread_mutex_t mutex;

typedef struct {
  matriz_bloco_t *matrizBlocoA;
  matriz_bloco_t **matrizBlocoB;
  matriz_bloco_t **matrizResultados;
  int **resultante;
  int cont, divisor;
} thread_arg;

int malocar_mat (mymatriz *matriz);
mymatriz *matrix_create(int lin, int col);

int mat_gerar(mymatriz *matriz, int valor);
int mat_imprimir (mymatriz *matriz);
int mat_zerar (mymatriz *matriz);
int mat_liberar (mymatriz *matriz);
int mat_comparar (mymatriz *mat_a, mymatriz *mat_b);

void multiplicacao_normal(mymatriz *A, mymatriz *B, mymatriz *C, int start);
mymatriz *mat_somar (mymatriz *mat_a, mymatriz *mat_b, int tipo);
mymatriz *mat_multiplicar (mymatriz *mat_a, mymatriz *mat_b, int tipo);
int escrever_matriz_arquivo(int **matriz, int lin, int col, int iteracao, int tipo);
matriz_bloco_t *mallocarBloco(int Alin_inicio, int Alin_fim, int Bcol_inicio, int Bcol_fim);
void somando_subbloco(int ***resultante, matriz_bloco_t *matrizResultados);
void matrizPrint(int **matriz, int lin, int col);
void matrizPrint2(int **matriz, int lin, int col, int linReal, int colReal);
int comparar_matrizes(int **matriz_a, int lin_a, int col_a, int **matriz_b, int lin_b, int col_b);

int mult_mat_submatrizes_seq (mymatriz *mat_a, mymatriz *mat_b, int direcao, int divisor,int ***result);
int mult_mat_submatrizes (mymatriz *mat_a, mymatriz *mat_b, int direcao, int divisor,int ***result);

void mat_explode(mymatriz *m);

int mat_mult_sub (matriz_bloco_t *mat_suba, matriz_bloco_t *mat_subb, matriz_bloco_t *mat_subc);

matriz_bloco_t **csubmatrizv2 (int mat_lin, int mat_col, int divisor);
matriz_bloco_t **particionar_matriz (int **matriz, int mat_lin, int mat_col, int orientacao, int divisor);

void values(mymatriz *m1, mymatriz *m2);

mymatriz mat_multiplica_OpenMP(mymatriz *mat_a, mymatriz *mat_b);

void mat_encherRandom(mymatriz *m);
void mat_printar(mymatriz *m);
