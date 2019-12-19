import java.util.*;

public class game {
	
	public char [][] hello = new char [3][3]; 
	public int q = 1, x, y, g = 0, t; 
	public Scanner input = new Scanner(System.in); 
	public Boolean imp;
	
 	public void finp(){ 
		
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				hello [i][j] = ' ';
			}
			System.out.println("-------------");
			System.out.println("| " + hello [i][0] + " | " + hello [i][1] + " | " + hello [i][2] + " |");
		}
		System.out.println("-------------");
	}
	
	public void outp(){ 
		for (int i = 0; i < 3; i++){
			System.out.println("-------------");
			System.out.println("| " + hello [i][0] + " | " + hello [i][1] + " | " + hello [i][2] + " |");
		}
		System.out.println("-------------");
	}
	
	public void step(){ 

		if (q > 0){
			q = q * (-1);
			hello [x][y] = 'X';
		} else {
			q = q * (-1);
			hello [x][y] = 'O';
		}
	}
	
	public Boolean stepch(){ 
		System.out.print("Укажите координаты вашего хода(ряд,столбец) ");
		x = input.nextInt() - 1;
		y = input.nextInt() - 1;
		if ((x < 0 || x > 2) || (0 > y || y > 2)){
			System.out.println("Неверные координаты ввода!");
			imp = false;
		} else {
			if ((hello [x][y] != ' ')){
				System.out.println("Неверные координаты ввода!");
				imp = false;
			} else {
			g++;
			imp = true;}
		}
		if (imp) {
			return imp;
		} else {return imp;}
	}
	
	public Boolean gameover(){ 
		// _______________
		if (g < 9){
			return true;
		} else {
			return false;
		}
		
	}
	
	public void wincheck(){ 
		Boolean N = true;
		for (int k = 0; k < 4; k++){
			switch (k){
			
			case 0:
				if ((hello [x][y] == hello [(x + 1) % 3][y]) && (hello [x][y] == hello [(x + 2) % 3][y])){
					System.out.println("Победил игрок игравший " + hello [x][y] + "-ом!");
					g = 9; N = false;
				}
				break;
				
			case 1:
				if ((hello [x][y] == hello [x][(y + 1) % 3]) && (hello [x][y] == hello [x][(y + 2) % 3])){
					System.out.println("Победил игрок игравший " + hello [x][y] + "-ом!");
					g = 9; N = false;
				}
				break;
			
			case 2:
				if (x == y){
					if ((hello [x][y] == hello [(x + 1) % 3][(y + 1) % 3]) && (hello [x][y] == hello [(x + 2) % 3][(y + 2) % 3])){
						System.out.println("Победил игрок игравший " + hello [x][y] + "-ом!");
						g = 9; N = false;
					}
				}
				break;
				
			case 3:
				if (x == (2 - y)){
					if ((hello [x][y] == hello [(x + 1) % 3][(y + 2) % 3]) && (hello [x][y] == hello [(x + 2) % 3][(y + 1) % 3])){
						System.out.println("Победил игрок игравший " + hello [x][y] + "-ом!");
						g = 9; N = false;
					}
				}
				break;
			}
		}
		if ((g == 9) && N){
			System.out.println("Ничья!");
		}	
	}
	
	public Boolean firstp(){ 
		System.out.print("Кто ходит первый? (Введите 1 для Х, 2 для О) ");
		t = input.nextInt();
		switch (t){
		case 1:
			return false;
		case 2:
			q = q * (-1);
			return false;
		default: return true;
		}
	}
}