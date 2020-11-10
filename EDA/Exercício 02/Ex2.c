#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>




struct STRUCT_FILA
 {
   int  front ;
   int  rear ;
   int  capacidade ;
   int * entrada ;
};
// e ainda
typedef  struct STRUCT_FILA  fila ;

// OS PROTOTIPOS FUNCOES e USO
fila * cria_fila (  int CAPACIDADE );
bool  fila_cheia  ( fila * F);
bool  fila_vazia  ( fila * F);
int   tamanho_fila ( fila * F );
void  chegada (int x, fila * F); // entra ... char
char  partida ( fila * F );        // .. sai char
void  destroi_fila( fila *F);
void  mostra_fila( fila *F );

int main(void)
{
  int tamanho, numeros;
  scanf("%i %i", &tamanho, &numeros);

  fila * line =  cria_fila(tamanho);
  int i =0;
  int x;

  for (i= 0; i < numeros; i ++){
    scanf("%i", &x);
    chegada(x, line);
  }
  scanf("%i %i", &tamanho, &numeros);
  fila * line2 =  cria_fila(tamanho);
  for (i= 0; i < numeros; i ++){
    scanf("%i", &x);
    chegada(x, line2);
  }
  fila * line3 = cria_fila(tamanho * 2);
  for(i = 0; i < numeros; i++){
    x = partida(line);
    chegada(x, line3);
    x = partida(line2);
    chegada(x, line3);
  }
  mostra_fila(line3);

  return 0;
}

fila * cria_fila ( int C )
{
	fila * FILA = malloc(sizeof (struct STRUCT_FILA));
	FILA -> capacidade = C ;
	FILA -> front = -1 ;
	FILA -> rear = -1 ;
	FILA -> entrada = malloc(FILA -> capacidade * sizeof(int) );
	if(FILA -> entrada == NULL)
	{
	  printf("\n Problema ao criar a FILA");
	  getchar();
	  exit(0);
	}
	return(FILA);
}
bool fila_vazia  ( fila * F )
{
  return (F -> front <  0);
}
bool fila_cheia  ( fila * F )
{
	return ((F -> rear + 1) % (F -> capacidade) == (F -> front));
}

int tamanho_fila ( fila * F )
{
   int tam;
   for(tam = F ->front ; tam != F -> rear ; tam = (tam+1) % F->capacidade );
   return ( tam + 1) ;
 }

 void chegada ( int x, fila * F )
{
	if ( fila_cheia ( F ))
	/* voltando 1 ou true se estiver estah cheia */
	{
	  printf("\n A FILA ESTAH CHEIA ");
	  getchar();
      //exit();
	}
  /* NOVO FINAL DA FILA: incrementa fila (+1) e modulo C */
  /* Incrementa ao final da fila ... sentido:
      0:1:2... :N-2:N-1:0:1:2.... */
   else
	{
	 F -> rear =  ((F -> rear) + 1) % F -> capacidade  ;
	 F -> entrada [ F -> rear ] = x;
	 if( F-> front == -1) // so na 1a. VEZ
	 	F-> front = F -> rear ; // FILA ESTAVA VAZIA

     // comente se for caso
	 //printf("\nFRONT: %i | POS: %d || REAR: %i | POS: %d",
	// 	F->entrada[F->front], F->front,
	 	//F->entrada[F->rear], F->rear);

   }
	 return;
  } /* fim da funcao de chegada */
  // retira o primeiro da fila
  char  partida ( fila * F )
    {
  	char retorno;
  	if ( fila_vazia ( F ) ) /*  1 ou true se estiver estah vazia */
  	{ printf("\n A FILA ESTAH VAZIA ");
  	  getchar();
  	  return '_';  // vazio
  	}
  	else
  	{
  	 retorno = F -> entrada [ F -> front ];
      // imprimir aqui para pegar todo historico
  	// comente se for caso
  	 //printf("\nFRONT: %i : %d || REAR: %i : %d\n",
  	 	//F->entrada[F->front], F->front,
  	 	//F->entrada[F->rear], F->rear);
          // fila SO tinha UM elemento
          if((F -> front) == (F -> rear))
      	 (F -> front) = (F -> rear) = -1;
          else
          {
  	   /* Pega o proximo da fila ... sentido de 0:1:2... :N-2:N-1:0:1:2.... */
  	    F -> front =  ((F -> front) + 1) % F -> capacidade  ;
  	    }
        return retorno;
      }
    }  /* fim da funcao chegada */

  void destroi_fila( fila *F)
    {
    	if(F) {
         if( F -> entrada )
         	free( F -> entrada ); // uma parte
         free( F ); // no inteiro
         }
       else
          {
       printf("\n FILA NAO LIBERADA\n" );
       }
      printf("\n FILA LIBERADA OK\n" );
    }


  void mostra_fila( fila *F )
  {
      int i;
      if( fila_vazia(F) )
          printf("\n Fila Vazia\n");
      else
      {//puts("\n FILA SE ENCONTRA ASSIM .....");
       //printf("\n Front ou cabeca : %d ", F -> front);
       //printf("\t Rear ou ultimo :  %d ", F-> rear);
       //printf("\n CONTEUDO da FILA : ");
       for(i = F ->front ; i != F -> rear ; i = (i+1) % F->capacidade )
       {
         printf("%i ", F->entrada[i]);
       }
       printf("%i \n", F->entrada[i] ); // ultimo

      }
  }
