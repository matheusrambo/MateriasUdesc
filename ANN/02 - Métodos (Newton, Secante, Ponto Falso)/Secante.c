#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[]){
    float x=-0.877, y=0.229; //p1 e p2
    float aux, fx, fy;

    for(int i=0;i<4;i++){
      fx = (pow(x,3) - 4*x -1); //F(p1)
      fy = (pow(y,3) - 4*y -1); //F(p2)
      printf("P%d:[%f] P%d:[%f]\n", i+1,x,i+2,y);
      aux = ((x*fy) - (y*fx))/(fy - fx);
      x = y;
      y = aux;
    }

    printf("Secante: %f\n", y);

    return 0;
}
