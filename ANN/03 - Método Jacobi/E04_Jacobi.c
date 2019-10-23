#include <stdio.h>

int main(int argc, char const *argv[])
{
    float x1=-2.6, x2=1.5, x3=-0.7, x4=-3.8, x5=4.5, x6=-0.0, x7=-1.0;
    float Aux1,Aux2,Aux3,Aux4,Aux5,Aux6,Aux7;
        for(int i=0;i<10;i++){
            //Sistema com as 7 variáveis
            Aux1 = (+ 0.4 + 1.2*x2 - 1.8*x3 + 2.7*x4 + 1.3*x5 - 2.8*x6 + 0.1*x7)/12.9;
            Aux2 = (-1.7*x1 + 4.8 + 2.5*x3 + 2.1*x4 + 2.6*x5 - 1.7*x6 - 2.4*x7)/16.3;
            Aux3 = (+1.9*x1 + 0.3*x2 + 3.8 + 2.4*x4 - 0.9*x5 + 0.9*x6 + 2.3*x7)/13.5;
            Aux4 = (-1.6*x1 + 1.2*x2 + 1.3*x3 - 1.8 + 1.2*x5 - 0.7*x6 + 1.8*x7)/13.8;
            Aux5 = (+1.6*x1 + 2.6*x2 + 0.2*x3 - 0.2*x4 + 4.2 - 0.3*x6 - 2.8*x7)/12.5;
            Aux6 = (-1.7*x1 + 0.6*x2 - 1.3*x3 - 1.9*x4 - 1.0*x5 + 2.3 - 2.9*x7)/11.2;
            Aux7 = (-1.9*x1 + 0.2*x2 - 0.7*x3 - 2.2*x4 + 1.3*x5 - 1.4*x6 + 4.4)/13.4;

            x1 = Aux1;
            x2 = Aux2;
            x3 = Aux3;
            x4 = Aux4;
            x5 = Aux5;
            x6 = Aux6;
            x7 = Aux7;

            printf("\n\nIteracao: %d", i+2);
            printf("\nx1: %.8f\nx2: %.8f\nx3: %.8f\nx4: %.8f\nx5: %.8f\nx6: %.8f\nx7: %.8f\n", Aux1,Aux2,Aux3,Aux4,Aux5,Aux6,Aux7);
        }
    return 0;
}
