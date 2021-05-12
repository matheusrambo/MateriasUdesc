#include <omp.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc, char *argv[]){

int num_threads, t_id;

#pragma omp parallel private(num_threads, t_id){
  t_id = omp_get_thread_num();
  printf("#### Hello World from thread = %d ####\n", t_id);
  #pragma omp master{
    printf("#### Pragma omp master = %d ####\n", omp_get_thread_num());
  }
  if (t_id == 0){
    num_threads = omp_get_num_threads();
    printf("#### Numero de threads = %d ####\n", num_threads);
    }
  }
}
