#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "prog.h"

/*typedef struct{
	int indice,num;
}matriz_compc;

typedef struct{
	char nome[50];
	int linhas,colunas;
	matriz_compc** m;
}matriz_info;
*/

matriz_info abrir_arquivo(){
	matriz_info info;
	FILE* arquivo;

	printf("Nome do arquivo: ");
	scanf("%s",info.nome);
	printf("\n");
	arquivo = fopen(info.nome,"rt");
	fscanf(arquivo,"%d %d",&info.linhas,&info.colunas);
	int i,j;
	info.m = malloc(sizeof(matriz_compc*)*info.linhas);
	int buf,ind=1;

	for (i = 0; i < info.linhas; i++){
		ind=1;
		info.m[i] = malloc(sizeof(matriz_compc)*ind);
		//printf("l[%d]: ",i);
		for (j = 0; j < info.colunas; j++){
			fscanf(arquivo,"%d",&buf);
			//printf("%d ",buf);

			if (buf != 0){

				ind++;
				//printf("Ind: %d\n", ind);
				info.m[i] = realloc(info.m[i],ind*sizeof(matriz_compc));


				info.m[i][ind-2].indice = j;
				info.m[i][ind-2].num = buf;
				//printf("[%2d]:%3d ",info.m[i][ind-2].indice, info.m[i][ind-2].num);
			}
		}
		info.m[i][ind-1].indice = -1;
		//printf("-1\n");
		//printf("%d\n", info.m[i][ind-1].indice);
	}

	fclose(arquivo);
	printf("Matriz carregada com sucesso!\n");
	return info;
}

int acha_na_compactada(matriz_compc* compc, int c){
	int i=0;
	for(i=0; compc[i].indice != -1; i++){
		if(compc[i].indice == c) return i;
	}
	return -1;
}

void modificar_matriz(matriz_info* info,int l,int c, int new){
	int i,k=0,count=0,atual=0, para_de_colocar=0;
	matriz_compc* copy = info->m[l];
	for(i=0; copy[i].indice != -1; i++){
		count++;
	}
	matriz_compc* nova_linha;
	int pos = acha_na_compactada(copy, c);
	if(pos != -1 && new != 0){
		copy[pos].num = new;
		info->m[l] = copy;
	}else if(new != 0){
		nova_linha = malloc(sizeof(matriz_compc)*(count+2));
		if (count == 0){
			nova_linha[0].indice = c;
			nova_linha[0].num = new;
			nova_linha[1].indice = -1;
			info->m[l]=nova_linha;
		}else{
			for(i = 0; i < count+1; i++){
				if(para_de_colocar != 1 && (c < copy[i].indice || copy[i].indice == -1)){

					nova_linha[atual].indice = c;
					nova_linha[atual++].num = new;

					nova_linha[atual].indice = copy[i].indice;
					nova_linha[atual++].num = copy[i].num;

					para_de_colocar=1;
				}else{
					nova_linha[atual].indice = copy[i].indice;
					nova_linha[atual++].num = copy[i].num;
				}
			}
			nova_linha[atual].indice = -1;
			info->m[l]=nova_linha;
		}
	}else{//zero case
		nova_linha = malloc(sizeof(matriz_compc)*(count));
		for (i = 0; i <= count; i++){
			if (copy[i].indice != c){
				nova_linha[k].indice = copy[i].indice;
				nova_linha[k].num = copy[i].num;
				k++;
			}
		}
		info->m[l]=nova_linha;
	}
}

void imprimi_matriz(matriz_info info){
	matriz_compc** m = info.m;
	int l=info.linhas, c=info.colunas;
	int i,j,pos;

	printf("-");
	for(i=0; i<c; i++){
		printf("----");
	}
	printf("\n");

	for (i = 0; i < l; i++){
		printf("|");
		for(j=0; j < c; j++){
			pos = acha_na_compactada(m[i],j);
			if(pos==-1)
				printf("  0|");
			else
				printf("%3d|",m[i][pos].num);
		}
		printf("\n");
		printf("-");
		for(j=0; j<c; j++){
			printf("----");
		}
		printf("\n");
	}
}

