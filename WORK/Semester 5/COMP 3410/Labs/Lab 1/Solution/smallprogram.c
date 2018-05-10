#include <stdio.h>
#define MAX 40

void display (char n[], int times);

int main(void){
	
	
	char name[MAX];
	int loop = 0;
	printf("What is your name?");
	gets(name);
	printf("How many times to print it?");
	scanf("%d",&loop);
		
	display(name,loop);	
	return 0;

}

void display(char n[],int t){

	int i = 0;
	for(i = 0;i<t;i++){
		printf("%s",n);
		printf("\n");
	}
}

