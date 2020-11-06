1) Atualização perdida, Leitura suja e Sumário incorreto nesta ordem de cima para baixo.

2) 
a)	X1 e X2 são spider-man.

b)	X1 é spider-man.
	X2 e X3 são null.
	X4 é 100.
	X5 é peter park.
3)
                      		Leitura   |  Suja Leitura não repetível |   Fantasma   |
    READ_UNCOMMITED		|      S      |              S              |       S      |
    READ_COMMITED		|      N      |              S              |       S      |
    REPETABLE_READ		|      N      |              N              |       S      |
    SERIALIZABLE		|      N      |              N              |       N      |

4) Resposta letra C.
5) Resposta letra A.
6) Resposta letra A.