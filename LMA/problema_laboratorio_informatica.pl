% Trabalho - Laboratório de informática
% URL do problema - http://rachacuca.com.br/logica/problemas/laboratorio-de-informatica/
% Alunos - Caio Ledoux Villwock e Matheus Rambo da Roza


mostrar_lab :-
	statistics(cputime, T0),
	laboratorio([LABORATORIO1, LABORATORIO2, LABORATORIO3, LABORATORIO4, LABORATORIO5]),
	writeln("LABORATORIO 1 ":LABORATORIO1),
	writeln("LABORATORIO 2 ":LABORATORIO2),
	writeln("LABORATORIO 3 ":LABORATORIO3),
	writeln("LABORATORIO 4 ":LABORATORIO4),
	writeln("LABORATORIO 5 ":LABORATORIO5),
	statistics(cputime, T1),
	TEMPO_TOTAL is T1 - T0,
	format("\nO tempo de execução é: ~10f sec", TEMPO_TOTAL),
	fail.
mostrar_lab :- true.

%%	EXEMPLO DE SAIDA  %%
%%	?- mostrar_lab.
%%	LABORATORIO 1 : (verde,otavio,agosto,cacapalavras,geografia,limao)
%%	LABORATORIO 2 : (branca,denis,dezembro,tresoumais,matematica,maracuja)
%%	LABORATORIO 3 : (azul,will,janeiro,jogodaforca,biologia,morango)
%%	LABORATORIO 4 : (vermelha,joao,setembro,problogica,historia,uva)
%%	LABORATORIO 5 : (amarela,lenin,maio,cubovermelho,portugues,laranja)

%%	O tempo de execução é: 3.1063679160 sec
%%	true.

laboratorio([(MOCHILA1, NOME1, MES1, JOGO1, MATERIA1, SUCO1),
	     (MOCHILA2, NOME2, MES2, JOGO2, MATERIA2, SUCO2),
	     (MOCHILA3, NOME3, MES3, JOGO3, MATERIA3, SUCO3),
	     (MOCHILA4, NOME4, MES4, JOGO4, MATERIA4, SUCO4),
	     (MOCHILA5, NOME5, MES5, JOGO5, MATERIA5, SUCO5)]) :- 

%%	EXEMPLO DE SAIDA  %%
%% 	laboratorio([LAB1,LAB2,LAB3,LAB4,LAB5]).
%% 	LAB1 = (verde, otavio, agosto, cacapalavras, geografia, limao),
%% 	LAB2 = (branca, denis, dezembro, tresoumais, matematica, maracuja),
%% 	LAB3 = (azul, will, janeiro, jogodaforca, biologia, morango),
%% 	LAB4 = (vermelha, joao, setembro, problogica, historia, uva),
%% 	LAB5 = (amarela, lenin, maio, cubovermelho, portugues, laranja)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% MOCHILA
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

mochila(MOCHILA1),mochila(MOCHILA2),mochila(MOCHILA3),mochila(MOCHILA4),mochila(MOCHILA5),
alldifferent([MOCHILA1,MOCHILA2,MOCHILA3,MOCHILA4,MOCHILA5]),

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% NOME
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

nome(NOME1),nome(NOME2),nome(NOME3),nome(NOME4),nome(NOME5),
alldifferent([NOME1,NOME2,NOME3,NOME4,NOME5]),

% 22 - Lenin está na quinta posição.
NOME5 = lenin,

% 23 - Otávio está em uma das pontas.
NOME1 = otavio,

% 5 - O garoto da mochila Branca está exatamente à esquerda de Will.
(
	(NOME2 == will, MOCHILA1 == branca);
	(NOME3 == will, MOCHILA2 == branca);
	(NOME4 == will, MOCHILA3 == branca)
),


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% MES
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

mes(MES1),mes(MES2),mes(MES3),mes(MES4),mes(MES5),
alldifferent([MES1,MES2,MES3,MES4,MES5]),

% 3 - O garoto da mochila Azul está em algum lugar à esquerda de quem nasceu em Maio.

(
	(MES2 == maio, MOCHILA1 == azul);
	(MES3 == maio, (MOCHILA1 == azul; MOCHILA2 == azul));
	(MES4 == maio, (MOCHILA1 == azul; MOCHILA2 == azul; MOCHILA3 == azul));
	(MES5 == maio, (MOCHILA1 == azul; MOCHILA2 == azul; MOCHILA3 == azul; MOCHILA4 == azul))
),

% 13 - O menino que nasceu em Janeiro está ao lado de quem nasceu em Setembro.

(
	(MES1 == setembro, MES2 == janeiro);
	(MES2 == setembro, (MES1 == janeiro; MES3 == janeiro));
	(MES3 == setembro, (MES2 == janeiro; MES4 == janeiro));
	(MES4 == setembro, (MES3 == janeiro; MES5 == janeiro));
	(MES5 == setembro, MES4 == janeiro)
),

% 17 - O dono da mochila Azul nasceu em Janeiro.

(
	(MES1 == janeiro, MOCHILA1 == azul);
	(MES2 == janeiro, MOCHILA2 == azul);
	(MES3 == janeiro, MOCHILA3 == azul);
	(MES4 == janeiro, MOCHILA4 == azul);
	(MES5 == janeiro, MOCHILA5 == azul)
),

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% JOGO
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

