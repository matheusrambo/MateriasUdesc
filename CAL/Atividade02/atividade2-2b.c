#include <stdio.h>
#include <stdlib.h>

void multiplicaMatrizes(int **M1, int **M2, int **M3, int n){
  int i,j,k;
  for (i=0;i<n; i++) {
      for (j=0; j<n; j++) {
        for (k=0; k<n; k++) {

          M3[i][j] = M3[i][j] + (M1[i][k] * M2[k][j]);

        }
      }
    }
}

int main(){

  int i, j;
  int n = 2;

  //Criação das Matrizes
  int **M1 = (int**)malloc(n * sizeof(int*));
  int **M2 = (int**)malloc(n * sizeof(int*));
  int **M3 = (int**)malloc(n * sizeof(int*));

	printf("Valores da M1:\n");
	for (i = 0; i < n; i++){
		M1[i] =  malloc(n * sizeof(int));
    for (j = 0; j < n; j++){
      scanf("%d\n", &M1[i][j]);
		}
	}
	printf("Valores da M2:\n");
  for (i = 0; i < n; i++){
    M2[i] =  malloc(n * sizeof(int));
    for (j = 0; j < n; j++){
      scanf("%d\n", &M2[i][j]);
		}
	}
	for (i = 0; i < n; i++){
		M3[i] =  malloc(n * sizeof(int));
		for (j = 0; j < n; j++){
			M3[i][j]=0; //matriz 3 esta vazia pois recebera a multiplicação da m1 com m2
		}
	}
  multiplicaMatrizes(M1,M2,M3,n);
  //Pintando a matriz 3 que recebeu a multiplicação da M1xM2
  for (i=0; i<n; i++) {
      for (j=0; j<n; j++) {
        printf("%d ", M1[i][j]);
      }
      printf("\n");
    }
		for (i=0; i<n; i++) {
	      for (j=0; j<n; j++) {
	        printf("%d ", M2[i][j]);
	      }
	      printf("\n");
	  }
}
