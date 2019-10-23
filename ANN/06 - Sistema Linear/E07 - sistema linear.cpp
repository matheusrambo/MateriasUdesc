#include <iostream>
#include <math.h>
using namespace std;
int main(void){
    // Entrada
    double Num;
    // Vetores
    double a[20][20], b, x[20];
    // Vetor de Y
    //double NUMX[20] = {-2.5, -2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5};
    double NUMY[20] = {3.44, 3.6, 4.97, 0.73, 2.29, 2.9, 4.13, 1.08, 4.89, 1.98, 3.37, 2.78, 4.57, 0.16, 2.0};

    int TAM = 14;

    for (int i = 0; i <= TAM; i++){
        scanf("%lf", &Num);
        for (int j = 0; j <= 15; j++){
            a[i][j] = pow(Num, j);
            a[i][15] = NUMY[i];
        }
    }

    for (int j = 0; j <= TAM; j++){
        for (int i = 0; i <= TAM; i++){
            if (i != j){
                b = a[i][j] / a[j][j];
                for (int k = 0; k <= TAM + 1; k++){
                    a[i][k] = a[i][k] - b * a[j][k];
                }
            }
        }
    }

    for (int i = 0; i <= TAM; i++){
        x[i] = a[i][TAM + 1] / a[i][i];
        printf("a%d = %.9lf\n", i, x[i]);
    }
}
