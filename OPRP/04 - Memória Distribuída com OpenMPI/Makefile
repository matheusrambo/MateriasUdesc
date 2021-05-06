# GNU Makefile

CC = mpicc
CCFLAGS = -Wall -O3 -pthread
LDFLAGS = -lm --openmp
TARGET = main help

all: $(TARGET)

main: main.c matriz-operacoesv3.c toolsv3.c
			$(CC) $(CCFLAGS) matriz-operacoesv3.c toolsv3.c main.c -o $@ $(LDFLAGS)

help:
	@echo "./main <arquivo_matrizA> <arquivo_matrizB> <divisor>"
	@echo "make help, make all, make main, make clean, make mapclean"

clean:
	-rm -f main

mapclean:
		-rm -f *.map
