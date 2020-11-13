#include <stdio.h>
#include <stdlib.h>

void f_MAIOR_MENOR(int *v, int *min, int *max);
int main(void){
	int i, x, min = 0, max=0;
	int *v = NULL;
	scanf("%i", &i);
	v=(int*)malloc(sizeof(int)*i);

	for(x=0; x<i; x++){
		scanf("%d", &v[x]);
	}
	min = i;
	f_MAIOR_MENOR(v, &min, &max);
	printf("%i %i\n", min, max);

	return 0;
}
void f_MAIOR_MENOR(int *v, int *min, int *max){
	int i, k;
	i = *min;
	*min = 0;
	for (k = 0; k < i; k++){
		if(k == 0){
			*max = v[k];
			*min = v[k];
		}
		if(v[k] > *max){
			*max = v[k];
		}
		if(v[k] < *min){
			*min = v[k];
		}
				
	}

}
