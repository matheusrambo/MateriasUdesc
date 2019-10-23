% Trabalho - Turistas na copa.
% URL do problema - http://rachacuca.com.br/logica/problemas/turistas-na-copa/
% Alunos - Caio Ledoux Villwock e Matheus Rambo da Roza


mostrar_turistas :-
	statistics(cputime, T0),
	turista([TURISTA1, TURISTA2, TURISTA3, TURISTA4, TURISTA5]),
	writeln("TURISTA 1 ":TURISTA1),
	writeln("TURISTA 2 ":TURISTA2),
	writeln("TURISTA 3 ":TURISTA3),
	writeln("TURISTA 4 ":TURISTA4),
	writeln("TURISTA 5 ":TURISTA5),
	statistics(cputime, T1),
	TEMPO_TOTAL is T1 - T0,
	format("\nO tempo de execução é: ~10f sec", TEMPO_TOTAL),
	fail.
mostrar_turistas :- true.

%%	EXEMPLO DE SAIDA  %%
%%	?- mostrar_turistas.
%%	TURISTA 1 : (15,irma,57,espanhol,agua,azul)
%%	TURISTA 2 : (10,esposa,36,frances,cha,verde)
%%	TURISTA 3 : (20,amigo,31,italiano,leite,branca)
%%	TURISTA 4 : (25,filho,45,alemao,cerveja,amarela)
%%	TURISTA 5 : (30,namorada,28,croata,cafe,vermelha)

%%	O tempo de execução é: 0.1057824350 sec
%%	true.

turista([(DIAS1, COMPANHIA1, IDADE1, NACIONALIDADE1, BEBIDA1, CAMISA1),
	     (DIAS2, COMPANHIA2, IDADE2, NACIONALIDADE2, BEBIDA2, CAMISA2),
	     (DIAS3, COMPANHIA3, IDADE3, NACIONALIDADE3, BEBIDA3, CAMISA3),
	     (DIAS4, COMPANHIA4, IDADE4, NACIONALIDADE4, BEBIDA4, CAMISA4),
	     (DIAS5, COMPANHIA5, IDADE5, NACIONALIDADE5, BEBIDA5, CAMISA5)]) :- 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% DIAS
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

dias(DIAS1),dias(DIAS2),dias(DIAS3),dias(DIAS4),dias(DIAS5),
alldifferent([DIAS1,DIAS2,DIAS3,DIAS4,DIAS5]),

% 1 - Na primeira posição está quem ficará 15 dias no Brasil.

DIAS1 == 15,

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% COMPANHIA
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

companhia(COMPANHIA1),companhia(COMPANHIA2),companhia(COMPANHIA3),companhia(COMPANHIA4),companhia(COMPANHIA5),
alldifferent([COMPANHIA1,COMPANHIA2,COMPANHIA3,COMPANHIA4,COMPANHIA5]),

% 8 - O turista do meio está acompanhado do Amigo.

COMPANHIA3 == amigo,

% 15 - Na quinta posição está o turista que veio com a Namorada.

COMPANHIA5 == namorada,

% 16 - Quem está acompanhado da Esposa está ao lado de quem ficará 20 dias no Brasil.

(
	(DIAS2 == 20, COMPANHIA1 == esposa);
	(DIAS3 == 20, (COMPANHIA2 == esposa; COMPANHIA4 == esposa));
	(DIAS5 == 20, COMPANHIA4 == esposa)
),

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% BEBIDA
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

bebida(BEBIDA1),bebida(BEBIDA2),bebida(BEBIDA3),bebida(BEBIDA4),bebida(BEBIDA5),
alldifferent([BEBIDA1,BEBIDA2,BEBIDA3,BEBIDA4,BEBIDA5]),

% 17 - O turista que gosta de Café está na quinta posição.

BEBIDA5 == cafe,

% 14 - Quem gosta de Chá está exatamente à esquerda de quem veio acompanhado do Amigo.

BEBIDA2 == cha,

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% IDADE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

idade(IDADE1),idade(IDADE2),idade(IDADE3),idade(IDADE4),idade(IDADE5),
alldifferent([IDADE1,IDADE2,IDADE3,IDADE4,IDADE5]),

% 5 - O turista de 45 anos está exatamente à direita do turista de 31 anos.

IDADE4 == 45,

% 20 - O turista de 31 anos veio com o Amigo.
	
IDADE3 == 31,

% 12 - O turista de 28 anos está exatamente à direita do turista que ficará 25 dias no Brasil.

DIAS4 == 25,

% 7 - O turista de 36 anos está exatamente à esquerda do turista que gosta de Leite.

BEBIDA3 == leite,
IDADE2 == 36,

% 18 - O turista que veio com a Esposa está exatamente à esquerda de quem gosta de Leite.

COMPANHIA2 == esposa,

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% NACIONALIDADE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

