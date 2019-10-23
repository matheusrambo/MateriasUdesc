Descrição geral: Nesse exercício deve ser desenvolvido um programa em Java, orientado a objetos e dividido em camadas, que gerencie uma loja de venda de veículos. Esse programa deve ser capaz de realizar vendas de motos, carros, caminhonetes, caminhões e ônibus. Note que cada tipo de veículo tem atributos comuns, como cor, ano de fabricação e número de chassi, mas também tem atributos específicos, como capacidade máxima de carga, número de assentos, tipo de carroceria, etc. Portanto, deve ser utilizado o conceito de herança para se implementar este programa.

Segue abaixo a lista mínima de classes que esse programa deve conter:

Veículo

Carro

Caminhonete

Caminhão

Ônibus

Moto

Revenda (classe que conterá as funcionalidades envolvidas com a venda de veículos)

Sistema (classe que conterá a interface com o usuário)

 

Segue abaixo a lista de atributos que deve estar presente em alguma das classes apresentadas acima:

- ano de fabricação

- modelo

- número de assentos

- número da placa

- cor

- capacidade máxima de carga

- número de chassi

- Quilometragem

- cilindradas

- marca

- tipo de carroceria

- potência em cv

- número de eixos

- número de portas

- número de marchas

- valor do veículo

- data de entrada na revenda

- data de venda

- vendido (“sim / não”)

- cabine dupla

- partida elétrica

 

A escolha da classe que terá determinado atributo deve ser compatível com a hierarquia de classes definida pelo aluno.

Podem ser criadas outras classes, entretanto, isso fica a critério do aluno. A forma como a hierarquia de herança será implementada também fica a critério do aluno. 

O programa deverá ser capaz de efetuar vendas de veículos através do número da placa do mesmo. Isto significa que o mesmo conterá uma lista de veículos cadastrados. Para que a venda ocorra, serão necessárias as seguintes funcionalidades:

·       Cadastrar veículo (um cadastro para cada tipo de veículo);

·       Listar veículos não vendidos;

·       Mostrar detalhes de um veículo informando o número de sua placa;

·       Vender um veículo ainda não vendido (através da informação da placa do mesmo).