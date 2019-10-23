Descrição geral: Neste exercício deve ser desenvolvido um programa, orientado a objetos, em Java que gerencie os empréstimos e devoluções de uma biblioteca. Neste programa deve ser utilizado o conceito de interface. Segue a descrição das principais classes:

Interface ItemDeBiblioteca

O método estaEmprestado() retorna o valor do atributo estaEmprestado;
O método empresta() seta o atributo estaEmprestado com true, se o item ainda não estiver emprestado, senão retorna false;
O método devolve() seta o atributo estaEmprestado com false;
O método localização() retorna o valor do atributo localização.
Classe Biblioteca

O método catalogarLivro() adiciona um Livro ao array de itens da Biblioteca;
O método catalogarCD() adiciona um CD ao array de itens da Biblioteca;
O método emprestarItem() chama o método empresta() de um itemDeBiblioteca, dependendo do titulo passado como parâmetro;
O método devolverItem() chama o método devolve() de um itemDeBiblioteca, dependendo do titulo passado como parâmetro;
O método mostrarLocalizacao() chama o método localizacao() de um itemDeBiblioteca, dependendo do titulo passado como parâmetro;
O método mostrarDetalhesItem() mostra todos os detalhes de um Livro ou de um CD, dependendo do ItemDeBiblioteca passado como parâmetro.
Segue abaixo o diagrama de classes UML.

 Diagrama de Classes