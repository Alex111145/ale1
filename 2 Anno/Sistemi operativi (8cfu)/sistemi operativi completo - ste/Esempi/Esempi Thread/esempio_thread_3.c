#include <pthread.h>  /* Piccola modifica: definiamo una variabile x  */
#include <stdio.h>       /* che viene condivisa tra i vari thread */  
#include <stdlib.h>    
int x;  /* il main e tutti i thread condividono x */

/* ATTENZIONE: questo programma serve solo a convincere che con i thread si
               può condividere memoria (la variabile x in questo caso).
               Eseguendo questo programma, i risultati possono però essere 
               sorprendenti, causa RACE CONDITION, che saranno esaminate nel capitolo 6.
 */

void *PrintHello(void *threadid){
   long tid = (long)threadid;
   printf("Ciao, sono la thread numero #%ld!\n", tid);
   printf("x vale %d\n", x);
   x=x+1; 
   pthread_exit(NULL);
}

int main( ){
   pthread_t th[5];  
   long t;
   x=10;
   printf("sono il main, x vale %d\n", x);
   for(t=0; t<5; t++){
      printf("Creo la thread numero %ld\n", t);
      pthread_create(&th[t], NULL, PrintHello, (void *)t);
   }
   pthread_exit(NULL);
}

