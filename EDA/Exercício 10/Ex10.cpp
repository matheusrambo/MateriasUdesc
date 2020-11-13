#include <stdio.h>
#include <stdlib.h>
int** inicializa_Matriz_Quadrada (int n);
int main (void){
  int n;
  scanf("%i", &n);
  int **m = NULL;
  m = inicializa_Matriz_Quadrada(n);
  int lin, col;
  for(lin = 0; lin < n; lin++){
    for(col= 0; col < n; col ++){
      printf("%i ", m[lin][col] );
    }
    printf("\n");
  }
  return 0;
}

int **inicializa_Matriz_Quadrada(int n){
  int **m =NULL;
  int contador, lin, col;
  m = (int **)malloc(sizeof(int *)* n);
  for(contador = 0; contador < n; contador ++){
    m[contador] = (int *)malloc(sizeof(int)*n);
  }
  for(lin = 0, contador = 111; lin < n; lin++){
    for(col = 0; col < n; col ++, contador++){
      m[lin][col] = contador;
    }
  }
  return m;
}
