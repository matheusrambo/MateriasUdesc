#include <bits/stdc++.h>

class lagrange{
    public:
        static double i(double *vetX,double *vetY, int tam, int loc);
};

double lagrange::i(double *vetX,double *vetY,int tam, int loc){
    /*
    Vamos criar um novo vetor Y, que será um tamanho menor do anterior, ele vai
    guardar nossos novos valores(a.k.a: A,B,C,D)
    */
    double *newVetY = (double*)malloc(sizeof(double)*(tam-1));
    /*
    Esse for faz o calculo, vamos guardar A,B,C,D...etc dentro do novo vetor
    */

    for(int l=0;l<tam-1;l++){
        newVetY[l] = (vetY[l+1]-vetY[l])/(vetX[l+1+loc]-vetX[l]);
        if (l == 0) {
          printf("%.9lf\n",newVetY[0]);
        }
    }
    /*
    Se o vetor tiver tamanho 1, ou seja, não tem como continuar os calculos, então retornamos o último A
    */
    if(tam==1) return newVetY[0];
    return i(vetX,newVetY,tam-1, loc+1);
}
