algoritmo "vetror ordenado"
// Fun��o :
// Autor :
// Data : 05/11/2015
// Se��o de Declara��es 
var
v:vetor[1..10] de real
i,n,i_menor,x:inteiro
aux:real
inicio
// Se��o de Comandos
n<-5
     para i de 1 ate n faca
          escreval("digite o",i,"� numero")
          leia(v[i])
     fimpara
     para x de 1 ate n-1 faca   //loop para os passos
          i_menor<-x
          para i de x+1 ate n faca   //loop para a troca de cada vetor
               se v[i]<v[i_menor] entao
               i_menor<-i
               fimse
          fimpara
          aux<-v[x]
          v[x]<-v[i_menor]
          v[i_menor]<-aux
          //c�digo para visualizar os passos da troca de vetorea
          para i de 1 ate n faca
             escreva(v[i])
          fimpara
          escreval()
     fimpara
     escreval("a sequencia dos valores �: ")
     para i de 1 ate n faca
          escreval(v[i])
     fimpara
fimalgoritmo
