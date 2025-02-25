#include<stdio.h>
#include <unistd.h>

int main(  ){
    int p = getpid( ); 
    printf("sono %d, ora mi  clono\n",p);
    int x = fork( );     
    p = getpid( );
    
    int y = 10;  
    printf("y vale %d e io sono %d\n",y,p);  
   
    if(x==0){   
        int f = getpid( );
        printf("sono il figlio, cioè %d\n",f);
        for(int i=0; i<5; i++){y=y+4;}/* IL FIGLIO STAMPA 30  */ 
        printf("y vale %d e io sono %d\n",y,p);
    }
   
    else{        
        printf("sono %d, mio figlio è %d\n",getpid( ),x);
        y=y+700; /* IL PADRE STAMPA 710 */ 
        printf("y vale %d e io sono %d\n",y,p);
    }
}

