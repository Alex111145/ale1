

/* Esempio 1 "raffinato": i thread perdono tempo */
/* più probabile vedere risultati diversi in */
/* esecuzioni diverse */


#include <pthread.h> 
#include <stdio.h>     
#include <stdlib.h>    
int x; /* x variabile globale condivisa */  

void * f1(void *threadid){
   for(long i=0;i<100000000;i++){}
   x=100; /* modifica di x */
   pthread_exit(NULL);
}

void * f2(void *threadid){
   for(long i=0;i<100000000;i++){}
   int y = x+5; /* lettura di x */
   printf("y vale %d\n",y);
   pthread_exit(NULL);
}

int main( ){ /* x è nel write_set */
   pthread_t t1,t2;  
   x=10; /* modifica di x */
   pthread_create(&t1, NULL, f1, (void *)t1);/* x è nel write_set */
   pthread_create(&t2, NULL, f2, (void *)t2); /* x è nel read_set */
   pthread_exit(NULL);
}

