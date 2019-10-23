#include <iostream>
#include <vector>
#include <utility>
#include <conio.h>
#include <math.h>

using namespace std;

void marcacao(int altura, vector < pair <double,int> > &regua, int divisao, int tamanho );
bool check (vector < pair <double,int> > &regua, double valor);
void ordena(vector < pair <double,int> > &regua);
void print(vector < pair <double,int> > &regua);
int main()
{

    vector < pair <double,int> > regua;

    double left, right;
    int altura;
    cin >> left >> right >> altura;
    //cout << left << " " << right << " " << altura << endl;

    //regua.push_back(make_pair((right-left)/2 , altura));

    int n = 1;
    int divisao = pow(2, n);
    int tamanho = right - left;
    //cout << regua[0].first << " " << regua[0].second << endl;
    while(altura > 0){

        //regua.push_back(make_pair((right-left)/2 , altura));
        marcacao(altura, regua, divisao, tamanho);
        ordena(regua);
        print(regua);
        altura -= 1;
        n++;
        divisao = pow(2, n);
    }

    return 0;
}

void marcacao(int altura, vector < pair <double,int> > &regua, int divisao, int tamanho){

    //cout << "altura = " << altura << " ||| " << "divisao = " << divisao << endl;
    for(int i=0 ; i<=divisao ; i++){
        double v = i * ((double)tamanho/divisao);
        //cout << "VALOR DE V = " << v << endl;
        if(!check(regua, v)){
            regua.push_back(make_pair( v , altura));
        }
    }
}

bool check (vector < pair <double,int> > &regua, double valor){

    for(int i=0 ; i<regua.size() ; i++){

        if(regua[i].first == valor)
            return true;
    }
    return false;
}

void ordena(vector < pair <double,int> > &regua){

    for(int i=0 ; i<regua.size() ; i++){

        for(int j=i+1 ; j<regua.size() ; j++){

            if(regua[i].first > regua[j].first){

                double aux1 = regua[i].first;
                double aux2 = regua[i].second;
                regua[i].first = regua[j].first;
                regua[i].second = regua[j].second;
                regua[j].first = aux1;
                regua[j].second = aux2;
            }
        }
    }
}

void print(vector < pair <double,int> > &regua){
    cout << "IMPRIMINDO REGUA" << endl;
    cout << "CM\tTAMANHO" << endl;
    for(int i=0 ; i<regua.size() ; i++){
        cout << regua[i].first << "\t" << regua[i].second << endl;
    }
}
