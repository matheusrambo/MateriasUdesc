#include <stdio.h>
#include <stdlib.h>

int *mallok(int i);
int * f_inverte_INT (int * vetor_original, int tam);

int main(void){
  int x=0;
  scanf("%i", &x);
  int *vetor_original=NULL;
  vetor_original = mallok(x);
  int i;
  for (i= 0; i < x; i ++){
    scanf("%i", &vetor_original[i]);
  }
  vetor_original = f_inverte_INT(vetor_original, x);

  for(i=0; i < x; i ++){
    printf("%i ", vetor_original[i]);
  }
  printf("\n");

  return 0;
}
int * f_inverte_INT(int * vetor_original, int tam){
    int i, x;
    int *novo_vetor = NULL;
    novo_vetor = mallok(tam);
    for (i=0, x = tam - 1; i < tam; x-- , i++){
      novo_vetor[i] = vetor_original[x];
    }
    return novo_vetor;
}
int *mallok(int i){
  int *v = NULL;
  v = (int*) malloc(sizeof(int)*i);
  return v;
}
