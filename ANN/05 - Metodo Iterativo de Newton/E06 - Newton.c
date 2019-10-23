#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[]){

    float x1 = 1.48, x2 = 1.09;
    float Det;
    float A, B;

    printf("x1 = [%.8f,%.8f]\n",x1,x2);

    for(int i =2;i<=4;i++){

        A = pow(x1,2) - 3*pow(x2,2) + 5;
        B = pow(x1,2) + 2*pow(x2,2) - 5;

        Det = 1/( (2*x1*4*x2) - (-6*x2*2*x1) ); // correto

        float a11,a12,a21,a22;
        a11 = 4*x2;
        a12 = 6*x2;
        a21 = -2*x1;
        a22 = 2*x1;

        x1 = x1 - (Det * ((a11*A)+(a12*B)));
        x2 = x2 - (Det * ((a21*A)+(a22*B)));

        printf("x%d = [%.8f,%.8f]\n",i,x1,x2);



    }
}
