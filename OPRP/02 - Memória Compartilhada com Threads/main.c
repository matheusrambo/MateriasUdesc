#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <pthread.h>
#include <omp.h>
#include "matriz.h"
#include "matriz_op.h"

int main(int argc, char *argv[]) {
    FILE* f;
    struct timespec start, end;
    int n = 30, m = 30;
    int qnt = 10;
    int n_threads = 10;
    init_random();

    if(n_threads > qnt){
        n_threads = qnt;
    }

    pthread_t* threads = (pthread_t*) malloc(n_threads * sizeof(pthread_t));

    matrix* m_a = m_alloc(n, m);
    m_reset(m_a, -1);
    f = fopen("A.map","w");
    m_file(m_a, f);
    fclose(f);
    matrix** b_a = divide(m_a, 1, qnt);
    matrix* m_b = m_alloc(m, n);
    m_reset(m_b, -1);
    f = fopen("B.map","w");
    m_file(m_b, f);
    fclose(f);
    matrix** b_b = divide(m_b, 0, qnt);
    matrix* m_c;

    printf("Soma Sequencial\n");

    clock_gettime(CLOCK_REALTIME, &start);
    m_c = m_plus(m_a, m_a);

    clock_gettime(CLOCK_REALTIME, &end);
    long seconds = end.tv_sec - start.tv_sec;
    long ns = end.tv_nsec - start.tv_nsec;
    if (start.tv_nsec > end.tv_nsec) { // clock underflow
	    --seconds;
	    ns += 1000000000;
    }
    printf("Demorou: %f\n", (double)seconds + (double)ns/(double)1000000000);

	f = fopen("soma_seq.result","w");
    m_file(m_c, f);
    fclose(f);
    m_free(m_c);

    printf("\nMultiplicação Sequencial:\n");

    clock_gettime(CLOCK_REALTIME, &start);
    m_c = m_mult(m_a, m_b);

    clock_gettime(CLOCK_REALTIME, &end);
    seconds = end.tv_sec - start.tv_sec;
    ns = end.tv_nsec - start.tv_nsec;
    if (start.tv_nsec > end.tv_nsec) { // clock underflow
	    --seconds;
	    ns += 1000000000;
    }
    printf("Demorou: %f\n", (double)seconds + (double)ns/(double)1000000000);
    f = fopen("mult_seq.result","w");
    m_file(m_c, f);
    fclose(f);
    m_free(m_c);

    printf("\nMultiplicaçãot bloco:\n");

    clock_gettime(CLOCK_REALTIME, &start);
    m_c = b_mult(b_a, b_b, qnt);

    clock_gettime(CLOCK_REALTIME, &end);
    seconds = end.tv_sec - start.tv_sec;
    ns = end.tv_nsec - start.tv_nsec;
    if (start.tv_nsec > end.tv_nsec) { // clock underflow
	    --seconds;
	    ns += 1000000000;
    }
    printf("Demorou: %f\n", (double)seconds + (double)ns/(double)1000000000);

    f = fopen("mult_bloco.result","w");
    m_file(m_c, f);
    fclose(f);
    m_free(m_c);
    for(int i = 0; i < qnt; ++i) {
        m_free(b_a[i]);
        m_free(b_b[i]);
    }

    printf("\nMultiplicação thread:\n");
    th_args** args = t_alloc(m_a, m_b, qnt, n_threads);

    matrix** t_plus = (matrix**) malloc(n_threads * sizeof(matrix*));
    for(int i = 0; i < n_threads; ++i) {
        t_plus[i] = (matrix*) malloc(sizeof(matrix));
    }

    clock_gettime(CLOCK_REALTIME, &start);

    for(int i = 0; i < n_threads; ++i){
        pthread_create(&threads[i], NULL, th_mult, (void*)args[i]);
    }
    for (int i = 0; i < n_threads; i++)
    {
        pthread_join(threads[i], NULL);
        t_plus[i] = args[i]->b_c;
    }
    m_c = b_plus(t_plus, n_threads);
    clock_gettime(CLOCK_REALTIME, &end);
    seconds = end.tv_sec - start.tv_sec;
    ns = end.tv_nsec - start.tv_nsec;
    if (start.tv_nsec > end.tv_nsec) { // clock underflow
	    --seconds;
	    ns += 1000000000;
    }
    printf("Demorou: %f\n", (double)seconds + (double)ns/(double)1000000000);
    f = fopen("mult_thread.result","w");
    m_file(m_c, f);
    fclose(f);
    m_free(m_c);
    for(int i = 0; i < n_threads; ++i){
        m_free(t_plus[i]);
        for(int j = 0; j < args[i]->qnt; ++j){
            m_free(args[i]->b_a[j]);
            m_free(args[i]->b_b[j]);
        }
    }


    printf("\nMultiplicação OpenMP:\n");
    args = t_alloc(m_a, m_b, qnt, n_threads);

    t_plus = (matrix**) malloc(n_threads * sizeof(matrix*));
    for(int i = 0; i < n_threads; ++i) {
        t_plus[i] = (matrix*) malloc(sizeof(matrix));
    }

    clock_gettime(CLOCK_REALTIME, &start);
    int i;
    #pragma omp parallel for private(i)
    for(i = 0; i < n_threads; ++i){
        th_mult((void*)args[i]);
        t_plus[i] = args[i]->b_c;
    }
    m_c = b_plus(t_plus, n_threads);
    clock_gettime(CLOCK_REALTIME, &end);
    seconds = end.tv_sec - start.tv_sec;
    ns = end.tv_nsec - start.tv_nsec;
    if (start.tv_nsec > end.tv_nsec) { // clock underflow
	    --seconds;
	    ns += 1000000000;
    }
    printf("Demorou: %f\n", (double)seconds + (double)ns/(double)1000000000);
    f = fopen("mult_omp.result","w");
    m_file(m_c, f);
    fclose(f);
    m_free(m_c);
    m_free(m_a);
    m_free(m_b);

    return 0;
}
