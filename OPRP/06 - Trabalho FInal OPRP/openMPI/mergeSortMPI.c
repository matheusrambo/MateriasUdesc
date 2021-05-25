#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <string.h>
#include <limits.h>

void conferePotencia(int id, int numeroProcessos);
void pegarDados(int argc, char* argv[], int id, int numeroDoProcesso, int* tamanhoDoArray);
void preencherArray(int array[], int tamanhoDoArray, int id);
void printarArray(int id, char arrayName[], int array[], int tamanhoDoArray);
int compararValores(const void* a_p, const void* b_p);

int* merge(int metade_a[], int metade_b[], int resultadoMerge[], int size);
int* mergeSort(int height, int id, int localArray[], int size, MPI_Comm comm, int globalArray[]);


void conferePotencia(int id, int numeroProcessos){
	int potencia;
	potencia = (numeroProcessos != 0) && ((numeroProcessos & (numeroProcessos - 1)) == 0);
	if (!potencia) {
		if (id == 0) printf("Número de processos tem que ser potência de 2 \n");
		MPI_Finalize();
		exit(-1);
	}
}

void pegarDados(int argc, char* argv[], int id, int numeroDoProcesso, int* tamanhoDoArray){
    if (id == 0){
        if (id % 2 != 0){
			fprintf(stderr, "mpirun -n <p> %s <size of array> \n", argv[0]);
            fflush(stderr);
            *tamanhoDoArray = -1;
        } else if (argc != 2){
            fprintf(stderr, "mpirun -n <p> %s <size of array> \n", argv[0]);
            fflush(stderr);
            *tamanhoDoArray = -1;
        } else if ((atoi(argv[1])) % numeroDoProcesso != 0) {
		    fprintf(stderr, "O tamanho do array tem que ser divisível pelo número de processos\n");
            fflush(stderr);
            *tamanhoDoArray = -1;
		} else {
            *tamanhoDoArray = atoi(argv[1]); //string p inteiro
        }
    }
    MPI_Bcast(tamanhoDoArray, 1, MPI_INT, 0, MPI_COMM_WORLD);
    if (*tamanhoDoArray <= 0) {
        MPI_Finalize();
        exit(-1);
    }
}


void preencherArray(int array[], int tamanhoDoArray, int id) {
	int i;
	srand(id + time(0));
	for (i = 0; i < tamanhoDoArray; i++) {
		array[i] = rand() % 100; 
	}
}


void printarArray(int id, char arrayName[], int array[], int tamanhoDoArray) {
    int i=0;
    printf("####### Valores do Array #######\n");
    printf(" - %s", arrayName);
    for (i=0; i<tamanhoDoArray; i++) {
        printf(" %d", array[i]);
    }
    printf("\n");
}

int compararValores(const void* a_p, const void* b_p) {
    int a = *((int*)a_p);
    int b = *((int*)b_p);
    if (a < b){
        return -1;
    } else if(a == b){
        return 0;
    } else {
        return 1;
    }
}

int* merge(int metade_a[], int metade_b[], int resultadoMerge[], int size){
    int ai, bi, ci;
    ai = bi = ci = 0;
    while ((ai < size) && (bi < size)){
        if (metade_a[ai] <= metade_b[bi]){
			resultadoMerge[ci] = metade_a[ai]; //fazendo merge ordenado, se a metade a for menor é ela que vai ser adiciona ao vetor final
			ai++;
		} else {
			resultadoMerge[ci] = metade_b[bi]; //se n for e a metade_b que dá o valor para o vetor resultante
			bi++;
		}
			ci++;
	}
	if (ai >= size){
        while (bi < size) {
			resultadoMerge[ci] = metade_b[bi];
			bi++; ci++;
		}
	}
	if (bi >= size){
		while (ai < size) {
			resultadoMerge[ci] = metade_a[ai];
			ai++; ci++;
		}
	}
	return resultadoMerge;
}

