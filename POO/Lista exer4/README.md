Aula Prática 4

Especificação de Classes e Criação de Objetos

 

Objetivo: Avaliar os conhecimentos do aluno sobre os conceitos básicos de orientação a objetos aplicados à linguagem de programação Java. Nos exercícios abaixo devem ser utilizados conceitos de classe, objetos, construtores, criação de objetos e comunicação entre objetos.

 

Exercícios:

 

1) Faça um programa, orientado a objetos, em Java que leia o nome e 4 notas de uma turma de estudantes de tamanho aleatório, calcule a média de cada estudante, a média da turma e mostre a média da turma e o nome e a média de cada estudante, em ordem decrescente do valor da média.

 

 

2) Faça um programa, orientado a objetos, em Java que gerencie as informações de funcionários de uma empresa. Este programa deve ser capaz de realizar o cálculo do salário líquido de cada funcionário, os descontos do salário bruto do funcionário e os benefícios agregados ao salário do funcionário.

Além da especificação das classes apresentadas abaixo, o aluno também deverá desenvolver este programa como uma aplicação em 2 camadas, contendo uma camada com as classes de dados, e outra com a interface de usuário. Todos os dados referentes à empresa e ao funcionário deverão ser fornecidos através da interface com o usuário.

Segue abaixo a lista de classes que este programa deve conter, com seus respectivos atributos e métodos:

 

Classe SistemaEmpresa

Atributos

-Empresa

 

 

Métodos

+cadastrar Empresa()

+cadastrar Endereço da Empresa()

+cadastrar Endereço do Funcionário()

+cadastrar Funcionário da Empresa()

+cadastrar Cargo da Empresa()

+definir Cargo do Funcionário()

+mostrar Salário Líquido do Funcionário()

+mostrar Salário Bruto do Funcionário()

+mostrar os descontos do salário do Funcionário()

+mostrar valor das horas extras do Funcionário()

+mostrar o valor dos benefícios do Funcionário()

   

Classe Empresa

Atributos

-nome

-Endereço

-Funcionários[]

-Cargos[]

Métodos

+get/set Nome()

+get/set Funcionário()

+get/set Cargo()

+get/set Endereço()

+calcular Média Salário Funcionários()

 

Classe Endereço

Atributos

-rua

-número

-bairro

-cidade

-estado

-CEP

Métodos

+gets()/sets()

 

Classe Funcionário

Atributos

-nome

-Endereço

-Cargo

-número de horas extras

-número de filhos

 

Métodos

+gets()/sets()

+calcular Salário Líquido() {

     salário base + calcular Acréscimos() - calcular Descontos()

  }

+calcular Salário Bruto() {

     base+ calcular Acréscimos()

  }

+calcular Descontos() {

    calcular INSS()+calcular RI()

  }

+calcular Acréscimos() {

     valor por filho*número de filhos +

     valor da hora extra* número de horas extras

  }

+calcular INSS() {base – 11%}

+calcular RI() {

     salário base < 1.372,81 -> 0%

     salário base > 1.372,82 e < 2.743,25 -> 15%

     salário base > 2.743,25 -> 27,5%

  }

 

Classe Cargo

Atributos

-descrição

-salário base

-valor da hora extra

-valor por filho

 

Métodos

+gets()/sets()