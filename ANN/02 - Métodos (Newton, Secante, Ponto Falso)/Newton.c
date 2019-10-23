#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[]){
    float x = 0.0;
    float y;
    //NEWTON
    for(int i=0;i< 5;i++){
      printf("X: %f\n", x);
      //x = x - ((pow(x,3) - 4*x -1)/( 3*pow(x,2) - 4));
      x = (cos(pow(x,2)))-x;
    }
    printf("NEWTON: %f\n", x);

    //SECANTES **FUNCIONANDO APENAS PARA O PONTO SEGUINTE
    //x=-0.877; y=-0.229;
    //cout <<"(CADERNO)SECANTES: " << ( (y*myf::f(x)) - (x*myf::f(y)) )/(myf::f(x) - myf::f(y)) << endl;

    //PONTO FALSO **FUNCIONANDO APENAS PARA O PONTO SEGUINTE
    //x=-.254098;y=.678;

    //cout << "P_FALSO:" << ((y*myf::f(x)) - (x*myf::f(y)) )/(myf::f(x) - myf::f(y)) << endl;

    return 0;
}