nacionalidade(NACIONALIDADE1),nacionalidade(NACIONALIDADE2),nacionalidade(NACIONALIDADE3),nacionalidade(NACIONALIDADE4),nacionalidade(NACIONALIDADE5),
alldifferent([NACIONALIDADE1,NACIONALIDADE2,NACIONALIDADE3,NACIONALIDADE4,NACIONALIDADE5]),

% 10 - O Italiano está na terceira posição.

NACIONALIDADE3 == italiano,

% 11 - O Alemão está ao lado do turista que passará 20 dias no Brasil.

(
	(DIAS2 == 20, (NACIONALIDADE1 == alemao; NACIONALIDADE3 == alemao));
	(DIAS3 == 20, (NACIONALIDADE2 == alemao; NACIONALIDADE4 == alemao));
	(DIAS4 == 20, (NACIONALIDADE3 == alemao; NACIONALIDADE5 == alemao));
	(DIAS5 == 20, NACIONALIDADE4 == alemao)
),

% 4 - O Espanhol é o turista mais velho.

( 
	(IDADE1 == 57, NACIONALIDADE1 == espanhol);
	(IDADE5 == 57, NACIONALIDADE5 == espanhol)
),

% 3 - O Alemão está acompanhado do Filho.

(
	(NACIONALIDADE1 == alemao, COMPANHIA1 == filho);
	(NACIONALIDADE4 == alemao, COMPANHIA4 == filho)
),
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% CAMISA
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

camisa(CAMISA1),camisa(CAMISA2),camisa(CAMISA3),camisa(CAMISA4),camisa(CAMISA5),
alldifferent([CAMISA1,CAMISA2,CAMISA3,CAMISA4,CAMISA5]),

% 9 - O turista de Azul está ao lado do turista que ficará 10 dias no Brasil.

(
	(DIAS2 == 10, (CAMISA1 == azul; CAMISA3 == azul));
	(DIAS3 == 10, (CAMISA2 == azul; CAMISA4 == azul));
	(DIAS4 == 10, (CAMISA3 == azul; CAMISA5 == azul));
	(DIAS5 == 10, CAMISA4 == azul)
),

% 19 - Os turistas das camisas amarela e vermelha estão lado a lado;

(
	(CAMISA1 == amarela, CAMISA2 == vermelha);
	(CAMISA2 == amarela, CAMISA3 == vermelha);
	(CAMISA3 == amarela, CAMISA4 == vermelha);
	(CAMISA4 == amarela, CAMISA5 == vermelha);

	(CAMISA1 == vermelha, CAMISA2 == amarela);
	(CAMISA2 == vermelha, CAMISA3 == amarela);
	(CAMISA3 == vermelha, CAMISA4 == amarela);
	(CAMISA4 == vermelha, CAMISA5 == amarela)
),

% 6 - O Alemão está exatamente à esquerda do turista de Vermelho.

(
	(CAMISA2 == vermelha, NACIONALIDADE1 == alemao);
	(CAMISA3 == vermelha, NACIONALIDADE2 == alemao);
	(CAMISA5 == vermelha, NACIONALIDADE4 == alemao)
),


% 2 - O turista da camisa Verde está em algum lugar entre quem gosta de água e o croata, nessa ordem;

(
	((CAMISA2 == verde; CAMISA3 == verde), (BEBIDA1 == agua, NACIONALIDADE4 == croata));
	((CAMISA2 == verde; CAMISA3 == verde; CAMISA4 == verde), (BEBIDA1 == agua, NACIONALIDADE5 == croata))
),

% 13 - O turista de Verde está em algum lugar entre o Espanhol e o turista de Branco, nessa ordem.

(
	(CAMISA2 == verde, (NACIONALIDADE1 == espanhol, CAMISA3 == branca));
	((CAMISA2 == verde; CAMISA3 == verde), (NACIONALIDADE1 == espanhol, CAMISA4 == branca));
	((CAMISA2 == verde; CAMISA3 == verde; CAMISA4 == verde), (NACIONALIDADE1 == espanhol, CAMISA5 == branca));
	((CAMISA3 == verde; CAMISA4 == verde), (NACIONALIDADE2 == espanhol, CAMISA5 == branca))
)
.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Biblioteca
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
camisa(amarela).
camisa(azul).
camisa(branca).
camisa(verde).
camisa(vermelha).

nacionalidade(alemao).
nacionalidade(croata).
nacionalidade(espanhol).
nacionalidade(frances).
nacionalidade(italiano).

bebida(agua).
bebida(cafe).
bebida(cha).
bebida(cerveja).
bebida(leite).

dias(10).
dias(15).
dias(20).
dias(25).
dias(30).

idade(28).
idade(31).
idade(36).
idade(45).
idade(57).

companhia(amigo).
companhia(filho).
companhia(irma).
companhia(esposa).
companhia(namorada).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% DEFINICAO DE ALLDIFERENT
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
alldifferent([]).
alldifferent([H|T]) :-
	not(member(H,T)),
	alldifferent(T).