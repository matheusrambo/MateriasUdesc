algoritmo "M�DIA DO VETOR"
// Fun��o :
// Autor :
// Data : 05/11/2015
// Se��o de Declara��es 
var
v: vetor[1..10] de real
i,n:inteiro
soma,media:real
inicio
// Se��o de Comandos
     n<-3
     para i de 1 ate n faca
          escreval("digite o",i,"� numero")
          leia(v[i])
     fimpara
     soma<-0
     para i de 1 ate n faca
          soma<-soma+v[i]
     fimpara
     media<-soma/n
     escreval("a m�dia �: ",media)
     para i de 1 ate n faca
          se v[i]>media entao
             escreval("o(s) numero(s) acima da m�dia �(S�o): ",v[i])
          fimse
     fimpara
fimalgoritmo
