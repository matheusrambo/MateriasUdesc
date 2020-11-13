#include <stdio.h>
#include <stdlib.h>

int * f_marior( int *x, int *y);

int main(void){
  int x, y, *z;
  scanf("%i %i", &x, &y);
  z = f_marior(&x, &y);
  printf("%i\n", *z);


  return 0;
}

 int * f_marior( int *x, int *y){
   if (*x > *y){
     return x;
   }
   else {
     return y;
   }

 }
