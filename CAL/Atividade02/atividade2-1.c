#include <stdio.h>
#include <stdlib.h>
#include <time.h> //para analizar o tempo

//implementação de funções dadas no slide da aula 02
void bubbleSort(int *v, int n){
  int i, j, aux;
  for (i = n - 1; i > 0; i--){
    for (j = 0; j < i; j++){
      if (v[j] > v[j+1]){
        aux = v[j];
        v[j] = v[j+1];
        v[j+1] = aux;
      }
    }
  }
}

void selecao(int *v, int n){
  int i, j, x, aux;
  for (i = 0; i < n; i++){
    x = i;
    for (j = i+1; j < n; j++){
      if( v[j] < v[x] )
        x = j;
    }
    aux = v[i];
    v[i] = v[x];
    v[x] = aux;
  }
}
void insercao(int *v, int n){
  int i, j, x;
  for (i = 1; i < n; i++){
    x = v[i];
    j = i - 1;
    while (j >= 0 && v[j] > x){
      v[j+1] = v[j];
      j--;
    }
    v[j+1] = x;
  }
}
int main(){
  clock_t Ticks[12];
  double Tempo;
  int n = 100000, i;
  int vet1[n], vet2[n], vet3[n], vetOrdenado[n];
  for (i = 0; i < n; i++){
    vet1[i] = rand() % 100000; //função que coloca numeros aleatorios
    vetOrdenado[i] = i; //vetor já ordenado
  }
  //Passando mesmos valores do vet1 para o vet2 e vet3
  for (i = 0; i < n; i++){
      vet2[i] = vet1[i];
      vet3[i] = vet1[i];
  }
  Ticks[0] = clock();
  bubbleSort(vet1,n);
  Ticks[1] = clock();

  Ticks[2] = clock();
  bubbleSort(vetOrdenado,n);
  Ticks[3] = clock();

  Ticks[4] = clock();
  selecao(vet2,n);
  Ticks[5] = clock();

  Ticks[6] = clock();
  selecao(vetOrdenado,n);
  Ticks[7] = clock();

  Ticks[8] = clock();
  insercao(vet3,n);
  Ticks[9] = clock();

  Ticks[10] = clock();
  insercao(vetOrdenado,n);
  Ticks[11] = clock();


  printf("Tempos:\n\n");
  Tempo = (double)((Ticks[1] - Ticks[0]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("BubbleSort DESORDENADO = %0.5lf ms\n",Tempo);
  Tempo = (double)((Ticks[3] - Ticks[2]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("BubbleSort ORDENADO = %0.5lf ms\n",Tempo);

  Tempo = (double)((Ticks[5] - Ticks[4]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("Selecao DESORDENADO = %0.5lf ms\n",Tempo);
  Tempo = (double)((Ticks[7] - Ticks[6]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("Selecao ORDENADO = %0.5lf ms\n",Tempo);

  Tempo = (double)((Ticks[9] - Ticks[8]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("Insercao DESORDENADO = %0.5lf ms\n",Tempo);
  Tempo = (double)((Ticks[11] - Ticks[10]) ) * 1000.0/ CLOCKS_PER_SEC;
  printf("Insercao ORDENADO = %0.5lf ms\n",Tempo);

}
