import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter you number: ");
        try {
            String x = reader.readLine();
            printBigNumbers(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printBigNumbers(String number){
        HashMap<Integer, String[]> bigNumbers = new HashMap<Integer, String[]>();
        bigNumbers.put(0, new String []{
                "  000  ",
                " 0   0 ",
                " 0   0 ",
                " 0   0 ",
                " 0   0 ",
                " 0   0 ",
                "  000  "});
        bigNumbers.put(1, new String []{
                "   1  ",
                "  11  ",
                " 1 1  ",
                "   1  ",
                "   1  ",
                "   1  ",
                " 1111 "});
        bigNumbers.put(2, new String []{
                "   22  ",
                " 2   2 ",
                "     2 ",
                "    2  ",
                "  2    ",
                " 2     ",
                " 22222 "});
        bigNumbers.put(3, new String []{
                "  333  ",
                " 3   3 ",
                "     3 ",
                "   33  ",
                "     3 ",
                " 3   3 ",
                "  333  "});
        bigNumbers.put(4, new String []{
                "   44  ",
                "  4 4  ",
                " 4  4  ",
                " 4  4  ",
                " 44444 ",
                "    4  ",
                "    4  "});
        bigNumbers.put(5, new String []{
                " 55555 ",
                " 5     ",
                " 5555  ",
                " 5   5 ",
                "     5 ",
                " 5   5 ",
                "  555  "});
        bigNumbers.put(6, new String []{
                "    6  ",
                "   6   ",
                "  6    ",
                " 6666  ",
                " 6   6 ",
                " 6   6 ",
                "  666  "});
        bigNumbers.put(7, new String []{
                " 77777 ",
                " 7   7 ",
                "     7 ",
                "    7  ",
                "   7   ",
                "  7    ",
                "  7    "});
        bigNumbers.put(8, new String []{
                "  888  ",
                " 8   8 ",
                " 8   8 ",
                "  888  ",
                " 8   8 ",
                " 8   8 ",
                "  888  "});
        bigNumbers.put(9, new String []{
                "  999  ",
                " 9   9 ",
                " 9   9 ",
                "  9999 ",
                "    9  ",
                "   9   ",
                "  9    "});
        char[] array = number.toCharArray();
        for (int i=0; i < 7; i++){
            for (char ch: array){
                int num = Integer.parseInt(String.valueOf(ch));
                String[] line = bigNumbers.get(num);
                System.out.print(line[i]);
            }
            System.out.println("");
        }
    }
}