int* mergeSort(int height, int id, int localArray[], int size, MPI_Comm comm, int globalArray[]){
    int parent, rightChild, myHeight;
    int *metade_a, *metade_b, *resultadoMerge;

    myHeight = 0;
    qsort(localArray, size, sizeof(int), compararValores);
    metade_a = localArray;
	
    while (myHeight < height) {
        parent = (id & (~(1 << myHeight)));

        if (parent == id) {
		    rightChild = (id | (1 << myHeight));

  		    
  		    metade_b = (int*) malloc (size * sizeof(int));
  		    MPI_Recv(metade_b, size, MPI_INT, rightChild, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
  		    resultadoMerge = (int*) malloc (size * 2 * sizeof(int));
  		    resultadoMerge = merge(metade_a, metade_b, resultadoMerge, size);
            metade_a = resultadoMerge;
			size = size * 2;
			
			free(metade_b); 
			resultadoMerge = NULL;

            myHeight++;

        } else {
              MPI_Send(metade_a, size, MPI_INT, parent, 0, MPI_COMM_WORLD);
              if(myHeight != 0) free(metade_a);  
              myHeight = height;
        }
    }

    if(id == 0){
		globalArray = metade_a;
	}
	return globalArray;
}


int main(int argc, char** argv) {
    int numeroDoProcesso, id, globaltamanhoDoArray, localtamanhoDoArray, height;
    int *localArray, *globalArray;
    double startTime, localTime, totalTime;
    double zeroStartTime, zeroTotalTime, processStartTime, processTotalTime;;
    int length = -1;
    char myHostName[MPI_MAX_PROCESSOR_NAME];

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &numeroDoProcesso);
    MPI_Comm_rank(MPI_COMM_WORLD, &id);
    MPI_Get_processor_name (myHostName, &length); 
    conferePotencia(id, numeroDoProcesso);
    pegarDados(argc, argv, id, numeroDoProcesso, &globaltamanhoDoArray);

    height = log2(numeroDoProcesso);

    if (id==0){
		globalArray = (int*) malloc (globaltamanhoDoArray * sizeof(int));
		preencherArray(globalArray, globaltamanhoDoArray, id);
		printarArray(id, "Array desordenado: ", globalArray, globaltamanhoDoArray);
	}
	
    localtamanhoDoArray = globaltamanhoDoArray / numeroDoProcesso;
    localArray = (int*) malloc (localtamanhoDoArray * sizeof(int));
    MPI_Scatter(globalArray, localtamanhoDoArray, MPI_INT, localArray, localtamanhoDoArray, MPI_INT, 0, MPI_COMM_WORLD);
    
    startTime = MPI_Wtime();

    if (id == 0) {
		zeroStartTime = MPI_Wtime();
		globalArray = mergeSort(height, id, localArray, localtamanhoDoArray, MPI_COMM_WORLD, globalArray);
		zeroTotalTime = MPI_Wtime() - zeroStartTime;
		printf("Processo #%d de %d no %s levou %f segundos \n", 
			id, numeroDoProcesso, myHostName, zeroTotalTime);
	}
	else {
		processStartTime = MPI_Wtime();
	        mergeSort(height, id, localArray, localtamanhoDoArray, MPI_COMM_WORLD, NULL);
		processTotalTime = MPI_Wtime() - processStartTime;
		printf("Processo #%d de %d no %s levou %f segundos \n", id, numeroDoProcesso, myHostName, processTotalTime);
	}

    localTime = MPI_Wtime() - startTime;
    MPI_Reduce(&localTime, &totalTime, 1, MPI_DOUBLE,MPI_MAX, 0, MPI_COMM_WORLD);

    if (id == 0){
		printarArray(0, "Array ordenado:", globalArray, globaltamanhoDoArray);
		printf("Tamanho array=%d \n Tempo necessário: %f segundos \n", globaltamanhoDoArray,totalTime);
		free(globalArray);
	}

    free(localArray);  
    MPI_Finalize();
    return 0;
}