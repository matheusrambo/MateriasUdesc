algoritmo "semnome"
// Fun��o :
// Autor :
// Data : 12/11/2015
// Se��o de Declara��es 
var
v:vetor[1..50] de inteiro
i,n:inteiro
inicio
// Se��o de Comandos
   Repita
         leia(n)
   ate n<=50
   v[1]<-1
   v[2]<-1
   para i de 3 ate n faca
        v[i]<-v[i-1]+v[i-2]
   fimpara
   para i de 1 ate n faca
        escreval(i,":",v[i])
   fimpara
fimalgoritmo
