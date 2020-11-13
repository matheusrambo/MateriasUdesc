#include <stdio.h>
#include <stdlib.h>

int f_posicao(char *str , char *substr);
int main(void){
  char *str =NULL;
  char *substr = NULL;
  int i;
  str=(char*) malloc(sizeof(char)*20);
  substr=(char*) malloc(sizeof(char)*20);
  scanf("%s %s", str, substr);
  i = f_posicao(str , substr);
  printf("%i \n", i);
  return 0;
}
int f_posicao(char *str , char *substr){
  int tamanhosub, tamanhostr;
  for(tamanhosub = 0; substr[tamanhosub] != '\0'; tamanhosub++);
  for(tamanhostr = 0; str[tamanhostr] != '\0'; tamanhostr++);
  int x=0;
  int i,k;
  int armazenar;
  for(i = 0; i < tamanhostr; i++){
    if(str[i] == substr[x]){

      x++;
      k = i+1;
      while(x < tamanhosub){
        if(k <= tamanhostr){

          if(str[k] == substr[x]){

            x++; k++;

          }
          else { break;}
        }
        else{
          break;
        }
      }
      if (x == tamanhosub){
        armazenar = i+1;
      }

    }
  }



  return armazenar;
}
