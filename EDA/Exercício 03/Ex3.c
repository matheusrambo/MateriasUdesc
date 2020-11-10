#include <stdio.h>
#include <stdlib.h>
#define MAX 100



typedef struct 
{
	int topo;
	char entrada[MAX];
} duracell;


void inicializar_pilha(duracell * P);
int pilha_cheia(duracell * P);
int pilha_vazia(duracell * P);
void push(char x, duracell * P);
void pop(char *x, duracell * P);
void mover_base(duracell * P);

int main(void){
	duracell P;
	inicializar_pilha(&P);
	int ncartas, i = 0;
scanf("%i", &ncartas);
	//while(scanf("%i", &ncartas), (ncartas != 0)){
		for (i = ncartas; i > 0; i--){


		push(i, &P);
		}
		int p = ncartas-1;
		
		int discard[p];
		int x = 0;
		while(p >= 1){
			discard[x] = P.entrada[P.topo];
			P.topo--;
			mover_base(&P);
			p--;
			x++;
		}
		printf("Cartas Descartadas: ");
		for(x = 0; x < ncartas - 2; x++){
			printf("%i, ", discard[x]);
			discard[x] = 0;
		}
		printf("%i", discard[ncartas-2]);
		discard[ncartas - 2] = 0;
		printf("\nCarta Restante: %i\n", P.entrada[P.topo]);
			inicializar_pilha(&P);
		
	//}
	
	return 0;
}

void inicializar_pilha(duracell *P){
	P->topo = -1;
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
	if (x <= 0){
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
void pop(char *x, duracell *P ){
	int possivel = pilha_vazia(P);
	if (possivel == 1){
		printf("Pilha vazia, impossivel fazer o POP\n");
	}
	else {
		*x = P->entrada[P->topo];
		P->topo--;
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