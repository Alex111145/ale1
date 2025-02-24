
/* esempio di thread che condividono una variabile x */
/* e possono dar luogo a race condition */
/* Nota: e' difficile che la race condition si verifichi */
/* in questo esempio. Più facile con il file */
/* ex_thread_share_x_race_more_dangerous.c */

#include <pthread.h> /* Esempio 2: variabile condivisa da 3 thread */
#include <stdio.h>
#include <stdlib.h>    
int x = 100; /* x variabile globale variabile condivisa */  

void * f1(void *threadid){
   x=x+5; /* modifica di x */
   pthread_exit(NULL);
}

void * f2(void *threadid){
   x = x+10; /* modifica di x */
   pthread_exit(NULL);
}

int main( ){ /* x è nel read_set */
   pthread_t t1, t2;  
   pthread_create(&t1, NULL, f1, (void *)t1); /* x è nel write_set */
   pthread_create(&t2, NULL, f2, (void *)t2); /* x è nel write_set */ 
   pthread_join(t1,NULL); /* attendo che termini il primo thread */
   pthread_join(t2,NULL); /* attendo che termini il secondo thread */
   printf("x vale %d\n",x); /* lettura di x */
}

