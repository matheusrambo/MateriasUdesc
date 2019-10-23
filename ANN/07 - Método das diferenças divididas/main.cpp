#include "lagrange.cpp"



int main(void){
    /*
    Primeiramente vamos pegar o tamanho dos vetores, basicamente quantas pontos de entrada
    isso vai ser usado na criação de vetores(array) do tamanho correto
    */
    int tam = 21;

    /*Criação dos vetores X e Y*/
    double *vetX = (double*)malloc(sizeof(double)*tam);
    double *vetY = (double*)malloc(sizeof(double)*tam);

    /*Entrada dos valores dos pontos*/
    for(int i=0;i<tam;i++){
        std::cin >> vetX[i];
        std::cin >> vetY[i];
    }

    /*Algoritimo de lagrange*/
    lagrange::i(vetX,vetY,tam,0);

    return 0;
}
