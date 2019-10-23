1) Crie um programa que receba um número inteiro x do usuário e escreva todos os números naturais primos que existam entre 2 e x. Implemente o crivo de Eratóstenes.
Crivo de Eratóstenes: Para exemplificá-lo, vamos determinar a lista de números primos entre 2 e 30, sendo 30 o número digitado pelo usuário.

Inicialmente, determina-se o maior número a ser checado. Ele corresponde à raiz quadrada do valor limite, arredondado para baixo. No caso, a raiz de 30, arredondada para baixo, é 5.

Crie um vetor com todos os números inteiros de 2 até o valor limite: 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30.

Encontre o primeiro número do vetor. Ele é um número primo, 2.

Remova do vetor todos os múltiplos do número primo encontrado. No nosso exemplo, o vetor fica: 2 3 5 7 9 11 13 15 17 19 21 23 25 27 29.

O próximo número do vetor é primo. Repita o procedimento. No caso, o próximo número do vetor é 3. Removendo seus múltiplos, o vetor fica: 2 3 5 7 11 13 17 19 23 25 29. O próximo número, 5, também é primo; o vetor fica: 2 3 5 7 11 13 17 19 23 29. 5 é o último número a ser verificado, conforme determinado inicialmente. Assim, o vetor encontrado contém somente números primos.

(Retirado da Wikipedia)

Exemplo de entrada:

30

Exemplo de saída:

2 3 5 7 11 13 17 19 23 29

O programa deve imprimir todos os números primos de 2 a x, todos numa única linha, e pedir novamente um número inteiro para calcular. Ele também deve finalizar quando receber 0 de entrada do usuário, e mostrar uma mensagem de erro quando receber 1 de entrada.



2) Faça um programa que permita a leitura de matrizes 2 X 2 ou 3 X 3 e calcule seus determinantes.

- Para matrizes 2 X 2, o determinante é a diferença entre o termo principal e o termo secundário, sendo que o termo principal de uma matriz quadrada é o produto de sua diagonal principal, e o termo secundário é o produto da diagonal secundária.

- Para matrizes 3 X 3, implemente a Regra de Sarrus.

Regra de Sarrus:

Exemplo

(Retirado da Wikipedia)

Exemplo de entrada de matriz:

1 3 10

-1 1 10

0 2 10

Exemplo de saída:

0

O programa deve apresentar um menu, logo no início da execução, perguntando qual a dimensão da matriz a ser digitada. Após receber uma matriz e fazer o cálculo do determinante, o programa deve voltar ao menu e permitir que o usuário escolha novamente. O menu também deve ter uma opção para sair do programa.

