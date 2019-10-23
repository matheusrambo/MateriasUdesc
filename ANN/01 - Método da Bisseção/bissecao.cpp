#include <bits/stdc++.h>

using namespace std;

class f{
    public:
    static double x;
    static double functionx(double x);
};

double f::functionx(double x){
    x = pow(x,5) - 4*x - 3;
    //x = cos(pow(x,2)) - x;
    return x;
}

class bolzano{

  public:
    static bool fbolzano(double a, double b);

};

bool bolzano::fbolzano(double a, double b){

  if( f::functionx(a) * f::functionx(b)  < 0) return true;
  else return false;

}

class bsearch{

  public:
    static double binary_lm(double a, double b, int loop);
    static double binary_ml(double a, double b, int loop);

};

//LM o f(a) é negativo e f(b) positivo
double bsearch::binary_lm(double a, double b, int loop){

  std::cout << 8-loop << " : " <<(a+b)/2 << std::endl;
  if(loop==0) return (a+b)/2;
  if( f::functionx((a+b)/2) > 0) binary_lm(a, (a+b)/2, --loop);
  else binary_lm((a+b)/2,b,--loop);

}

//ML o f(a) é positivo e f(b) negativo
double bsearch::binary_ml(double a, double b, int loop){
  std::cout << 8-loop << " : " <<(a+b)/2 << std::endl;
  if(loop==0) return (a+b)/2;
  if( f::functionx((a+b)/2) < 0) binary_ml(a, (a+b)/2, loop--);
  else binary_ml(a+b,b,loop--);

}

int main(void){

    double a = 0.048;
    double b = 2.157;

    if(bolzano::fbolzano(a,b)){

        if ( f::functionx(a) < 0 ) cout << bsearch::binary_lm(a,b,7) << endl;
        else bsearch::binary_ml(a,b,7);

    }
    //cout << "Numero de iteracoes maior que " << ceil(( log(b-a)-log(pow(10,-15)) )/log(2) ) << endl;
    return 0;
}
