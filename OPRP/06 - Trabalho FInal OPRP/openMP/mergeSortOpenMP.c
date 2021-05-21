#include<stdio.h>
#include <stdlib.h>
#include<omp.h>

void merge(int array[],int low,int mid,int high){
  int temp[500000];
  int i,j,k,m;
  j=low;
  m=mid+1;
  for(i=low; j<=mid && m<=high ; i++)
  {
     if(array[j]<=array[m])
     {
         temp[i]=array[j];
         j++;
     }
     else
     {
         temp[i]=array[m];
         m++;
     }
  }
  if(j>mid)
  {
     for(k=m; k<=high; k++)
     {
         temp[i]=array[k];
         i++;
     }
  }
  else
  {
     for(k=j; k<=mid; k++)
     {
        temp[i]=array[k];
        i++;
     }
  }
  for(k=low; k<=high; k++)
     array[k]=temp[k];
}


void mergesort(int array[],int low,int high){
 int mid;
 if(low<high){
   mid=(low+high)/2;
   #pragma omp parallel sections num_threads(2){
      #pragma omp section{
          mergesort(array,low,mid);
        }
      #pragma omp section{
          mergesort(array,mid+1,high);
        }
    }
   merge(array,low,mid,high);
 }
}


int main(){
  FILE *pont_arq;
  pont_arq = fopen("dados.txt", "r");

  int *array;
  array = malloc(sizeof(int) * 500000);
  int teste = 0;
  int p=0;

  if(pont_arq == NULL){
    printf("Erro na abertura do arquivo!");
    return 0;
  } else {

    fscanf(pont_arq, "%d", &p);
    while (!feof (pont_arq)){
      array[teste] = p;
      //printf ("%d \n", p);
      fscanf (pont_arq, "%d", &p);
      teste = teste + 1;
    }
    fclose (pont_arq);
  }

  int i,size;
  size = 500000;

  mergesort(array,0,size-1);
  printf("Resultado ordenado:\n");

  for(i=0; i<size; i++)
    printf("%d ",array[i]);
  printf("\n");

  return 0;

}
