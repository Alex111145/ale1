#include<stdio.h>
#include<signal.h>
#include <unistd.h>
#include <sys/wait.h>

int main(  ){
 int p = getpid( );
    printf("sono %d, ora mi  clono\n",p);
    int f;
    printf("eseguo una wait ma non ho figli!!!!"); 
    int r = wait(&f);
    printf("codice terminazione figlio inesistente %d \n",f);
    printf("risultato system call %d \n",r);

    int x = fork( ); 
    int y = 10;  
    printf("y vale %d\n",y);  
   
    if(x==0){ 
        int f = getpid( );
        printf("sono il figlio, cioè %d\n",f);
        for(int i =1;;i++){printf("ciao, %d  \n",i);}
   }
   
    else{       
        printf("sono %d, mio figlio è %d\n",getpid( ),x);
        for(int i=1;i<500000;i++){}
        kill(x,SIGKILL);
        int s;
        int u;
        int  w = wait(&s); /* il padre aspetta la terminazione del figlio */
        int  z = wait(&u);
      printf("sono %d, mio figlio %d ha terminato\n",getpid( ),x);
      printf("il suo stato e' %d \n"  , s);
      printf("il risultato della wait e': %d \n",w);
      printf("sono %d, mio figlio %d ha terminato\n",getpid( ),x);
      printf("il suo stato e': %d \n"  , u);
      printf("il risultato della wait e':%d" , z);   
    }
    
}

