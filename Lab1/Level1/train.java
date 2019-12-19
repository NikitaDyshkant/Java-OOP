
public class body {

    static int N = 10; 
    static int sum = -1;  
    static int[][] M = new int[N][];  
    static int[][] stn = new int[3][N]; 
    
    static void initstn() { 
        for (int i = 0; i < N; i++) {
            stn[0][i] = 0;
            stn[1][i] = 0;
            stn[2][i] = 0;
        }
    }
    
    static void initAll() {	
        for (int i = 0; i < N; i++) {
            M[i] = new int[N - i];
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (N - i); j++) {
                M[i][j] = (int) ((Math.random() * 6 + 2) * (j + 1));   
            }
        }
    }
    
    static void pricePrint() {  
        System.out.println("Всего станций: " + (N + 1));
        for (int i = 0; i < N; i++) {
            System.out.println("Цена проезда из станции №" + (i + 1));
            for (int j = 0; j < (N - i); j++) {
                System.out.print(M[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    static void step(int st, int iv) { 
        if (st == N) {                  
            int x = 0;                           
            for (int a : stn[0]) {
                x += a;
            }
            check(x);
            x = 0;
            stn[0][iv - 1] = 0;
            stn[1][iv - 1] = 0;
        }
        else {
            for (int i = st; i < N; i++) {
                stn[0][iv] = M[st][i - st];
                stn[1][iv] = st;
                step(i + 1, iv + 1);    
            }
        }
    }
    
    
    
    private static void check(int a) {   
        if (sum < 0 || a < sum) {
            for (int i = 0; i < N; i++) {
                stn[2][i] = stn[1][i];     
            }
            sum = a;
        }
    }
    
    private static void printResult() {    
        System.out.println("Минимальная стоимость: " + sum);
        System.out.print("Порядок посещения станций: 1");
        for (int i : stn[2]) {
            if (i != 0) {
                System.out.print(", " + (i + 1));
            }
        }
        System.out.println(", " + (N + 1));
    }

    public static void main(String agrs[]) {
        initAll();
        pricePrint();
        step(0, 0);
        printResult();
    }
}