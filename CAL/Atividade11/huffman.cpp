#include <iostream>
#include <string>
#include <vector>
#include <utility>      // std::pair, std::get


using namespace std;


int existeLetra(vector <char> letra, char c);
void codigoFixo(vector <int> quant,vector <char> letra, string str);
void procurar(vector < pair <char,string> > codFix, char c);
int main(){

	string str = ("abracadabra pe de cabra");
	vector <int> quant;
	vector <char> letra;

	for(int i=0 ; i<str.length() ; i++){
		if(existeLetra(letra, str[i]) == -1){
			letra.push_back(str[i]);
			quant.push_back(1);
		}
		else{
			quant[existeLetra(letra, str[i])]++;
		}
	}

	/*
	for(int i=0 ; i< letra.size() ; i++){

		cout << letra[i] << " = " << quant[i] << endl;
	}
	*/
	codigoFixo(quant, letra, str);


	return 0;
}

int existeLetra(vector <char> letra, char c){

	for(int i=0 ; i<letra.size() ; i++){
		if(letra[i] == c)
			return i;
	}
	return -1;
}

void codigoFixo(vector <int> quant, vector <char> letra, string str){

	vector < pair <char,string> > codFix;
	codFix.push_back(make_pair('a', ("000")));
	codFix.push_back(make_pair('a', ("001")));
	codFix.push_back(make_pair('a', ("010")));
	codFix.push_back(make_pair('a', ("011")));
	codFix.push_back(make_pair('a', ("100")));
	codFix.push_back(make_pair('a', ("101")));
	codFix.push_back(make_pair('a', ("110")));
	codFix.push_back(make_pair('a', ("111")));



	for(int i=0 ; i<quant.size() ; i++){
		for(int j=i+1 ; j<quant.size() ; j++){
			if(quant[i] < quant[j]){
				int q = quant[i];
				int l = letra[i];
				quant[i] = quant[j];
				letra[i] = letra[j];
				quant[j] = q;
				letra[j] = l;
			}
		}
	}


	cout << "TABELA" << endl;
	for(int i=0 ; i<quant.size() ; i++){
		codFix[i].first = letra[i];
		cout << codFix[i].first << " " << codFix[i].second << endl;
	}

	cout << "\nabracadabra pe de cabra -> CODIFICADA" << endl;
	for(int i=0 ; i<str.length() ; i++){
		procurar(codFix, str[i]);
	}
	cout << endl;
}

void procurar(vector < pair <char,string> > codFix, char c){

	for(int i=0 ; i<codFix.size() ; i++){
		if(codFix[i].first == c)
			cout << codFix[i].second;
	}
}
