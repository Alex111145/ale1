#include<stdio.h>
#include<signal.h>
#include <unistd.h>
#include <sys/wait.h>

int main(  ){
 int p = getpid( );
    printf("sono %d, ora mi  clono\n",p);
    int x = fork( );  
    int y = 10; /*istruzione eseguita da entrambi padre e figlio*/ 
    printf("y vale %d\n",y); /*anche questa*/ 
   
    if(x==0){   /* ramo eseguito solo dal figlio */
        int f = getpid( );
        printf("sono il figlio, cioè %d\n",f);
        for(int i =1;;i++){printf("ciao, %d  \n",i);}
   }
   
    else{         /* ramo eseguito solo dal padre */
        printf("sono %d, mio figlio è %d\n",getpid( ),x);
        for(int i=1;i<500000;i++){}
        kill(x,SIGKILL);
        int s;
        int u;
        int  w = wait(&s); /* il padre aspetta la terminazione del figlio */
        int  z = wait(&u); /* il padre aspetta la terminazione del figlio, ma */
                           /* non ha figli: la wait restituisce errore /*
        printf("sono %d, mio figlio %d ha terminato\n",getpid( ),w);
        printf("il suo stato e' %d \n"  , s);
        printf("sono %d, mio figlio %d ha terminato\n",getpid( ),z);
        printf("il suo stato e' %d \n"  , u);
    }
    
}

