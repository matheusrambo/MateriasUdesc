(1)   Faça um programa em Java que implemente uma agenda de contatos com as classes descritas abaixo:



a)     Contém um menu principal que permite a Inserção, Remoção e Consulta de Dados;

b)     A inserção deve receber o nome, número de telefone, endereço e e-mail de um contato, e adicioná-lo à lista de contatos. (Nome, endereço e e-mail são dados do tipo String, e telefone é um dado do tipo int). Lembre-se antes de verificar se o telefone já foi inserido, e não permitir a inserção caso o número seja repetido! Nomes, endereços e/ou e-mails repetidos são permitidos.

c)     A remoção deve excluir um contato com todas as suas informações e, caso o contato removido seja anterior a algum outro contato, todos os contatos que se encontram após ele na agenda devem ser trazidos uma posição para frente, evitando assim espaços vazios no meio de contatos;

d)     A consulta deve mostrar uma lista de todos os telefones cadastrados, ou uma mensagem caso não haja nenhum contato cadastrado. Logo após, deve pedir ao usuário que digite um telefone para consultar. Se existir, deve dizer o nome, o endereço e o e-mail desse contato; caso contrário, deve dizer que o número não foi cadastrado ainda. Um número de telefone igual a 0 termina a consulta;

e)     A classe MenuPrincipal apenas faz a entrada e saída de dados pelo console;

f)      O método sair() deve, obviamente, encerrar a aplicação.

 

(2)   Crie duas classes que executam as operações lógicas E(AND) e OU(OR):



a)     Os dois construtores recebem um array de inteiros ou de booleanos e armazenam o valor da operação na variável resultado;

b)    O método bool retorna o resultado na forma booleana;

c)     O método num retorna o resultado na forma de inteiro ( 0 caso resultado seja igual a  false e 1 caso resultado seja igual a true );

 

 

Exemplo 1:


int[] numeros = {1,0,1}; // Cria um array contendo 0’s e 1’s

E operacao = new E(numeros); // Cria uma instância da classe E que guarda o resultado da operação E sobre o array numeros no atributo resultado da classe, como ( 1 E 0 E 1 ) = false, então o atributo resultado recebe o valor false.

System.out.println(operacao.num()); // Escreve na tela o resultado da operação num como inteiro, ou seja, escreve o valor 0 na tela.



Exemplo 2:


boolean[] mentira = {false}; // Cria um array contendo false

boolean[] meiaverdade = {true,false}; // Cria um array contento true e false

OU or1 = new OR(meiaverdade); // Cria uma instância da classe OU que guarda o resultado da operação OU sobre o array meiaverdade, no caso o resultado da operação é true

OU or2 = new OR(mentira); // Cria uma instância da classe OU que guarda o resultado da operação OU sobre o array mentira, no caso o resultado da operação é false

OU or3 = new OR(new boolean[] {or1 .bool(), or2.bool()} ); // Cria uma instância da classe OU que guarda o resultado da operação OU sobre a chamada do método bool dos objetos or1 e or2, cujo resultado da operação é true

if ( or3.bool() ) system.out.println(“X”); // Faz a verificação do método bool do objeto or3, como é true, imprime X

