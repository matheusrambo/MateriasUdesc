#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "prog.h"

int main(){
	
	int menu,info_ind=0;
	int n,b,new;
	matriz_info* matriz;
	
	while(1){
		printf("----------------------------------\n");
		printf("0. Encerrar                      |\n");
		printf("1. Carregar matriz do arquivo    |\n");
		printf("2. Visualizar uma matriz         |\n");
		printf("3. Modificar uma matriz          |\n");
		printf("4. Somar duas matrizes           |\n");
		printf("5. Subtrair duas matrizes        |\n");
		printf("6. Multiplicar duas matrizes     |\n");
		printf("7. debug struct m                |\n");
		printf("8. salvar                        |\n");
		printf("----------------------------------\n");
		printf("Escolha: ");
		scanf("%d",&menu);
		if (menu == 0){
			exit(0);
		}
		if (menu == 1){
			if (info_ind == 0){
				matriz = malloc(sizeof(matriz_info)*(info_ind+1));
				matriz[info_ind] = abrir_arquivo();
				info_ind++; 
			}else{
				matriz = (matriz_info*) realloc(matriz,sizeof(matriz_info)*(info_ind+1));
				matriz[info_ind] = abrir_arquivo();
				info_ind++;
			}
		}
		if (menu == 2){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			printf("Visualizar uma matriz(1-%d)\n", info_ind);
			scanf("%d",&n);
			printf("%dª Matriz:\n", n);
			imprimi_matriz(matriz[n-1]);

		}
		if (menu == 3){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			int l,c;
			printf("Informe a matriz a modificar(1-%d)\n", info_ind);
			scanf("%d",&n);
			printf("\n%dª Matriz:\n", n);
			imprimi_matriz(matriz[n-1]);
			printf("\n");
			while(1){
				printf("Informe a posiçao a modificar linha e coluna respectivamente(-1 -1 para sair): ");
				scanf("%d %d",&l,&c);
				if(l==-1 && c==-1){
					printf("\n");
					break;
				}

				if(l<0 || c<0 || l>=matriz[n-1].linhas || c>=matriz[n-1].colunas){
					printf("Posição inválida, tente novamente!!\n\n");
					continue;
				}
				
				printf("Novo valor: ");
				scanf("%d", &new);

				modificar_matriz(&matriz[n-1],l,c,new);
				printf("\n%dª Matriz:\n", n);
				imprimi_matriz(matriz[n-1]);
				//debug_struct(matriz[n-1]);
				printf("\n");
			}
		}
		if (menu == 4){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			printf("Informe a primeira matriz para soma(1-%d)\n", info_ind);
			scanf("%d",&n);
			printf("Informe a segunda matriz para soma(1-%d)\n", info_ind);
			scanf("%d",&b);
			if (matriz[n-1].linhas == matriz[b-1].linhas && matriz[n-1].colunas == matriz[b-1].colunas){

				matriz = (matriz_info*) realloc(matriz,sizeof(matriz_info)*(info_ind+1));

				matriz[info_ind] = soma_matriz(matriz[n-1], matriz[b-1]);
				
				info_ind++;
			}else{
				printf("Matriz invalidas\n");
			}
		}

		if (menu == 5){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			printf("Informe a primeira matriz para subtrair(1-%d)\n", info_ind);
			scanf("%d",&n);
			printf("Informe a segunda matriz para subtrair(1-%d)\n", info_ind);
			scanf("%d",&b);
			if (matriz[n-1].linhas == matriz[b-1].linhas && matriz[n-1].colunas == matriz[b-1].colunas){

				matriz = (matriz_info*) realloc(matriz,sizeof(matriz_info)*(info_ind+1));

				matriz[info_ind] = subtrai_matriz(matriz[n-1], matriz[b-1]);
				
				info_ind++;
			}else{
				printf("Matriz invalidas\n");
			}
		}

		if (menu == 6){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			printf("Informe a primeira matriz para multiplicar(1-%d)\n", info_ind);
			scanf("%d",&n);
			printf("Informe a segunda matriz para multiplicar(1-%d)\n", info_ind);
			scanf("%d",&b);
			if (matriz[n-1].linhas == matriz[b-1].colunas){

				matriz = (matriz_info*) realloc(matriz,sizeof(matriz_info)*(info_ind+1));

				matriz[info_ind] = multplica_matriz(matriz[n-1], matriz[b-1]);
				
				info_ind++;
			}else{
				printf("Matriz invalidas\n");
			}
		}

		if (menu == 7){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}else{
				printf("(1-%d)\n", info_ind);
				scanf("%d",&n);
				debug_struct(matriz[n-1]);
			}
		}

		if (menu == 8){
			if(info_ind==0){
				printf("Nenhuma matriz carregada no momento!\n");
				continue;
			}
			printf("Salvar(1-%d)\n", info_ind);
			scanf("%d",&n);
			salvar_matriz(matriz[n-1]);
		}

	}
	return 0;
}