EXERCÍCIOS

 

1)   A série de Fibonacci tem como os seus dois primeiros termos os valores 0 e 1. Os demais termos são construídos pela seguinte regra: tn = tn-1 + tn-2, sendo n o enésimo termo da série. Faça um programa em Java que mostra os n primeiros termos da série de Fibonacci e a soma destes n primeiros termos, onde n é informado pelo usuário.

2)   Um palíndromo é uma seqüência de caracteres que é lida da esquerda para a direita ou da direita para a esquerda. Por exemplo, cada um dos seguintes inteiros de 5 dígitos é um palíndromo: 12321, 55555, 45554 e 11611. Escreva um aplicativo que leia um inteiro de 5 dígitos e determine se ele é ou não um palíndromo. Se o número não for de 5 dígitos, exiba uma mensagem de erro e permita que o usuário insira um novo valor.

3)   Faça um programa em Java para calcular dígitos verificadores de CPF.

Regra:

O CPF é composto por onze algarismos, onde os dois últimos são chamados de dígitos verificadores, ou seja, os dois últimos dígitos são criados a partir dos nove primeiros. O cálculo é feito em duas etapas utilizando o módulo de divisão 11. Para exemplificar melhor será usado um CPF hipotético, por exemplo, 222.333.444-XX.

 

O primeiro dígito é calculado com a distribuição dos dígitos colocando-se os valores 10,9,8,7,6,5,4,3,2 conforme a representação abaixo:

 

2 2 2 3 3 3 4 4 4

10 9 8 7 6 5 4 3 2

 

Na seqüência multiplica-se os valores de cada coluna:

 

2    2    2    3    3    3    4    4    4

10  9    8    7    6    5    4    3    2

20 18  16  21  18  15  16  12   8

 

Em seguida efetua-se o somatório dos resultados (20+18+...+12+8), o resultado obtido (144) deve ser divido por 11. Considere como quociente apenas o valor inteiro, o resto da divisão será responsável pelo cálculo do primeiro dígito verificador. 144 dividido por 11 tem-se 13 de quociente e 1 de resto da divisão. Caso o resto da divisão seja menor que 2, o primeiro dígito verificador se torna 0 (zero), caso contrário subtrai-se o valor obtido de 11. Como o resto é 1 então o primeiro dígito verificador é 0 (222.333.444-0X).

 

Para o cálculo do segundo dígito será usado o primeiro dígito verificador já calculado. Monta-se uma tabela semelhante a anterior só que desta vez é usado na segunda linha os valores 11,10,9,8,7,6,5,4,3,2, já que é incorporado mais um algarismo para esse cálculo.

 

2    2   2  3  3  3  4  4  4  0

11 10  9  8  7  6  5  4  3  2

 

Na próxima etapa é feita como na situação do cálculo do primeiro dígito verificador, multiplica-se os valores de cada coluna:

 

2     2    2    3    3    3    4    4    4   0

11  10   9    8    7    6    5    4    3   2

22  20  18  24  21  18  20  16  12  0

 

 Depois efetua-se o somatório dos resultados: 22+20+18+24+21+18+20+16+12+0=171.

Agora, pega-se esse valor e divide-se por 11. Considere novamente apenas o valor inteiro do quociente, e com o resto da divisão, no caso 6, usa-se para o cálculo do segundo dígito verificador, assim como na primeira parte. Se o valor do resto da divisão for menor que 2, esse valor passa automaticamente a ser zero, caso contrário é necessário subtrair o valor obtido de 11 para se obter o dígito verificador, nesse caso 11-6=5. Portanto, chega-se ao final dos cálculos e descobre-se que os dígitos verificadores do CPF hipotético são os números 0 e 5, portanto o CPF fica:

 

222.333.444-05

