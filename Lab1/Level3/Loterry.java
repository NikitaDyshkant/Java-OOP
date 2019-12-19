/* Мини-лотерея. 
Программа загадывает 5 (неповторяющихся) чисел в диапазоне от 1 до 42, но не показывает их на экран.
Пользователь пытается их угадать – вводит каких-то 5 чисел с клавиатуры. 
Назначить призы за каждое совпадение.*/
package qwer;
public class Lotereya {
static void meth(int a, int b,int c,int d, int e){
		int similarity=0;
		int array[]=new int [5];
		System.out.println("Ваши числа:"+a+" "+b+" "+c+" "+d+" "+e);
		for (int i=0;i<array.length;i++){
			int n=(int)(Math.random()*43+1);
			boolean yes=true;
			for(int j=0;j<=i;j++){
			if (array[j]==n)
				yes=false;
		}
			if(yes)
				array[i]=n;
			else i--;
		}
	for(int i=0;i<array.length;i++){
		if(array[i]==a || array[i]==b || array[i]==c || array[i]==d || array[i]==e){
			similarity++;
		System.out.print("Совпало число "+array[i]+" ");
		}
	}
	System.out.println();
	if(similarity<=2)
		System.out.println("У вас "+similarity+" совподения. Могло быть и лучше!");
	else
		if(similarity>2 && similarity<=4)
			System.out.println("У вас "+similarity+" совподения. Довольно не плохой результат!");
		else
			System.out.println("ДА НУ НА!! У вас "+similarity+" совподения.");
	}
	

public static void main(String[] args) {
	meth(5,14,32,12,8);
}
}
