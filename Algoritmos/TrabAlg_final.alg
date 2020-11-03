algoritmo "Trabalho: conjuntos em pseudocódigo"
// Função :
// Autor : Bruno Muriel, Matheus Rambo
// Data : 08/12/2015
// Seção de Declarações
var
veta:vetor[1..50] de inteiro
vetb:vetor[1..50] de inteiro
vetuniao:vetor[1..50] de inteiro
vetinter:vetor[1..50] de inteiro
vetdif:vetor[1..50] de inteiro
contins, i, i2, n, conjunto :inteiro
conta, contb, contuni, continter, contdif, valorA, valorB :inteiro
opcao,operacao:caracter
numeroigual:logico
inicio
// Seção de Comandos
conta<-0
contb<-0
valorA<-1
valorB<-1

repita
   escreval("Escolha uma Operação:")
   escreval("")
   escreval("Inserir valores em um conjunto                 (Digite 1)")
   escreval("Remover valores de um conjunto                 (Digite 2)")
   escreval("Exibir os valores dos conjuntos                (Digite 3)")
   escreval("Realizar a união dos conjuntos                 (Digite 4)")
   escreval("Realizar a intersecção dos conjuntos           (Digite 5)")
   escreval("Realizar a subtração dos conjuntos             (Digite 6)")
   escreval("Realizar produto cartesiano entre os conjuntos (Digite 7)")
   escreval("Fechar Programa                                (Digite 0)")
   leia(operacao)

   escolha operacao

   caso "1"
      limpatela
      escreval("Inserir no Conjunto A           (Digite 1)")
      escreval("Inserir no Conjunto B           (Digite 2)")
      escreval("Retornar ao Menu Principal  (Aperte Enter)")
      leia(opcao)
      se  opcao="1" entao
         contins<-conta+1
         repita  //esse repita é para se digitar "0" parar de solicitar valores
            escreval("Insira ",contins,"º o valor do conjunto A:")
            contins<-contins+1
            repita  //repete enquanto o numero for diferente
               leia(valorA)
               numeroigual<-falso
               se conta<>0 entao
                  para i de 1 ate conta faca
                     se veta[i]=valorA entao  // verificando se o numero digitado ja existe
                        numeroigual<-verdadeiro
                     fimse
                  fimpara
               fimse
               se numeroigual entao
                  escreval("Este valor ja existe")
               senao
                  se (valorA<>0) entao
                     contA<-contA+1
                     veta[conta]<-valorA
                  fimse
               fimse
            ate nao numeroigual
        ate(valorA=0)
      fimse

      se  opcao="2" entao
         contins<-contb+1
         repita
            escreval("Insira ",contins,"º o valor do conjunto B:")
            contins<-contins+1
            repita
               leia(valorB)
               numeroigual<-falso
               se contb<>0 entao
                  para i de 1 ate contb faca
                     se vetb[i]=valorB entao
                        numeroigual<-verdadeiro
                     fimse
                  fimpara
               fimse
               se numeroigual entao
                  escreval("Este valor ja existe")
               senao
                  se valorB<>0 entao
                     contB<-contB+1
                     vetb[contb]<-valorB
                  fimse
               fimse
            ate nao numeroigual
         ate(valorB=0)
      fimse

      limpatela

   caso "2"
      limpatela
      escreval("Remover valores do Conjunto A     (Digite 1)")
      escreval("Remover valores do Conjunto B     (Digite 2)")
      escreval("Retornar ao Menu Principal    (Aperte Enter)")
      repita
         leia(opcao)
         se (opcao="1") ou (opcao="a") entao
            se veta[1]=0 entao
               escreval("Este conjunto já está vazio.")
            senao
               para i de 1 ate conta faca
                  veta[i]<-0
               fimpara
               escreval("Conjunto deletado!")
               conta<-0
            fimse
            leia(opcao)
         fimse
         se (opcao="2") ou (opcao="b") entao
            se vetb[1]=0 entao
               escreval("Este conjunto já está vazio.")
            senao
               para i de 1 ate contb faca
                  vetb[i]<-0
               fimpara
               escreval("Conjunto deletado!")
               contb<-0
            fimse
         fimse
      ate (opcao<>"1") e (opcao<>"2") e (opcao<>"a") e (opcao<>"b")
      limpatela
   caso "3"
      limpatela
      escreval("Mostrar valores do Conjunto A     (Digite 1)")
      escreval("Mostrar valores do Conjunto B     (Digite 2)")
      escreval("Retornar ao Menu Principal    (Aperte Enter)")
      repita
         leia(opcao)
         se (opcao="1") ou (opcao="a") entao
            se veta[1]=0 entao //verificando se tem valor no vetor A
               escreval("Este conjunto está vazio")
            senao
               escreva("A sequencia dos valores do conjunto A é: ")
               para i de 1 ate conta faca
                  escreva(" ",veta[i])
               fimpara
               escreval("")
            fimse
         fimse
         se (opcao="2") ou (opcao="b") entao
            se vetb[1]=0 entao //verificando se tem valor no vetor B
               escreval("Este conjunto está vazio")
            senao
               escreva("A sequencia dos valores do conjunto B é: ")
               para i de 1 ate contb faca
                  escreva(" ",vetb[i])
               fimpara
               escreval("")
            fimse
         fimse
      ate (opcao<>"1") e (opcao<>"2") e (opcao<>"a") e (opcao<>"b")
      limpatela

   caso "4"
      limpatela
      contuni<-0
      escreval("A União dos conjuntos:")
      escreval()
      para i de 1 ate conta faca
         contuni<-contuni+1
         vetuniao[i]<-veta[i] //adicionando vetor A
      fimpara
      para i2 de 1 ate contb faca //verificando os numeros iguais
         numeroigual<-falso
         para i de 1 ate contuni faca
            se vetuniao[i]=vetb[i2] entao
               numeroigual<-verdadeiro
            fimse
         fimpara
         se nao(numeroigual) entao
            contuni<-contuni+1
            vetuniao[contuni]<-vetb[i2]
         fimse
      fimpara
      se vetuniao[1]=0 entao //verificando se tem valor no vetor A
         escreval("Este conjunto está vazio")
         escreval()
      senao
         escreva("A sequencia dos valores do conjunto da união é: ")
         para i de 1 ate contuni faca
            escreva(" ",vetuniao[i])
         fimpara
         escreval("")
      fimse
      para i de 1 ate contuni faca //zerando o vetor da uniao
         vetuniao[i]<-0
      fimpara
      contuni<-0
      escreval("Aperte Enter para voltar ao menu.")
      leia(opcao)
      limpatela

   caso "5"
      limpatela
      continter<-1
      escreval("Intersecção dos conjuntos:")
      escreval()
      se (veta[1]=0) ou (vetb[1]=0) entao
         Escreval("Um dos vetores está Sem valor")
      senao
         para i de 1 ate conta faca
            para i2 de 1 ate contb faca
               se veta[i]=vetb[i2] entao
                  vetinter[continter]<-veta[i]
                  continter<-continter+1
               fimse
            fimpara
         fimpara

         escreval("A sequencia dos valores do conjunto da intersecção é: ")
         para i de 1 ate continter-1 faca
            Escreva(vetinter[i])
         fimpara
      fimse
      escreval()
      escreval("Aperte Enter para voltar ao menu.")
      leia(opcao)
      limpatela

   caso "6"
      limpatela
      escreval("Mostrar diferença A-B             (Digite 1)")
      escreval("Mostrar diferença B-A             (Digite 2)")
      escreval("Retornar ao Menu Principal    (Aperte Enter)")
      repita
         leia(opcao)
         escreval()
         contdif<-0
         se (opcao="1") entao
            para i de 1 ate conta faca
               numeroigual<-falso
               para i2 de 1 ate contb faca
                  se veta[i]=vetb[i2] entao
                     numeroigual<-verdadeiro
                  fimse
               fimpara
               se numeroigual=falso entao
                  contdif<-contdif+1
                  vetdif[contdif]<-veta[i]
               fimse
            fimpara
            se  contdif=0 entao
               escreval ("O conjunto está vazio!")
            senao
               escreva("A sequencia dos valores do conjunto da diferença (A-B) é: ")
               para i de 1 ate contdif faca
                  escreva(" ",vetdif[i])
               fimpara
               escreval()
            fimse
         fimse
         se (opcao="2") entao
            i<-0
            para i de 1 ate contdif faca
               vetdif[i]<-0
            fimpara
            contdif<-0
            para i de 1 ate contb faca
               numeroigual<-falso
               para i2 de 1 ate contb faca
                  se vetb[i]=veta[i2] entao
                     numeroigual<-verdadeiro
                  fimse
               fimpara
               se numeroigual=falso entao
                  contdif<-contdif+1
                  vetdif[contdif]<-vetb[i]
               fimse
            fimpara
            se contdif=0 entao
               escreval()
               escreval("O conjunto está vazio!")
               escreval()
            senao
               escreval()
               escreva("A sequencia dos valores do conjunto da diferença (B-A) é: ")
               para i de 1 ate contdif faca
                  escreva(" ",vetdif[i])
               fimpara
               escreval()
            fimse
         fimse
         i<-0
         para i de 1 ate contdif faca
            vetdif[i]<-0
         fimpara
         contdif<-0
      ate (opcao<>"1") e (opcao<>"2") e (opcao<>"a") e (opcao<>"b")
      limpatela

   caso "7"
      limpatela
      escreval("Produto cartesiano entre os conjuntos")
      escreval()
      se (veta[1]=0) ou (vetb[1]=0) entao
         escreval("Um dos Conjuntos está sem valor")
         escreval()
      senao
         para i de 1 ate conta faca
            para i2 de 1 ate contb faca
               escreval("(",veta[i],",",vetb[i2],")")
            fimpara
         fimpara
      fimse
      escreval("Aperte Enter para voltar ao menu.")
      leia(opcao)
      limpatela

   fimescolha
   limpatela
ate (operacao="0")


fimalgoritmo


