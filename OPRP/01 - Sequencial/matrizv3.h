#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
  int lin;
  int col;
} mymatriz;

typedef struct {
  mymatriz* matriz;
  bloco_t* bloco;
} matriz_bloco_t;
#endif

// gerência de matrizes
int malocar (mymatriz *matriz);// verificado
int mgerar(mymatriz *matriz, int valor);// verificado
int mimprimir (mymatriz *matriz);// verificado
int mzerar (mymatriz *matriz);// verificado
int mliberar (mymatriz *matriz);// verificado
int mcomparar (mymatriz *mat_a, mymatriz *mat_b);// verificado

// submatriz
int gerar_submatriz (int **mat_origem, matriz_bloco_t *submatriz, bloco_t *bloco);//verificado
int imprimir_submatriz (matriz_bloco_t *submatriz);//verificado

int imprimir_bloco (matriz_bloco_t *submatriz);
matriz_bloco_t **particionar_matriz (int **matriz, int mat_lin, int mat_col, int orientacao, int divisor);
matriz_bloco_t **liberar_submatriz (matriz_bloco_t **submatriz);

matriz_bloco_t **constroi_submatriz (int **matriz, int mat_lin, int mat_col, int divisor);
matriz_bloco_t **constroi_submatrizv2 (int mat_lin, int mat_col, int divisor);
