#include <stdio.h>
#include <stdlib.h>

void hm(int mnts, int *h, int *m);

int main(void){
	int mnts, h, m;
	scanf("%i", &mnts);
	hm(mnts, &h, &m);
	printf("%i h %i m\n", h, m);
	return 0;
}
void hm(int mnts, int *h, int *m){
	int horas, minutos;
	horas = mnts/60;
	minutos= mnts%60;
	*h = horas;
	*m = minutos;
}