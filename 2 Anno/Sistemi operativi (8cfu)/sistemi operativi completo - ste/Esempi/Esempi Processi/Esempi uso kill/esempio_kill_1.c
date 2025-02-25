#include<stdio.h>
#include<signal.h>
#include <unistd.h>
/* #include <sys/wait.h> */

int main(  ){
 int p = getpid( );  
    printf("sono %d, ora mi  clono\n",p);
    int x = fork( ); 
    int y = 10; 
    printf("y vale %d\n",y); 
   
    if(x==0){   /* ramo eseguito solo dal figlio */
        int f = getpid( );
        printf("sono il figlio, cioè %d\n",f);
        for(int i =1;;i++){printf("ciao, %d  \n",i);}
   }
   
    else{     /* ramo eseguito solo dal padre */
        printf("sono %d, mio figlio è %d\n",getpid( ),x);
        for(int i=1;i<500000;i++){ }
        kill(x,SIGKILL); /* invio al figlio il segnale che ne provoca la terminazione */
                         /* non posso prevedere quante volte il figlio riuscirà a fare */
                         /* la print */
    }
    
}

