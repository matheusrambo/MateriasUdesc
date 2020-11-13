#include <stdio.h>
#include <stdlib.h>

void f_ordenacao_3 ( int * A, int *B, int *C);
int main(void){
  int A, B, C;
  scanf("%i %i %i", &A, &B, &C);
  f_ordenacao_3(&A, &B, &C);
  printf ("%i %i %i\n", A, B, C);
  return 0;
}
void f_ordenacao_3(int *A, int *B, int *C){
  int aux;

  if (*A > *B){
    aux = *A;
    *A = *B;
    *B = aux;

  }
  if(*A > *C){
    aux = *A;
    *A = *C;
    *C = aux;

  }
  if (*C < *B){
    aux = *C;
    *C = *B;
    *B = aux;

  }


}
