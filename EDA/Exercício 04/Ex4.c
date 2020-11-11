#include <stdio.h>
#include <stdlib.h>
#define MAX 51



typedef struct
{
	int topo;
	char entrada[MAX];
} duracell;


void inicializar_pilha(duracell * P);
int pilha_cheia(duracell * P);
int pilha_vazia(duracell * P);
void push(char x, duracell * P);
char pop(duracell * P);
char pop_bottom(duracell * P);
void mover_base(duracell * P);

int main(void){
	duracell PilhaM;
	duracell PilhaW;
	inicializar_pilha(&PilhaM);
  inicializar_pilha(&PilhaW);
	char v[50];
	scanf("%s", v);
	int i, tam, j;
	for(tam=0;v[tam] != '\0'; tam++);

	for(i=0; v[i] != 'c';i++)
	{
		push(v[i], &PilhaM);
	}
  j = i + 1;

	for(;j < tam; j++){
    push(v[j], &PilhaW);
  }
  int t = 0;

  while(PilhaW.topo == PilhaM.topo && PilhaW.topo > 0){


    if(pop(&PilhaM) == pop_bottom(&PilhaW))
    t++;
    else
    {
      t=99;
      break;
    }
  }
  if (t == tam/2)
  {
    printf("ACEITO\n");
  }
  else
      printf("NAO ACEITO\n");



	return 0;
}

void inicializar_pilha(duracell *P){
	P->topo = 0;
}

int pilha_cheia(duracell * P){
	int x = P->topo;
	if (x >= MAX-1){
		x = 1;
	}
	else {
		x = 0;
	}
	return x;
}

int pilha_vazia(duracell * P){
	int x = P->topo;
	if (x < 0){
		x = 1;
	}
	else {
		x = 0;
	}
	return x;
}

void push( char x, duracell *P){
	int possivel = pilha_cheia(P);
	if (possivel == 1){
		printf("Pilha cheia, impossivel fazer o PUSH\n");
	}
	else {
		P->topo++;
		P->entrada[P->topo]=x;

	}

}
char pop_bottom (duracell *P){
  char x;
  int tam = P->topo;


  if (tam > 0)
  {

    x = P->entrada[1];
    int i;
    for(i = 1; P->entrada[i] != '\0'; i++){
      P->entrada[i] = P->entrada[i+1];
    }
    P->topo--;
    return x;
  }
  else
  return 0;

}
char pop(duracell *P )
{
	int possivel = pilha_vazia(P);
  char x;
	if (possivel == 1)
  {
		printf("Pilha vazia, impossivel fazer o POP\n");
    return 0;
	}
	else
  {
	  x = P->entrada[P->topo];
		P->topo--;
    return x;
	}
}

void mover_base(duracell *P){
	int aux = P->entrada[P->topo];
	int i;
	for (i = P->topo-1; i>= 0; i--){
		P->entrada[i+1] = P->entrada[i];
	}
	P->entrada[0] = aux;

}
