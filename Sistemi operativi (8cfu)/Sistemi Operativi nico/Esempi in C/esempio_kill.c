#include<stdio.h>
#include<signal.h>

main(  ){
    int p = getpid( ); 
    printf("sono %d, ora mi  clono\n",p);
  
    int x = fork( );     
    for (int i=0;i<500000000;i++){}
    p = getpid( );
    
    int y = 10;  
    printf("y vale %d e io sono %d\n",y,p);  
    

    if(x==0){   
        int f = getpid( );
        printf("sono il figlio, cioè %d\n",f);
    }
   
    else{        
         kill(x,SIGKILL);
         printf("sono %d, mio figlio è %d\n",getpid( ),x);
    }
}
