Descrição geral: Crie uma estrutura hierárquica que contenha as classes Veículo, Bicicleta e Automóvel, com as seguintes especificações:

·       Classe Veiculo: Classe abstrata que contém os seguintes atributos: marca, modelo, ano e cor.

·       Classe Automóvel: Filha da classe Veículo que contém os seguintes atributos: combustível, potência, nº de portas;

·       Classe Bicicleta: Filha da classe Veículo que contém os seguintes atributos: nº de marchas, tamanho do quadro, tipo de freio.

 

A classe veículo deve ter métodos abstratos com as seguintes assinaturas:

listarVerificacoes() imprime na tela “verificar no automóvel (ou bicicleta): pneu, suspensão, motor (, guidão, etc.)”;
ajustar() imprime na tela “todos os itens do automóvel (ou bicicleta) foram verificados com sucesso”;
limpar() imprime na tela “o automóvel (ou bicicleta) já foi lavados e encerado”;
 

Note que os métodos acima devem ser implementados de forma específica para o Automóvel e a Bicicleta. Assim, apenas itens a serem verificados em automóveis devem estar presentes no método listarVerificacoes() do Automóvel, assim como no da Bicicleta.

Acrescentar na classe Automóvel o método trocarOleo() que retorna para impressão na tela, “o óleo foi trocado com sucesso”.

 

Para desenvolver a classe AutomovelApp, que conterá o método main() com um array de veículos, é necessário criar também a classe Oficina que terá dois métodos:

proximo() que retorna aleatoriamente um objeto do tipo bicicleta ou automóvel;
manutencao(Veiculo v) que recebe como parâmetro um veiculo e chama os métodos:
listarVerificacoes();
ajustar();
limpar();
se o veiculo for Automóvel deve também chamar o método trocarOleo().
 

Método para retornar um veículo aleatório:

public class Oficina {

Random r = new Random();

public Veiculo proximo() {

Veiculo v;

int code = r.nextInt();

if (code%2 == 0)

v = new Automovel();

else

v = new Bicicleta();

return v;

}

}