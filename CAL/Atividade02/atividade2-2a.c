#include <stdio.h>
#include <stdlib.h>

void potencia(int a, int b){
	int c = 1;
  for (int i = 0; i < b; i++) {
  	c = c * a;
  }
  printf("%d elevado a %d = %d \n",a,b,c);

}

int main(){
  //declarações para a função de potencia
  int a = 5;
  int b = 10;

  potencia(a,b);
}
