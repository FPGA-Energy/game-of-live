#include <stdio.h>
#include <stdlib.h>

// n = 4 , m = 5
// 0,0 0,1 0,2 0,3 0,4		 0  1  2  3  4
// 1,0 1,1 1,2 1,3 1,4		 5  6  7  8  9 
// 2,0 2,1 2,2 i,j 2,4		10 11 12 13 14
// 3,0 3,1 3,2 3,3 3,4

// i = 2
// j = 3 
// pos = 2*m + 3 = i*m + j

void print2darray(int *prev, int n, int m){
	int i, j;
	for (i = 0; i < n; i++){
		for (j = 0; j < m; j++){
		printf("%d", prev[i*m + j]);
		}
		printf("\n");
	}
printf("\n");
}

int * step (int *prev, int n, int m) {
    int i, j;
    int count;
    int *next = calloc(n * m, sizeof (int));

    for (i = 1; i < n-1; i++){
    	for (j = 1; j < m-1; j++){
    		// count live neighbours
    		count = ( 	prev [(i-1)*m + j-1] + prev [(i-1)*m + j] + prev [(i-1)*m + j+1] + 
    					prev [i*m + j-1]     +   	  0 		  + prev [i*m + j+1]     +
    					prev [(i+1)*m + j-1] + prev [(i+1)*m + j] + prev [(i+1)*m + j+1]
    					);
    		// if the cell is live and 2 neighbours live, the cell lives
    		if(prev [i*m + j] && count == 2 ){
    		 	next[i*m + j]  = 1;
    		} else {
    		// if the cell has 3 living neighbours, the cell lives
   				if(count == 3){
   					next[i*m + j]  = 1;
   				}else{
   					next[i*m + j]  = 0;
   				};
   			};
    	
    	}
    }
    
	free(prev);	      
    return next;
    }


int main (int argc, char** argv) {
	int n, m;
	int repetitons = 10; // default value
	// input argument is the filename of the input
	
   FILE *fp;
   char buff[255];
   fp = fopen(argv[1], "r");
   if(argc > 1){
   		repetitons = atoi(argv[2]);
   	}

   	
   
   // the first two inputs in the input file indicates rows and columns
   fscanf(fp, "%d", &n); //  n rows
   fscanf(fp, "%d", &m); //  m columns
   	
    //int n = 7; // rows
    // int m = 6; // columns
    // int	init[] = { 0, 0, 0, 0, 0, 0,
//     	  		   0, 1, 1, 0, 0, 0,
//     			   0, 1, 0, 0, 0, 0,
//     			   0, 0, 0, 0, 1, 0, 
//     			   0, 0, 0, 1, 1, 0,
//     			   0, 0, 0, 0, 0, 0,
//     			   0, 0, 0, 0, 0, 0};

	// create an array large enough to hold the content
    int *a = malloc(n * m * sizeof (int));
    
   
    for(int i = 0; i < n*m; i++){
    	// a[i]=init[i];
    	fscanf(fp, "%d", &a[i]); 
    	}

	// printf("%p \n",a);

    print2darray(a,n,m);
    
    for(int x=0; x<repetitons; x++){
    	a = step(a,n,m);
    	// print2darray(a,n,m);
    }
    print2darray(a,n,m);
	free(a);
    return 0;
}

