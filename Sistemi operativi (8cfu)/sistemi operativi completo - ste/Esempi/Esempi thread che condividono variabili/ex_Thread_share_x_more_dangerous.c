
/* esempio di thread che condividono una variabile x */
/* e possono dar luogo a race condition */

#include <pthread.h> 
#include <stdio.h>
#include <stdlib.h>  
  
int x = 100; /* x variabile globale variabile condivisa */  

void * f1(void *threadid){
   for(int i=0 ; i<100000; i=i+1){
      x=x+5;
   } /* questo ciclo dovrebbe sommare 500000 a x */
   pthread_exit(NULL);
}

void * f2(void *threadid){
   for(int i=0 ; i<100000; i=i+1){
      x=x+10; /* questo ciclo dovrebbe sommare 1000000 a x */
   }  
   pthread_exit(NULL);
}

int main( ){ /* x è nel read_set */
 pthread_t t1, t2;  
 pthread_create(&t1, NULL, f1, (void *)t1);/*x è nel write_set*/
 pthread_create(&t2, NULL, f2, (void *)t2);/*x è nel write_set*/ 
 pthread_join(t1,NULL); /* attendo che termini il primo thread */
 pthread_join(t2,NULL); /* attendo che termini il secondo thread */
 printf("x vale %d\n",x); /* lettura di x che dovrebbe valere 1500100*/
}