void salvar_matriz(matriz_info info){
	
	FILE* arquivo;
	int i,j,k=0;
	arquivo = fopen(info.nome,"w");
	fprintf(arquivo, "%d %d\n", info.linhas, info.colunas);
	for (i = 0; i < info.linhas; i++){
		for (j = 0; j < info.colunas; j++){
			if (j == info.m[i][k].indice){
				fprintf(arquivo, "%d ", info.m[i][k].num);
				k++;
			}else{
				fprintf(arquivo, "0 ");
			}
		}
		fprintf(arquivo,"\n");
		k=0;
	}
	fclose(arquivo);
}

void criar_matriz0(matriz_info* p,int l, int c){
	p->linhas = l;
	p->colunas = c;
	p->m = malloc(sizeof(matriz_compc*)*l);
	int i;
	for (i = 0; i < l; i++){
		p->m[i] = malloc(sizeof(matriz_compc));
		p->m[i][0].indice = -1;
	}
}


/*int le_valor(matriz_info info, int i, int j){
	matriz_compc* linha = info.m[i];
	if (linha[k].indice == j){
		return linha[k].num;
		k++;
	}else{
		return 0;
	}
}
*/
matriz_info soma_matriz(matriz_info m1, matriz_info m2){
	matriz_info mab;
	printf("Nome da resultante: ");
	scanf("%s",mab.nome);
	int i,j,soma,n1,n2,k=0,g=0;

	criar_matriz0(&mab, m1.linhas, m1.colunas);

	for (i = 0; i < m1.linhas; i++){
		for (j = 0; j < m1.colunas; j++){
			
		//	soma = le_valor(m1, i, j) + le_valor(m2, i, j);


			if (m1.m[i][k].indice == j){
				n1 = m1.m[i][k].num;
				k++;
			}else{
				n1 = 0;
			}

			if (m2.m[i][g].indice == j){
				n2 = m2.m[i][g].num;
				g++;
			}else{
				n2 = 0;
			}

			soma = n1+n2;

			printf("%d  ",soma);
			if(soma != 0){
				modificar_matriz(&mab, i, j, soma);
			}
		}
		k=0;
		g=0;
		printf("\n");
	}

	return mab;
}

matriz_info subtrai_matriz(matriz_info m1, matriz_info m2){
	matriz_info mab;
	printf("Nome da resultante: ");
	scanf("%s",mab.nome);
	int i,j,sub,n1,n2,k=0,g=0;

	criar_matriz0(&mab, m1.linhas, m1.colunas);

	for (i = 0; i < m1.linhas; i++){
		for (j = 0; j < m1.colunas; j++){
			
		//	sub = le_valor(m1, i, j) + le_valor(m2, i, j);


			if (m1.m[i][k].indice == j){
				n1 = m1.m[i][k].num;
				k++;
			}else{
				n1 = 0;
			}

			if (m2.m[i][g].indice == j){
				n2 = m2.m[i][g].num;
				g++;
			}else{
				n2 = 0;
			}

			sub = n1-n2;

			printf("%d  ",sub);
			if(sub != 0){
				modificar_matriz(&mab, i, j, sub);
			}
		}
		k=0;
		g=0;
		printf("\n");
	}

	return mab;
}

matriz_info multplica_matriz(matriz_info m1, matriz_info m2){
	matriz_info mab;
	printf("Nome da resultante: ");
	scanf("%s",mab.nome);
	int i,j,x,mult,n1,n2,k=0,g=0;

	criar_matriz0(&mab, m1.linhas, m2.colunas);

	for (i = 0; i < m1.linhas; i++){
		for (j = 0; j < m1.linhas; j++){
			for (x = 0; x < m1.colunas; x++){
 				
				if (m1.m[k][x].indice == x){
					n1 = m1.m[k][x].num;
					k++;
				}else{
					n1 = 0;
				}

				if (m2.m[x][g].indice == x){
					n2 = m2.m[x][g].num;
					g++;
				}else{
					n2 = 0;
				}

				mult += n1*n2;

				if(mult != 0){
					modificar_matriz(&mab, i, j, mult);
				}
			}
			mult=0;
			k=0;
			g=0;
		}
	}

	return mab;
}


void debug_struct(matriz_info info){
	int j,i;

	for (i = 0; i < info.linhas; i++){
		for (j = 0; info.m[i][j].indice != -1; j++){
			printf("%d %d  ",info.m[i][j].indice, info.m[i][j].num);
		}
		printf("%d\n",info.m[i][j].indice);
	}
}