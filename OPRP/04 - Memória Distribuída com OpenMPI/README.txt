Apenas matrizes quadradas vão rodar aqui!
Compilar: make
executar após compilar: 
mpirun -np <num processos> ./a.out <matrizA> <matrizB> <divisor>
exemplo: mpirun -np 1 ./main 1000x1000-mat.map 1000x1000-mat.map 1
Criar novas matrizes:
cd gerarMatrizes
Compilar: make
Criar matrizeas: ./gerarMatriz 1000 1000,
Leva os arquivos para o diretório
Conferir se matrizes são iguais: pode olhar no terminal ou compara nos arquivos tipo0.map, tipo1.map1...