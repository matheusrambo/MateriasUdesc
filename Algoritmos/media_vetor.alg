algoritmo "MÉDIA DO VETOR"
// Função :
// Autor :
// Data : 05/11/2015
// Seção de Declarações 
var
v: vetor[1..10] de real
i,n:inteiro
soma,media:real
inicio
// Seção de Comandos
     n<-3
     para i de 1 ate n faca
          escreval("digite o",i,"º numero")
          leia(v[i])
     fimpara
     soma<-0
     para i de 1 ate n faca
          soma<-soma+v[i]
     fimpara
     media<-soma/n
     escreval("a média é: ",media)
     para i de 1 ate n faca
          se v[i]>media entao
             escreval("o(s) numero(s) acima da média é(São): ",v[i])
          fimse
     fimpara
fimalgoritmo