jogo(JOGO1),jogo(JOGO2),jogo(JOGO3),jogo(JOGO4),jogo(JOGO5),
alldifferent([JOGO1,JOGO2,JOGO3,JOGO4,JOGO5]),

% 24 - Na terceira posição está o menino que gosta do Jogo da Forca.

JOGO3 == jogodaforca,

% 8  - O garoto que gosta do Jogo da Forca está ao lado do que gosta do 3 ou Mais.

(
	JOGO2 == tresoumais;
	JOGO4 == tresoumais
),

% 4 - Will está ao lado do menino que gosta de Problemas de Lógica.

(
	(JOGO1 == problogica, NOME2 == will);
	(JOGO2 == problogica, NOME3 == will);
	(JOGO4 == problogica, NOME3 == will);
	(JOGO5 == problogica, NOME4 == will)
),

% 10 - Em uma das pontas está o menino que adora jogar Cubo Vermelho.

(
	JOGO1 == cubovermelho;
	JOGO5 == cubovermelho
),

% 11 - Quem gosta do Jogo da Forca está ao lado do dono da mochila Vermelha.

(
	MOCHILA2 == vermelha;
	MOCHILA4 == vermelha
),

% 16 - Quem curte Problemas de Lógica está ao lado do menino da mochila Amarela.

(
	(MOCHILA1 == amarela, JOGO2 == problogica);
	(MOCHILA2 == amarela, JOGO1 == problogica);
	(MOCHILA3 == amarela, (JOGO2 == problogica; JOGO4 == problogica));
	(MOCHILA4 == amarela, (JOGO3 == problogica; JOGO5 == problogica));
	(MOCHILA5 == amarela, JOGO4 == problogica)
),

% 19 - O garoto que nasceu em Setembro está ao lado de quem gosta de jogar Cubo Vermelho.

(
	(JOGO1 == cubovermelho, MES2 == setembro);
	(JOGO5 == cubovermelho, MES4 == setembro)
),

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% MATERIA
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

materia(MATERIA1),materia(MATERIA2),materia(MATERIA3),materia(MATERIA4),materia(MATERIA5),
alldifferent([MATERIA1,MATERIA2,MATERIA3,MATERIA4,MATERIA5]),

% 2 - João gosta de história.

(
	(NOME2 == joao, MATERIA2 == historia);
	(NOME3 == joao, MATERIA3 == historia);
	(NOME4 == joao, MATERIA4 == historia)
),

% 15 - O menino que gosta de Matemática nasceu em Dezembro.

(
	(MES1 == dezembro, MATERIA1 == matematica);
	(MES2 == dezembro, MATERIA2 == matematica);
	(MES3 == dezembro, MATERIA3 == matematica);
	(MES4 == dezembro, MATERIA4 == matematica);
	(MES5 == dezembro, MATERIA5	 == matematica)
),

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% SUCO
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

suco(SUCO1),suco(SUCO2),suco(SUCO3),suco(SUCO4),suco(SUCO5),
alldifferent([SUCO1,SUCO2,SUCO3,SUCO4,SUCO5]),

% 6 - Na terceira posição está quem gosta de suco de Morango.

SUCO3 == morango,

% 20 - Na primeira posição está quem gosta de suco de Limão.

SUCO1 == limao,

% 12 - O garoto que gosta de Biologia gosta de suco de Morango.

MATERIA3 == biologia,

% 14 - Quem gosta de suco de Uva está exatamente à esquerda de quem gosta de Português.

MATERIA5 == portugues,
SUCO4 == uva,

% 1 - Quem nasceu em Setembro está ao lado de quem gosta de suco de Laranja.

(
	(SUCO2 == laranja, (MES1 == setembro; MES3 == setembro));
	(SUCO5 == laranja, MES4 == setembro)
),

% 7 - Quem gosta de suco de Uva gosta de Problemas de Lógica.

JOGO4 == problogica,

% 9 - O menino que gosta de suco de Uva está em algum lugar à direita do garoto da mochila Azul.

((MOCHILA1 == azul;MOCHILA2 == azul;MOCHILA3 == azul), SUCO4 == uva),


% 21 - Quem gosta de Matemática gosta também de suco de Maracujá.

MATERIA2 == matematica,
SUCO2 == maracuja.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Biblioteca
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
mochila(amarela).
mochila(azul).
mochila(branca).
mochila(verde).
mochila(vermelha).

nome(denis).
nome(joao).
nome(lenin).
nome(otavio).
nome(will).

mes(agosto).
mes(dezembro).
mes(janeiro).
mes(maio).
mes(setembro).

jogo(tresoumais).
jogo(cacapalavras).
jogo(cubovermelho).
jogo(jogodaforca).
jogo(problogica).

materia(biologia).
materia(geografia).
materia(historia).
materia(matematica).
materia(portugues).

suco(laranja).
suco(limao).
suco(maracuja).
suco(morango).
suco(uva).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% DEFINICAO DE ALLDIFERENT
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
alldifferent([]).
alldifferent([H|T]) :-
	not(member(H,T)),
	alldifferent(T).