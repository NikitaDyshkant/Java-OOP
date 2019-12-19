import java.io.*;

class Work {
    private byte number[] = new byte[2];

    void setNumber() {
        for (int a = number.length - 1; a >= 0; a--) {
            number[a] = (byte) (Math.random() * 10);
            System.out.print(number[a]);
        }
        System.out.println("");
    }

    int getNumber() {
        int num = (number[0] * 10) + number[1];
        return num;
    }
}

public class Reactions_Game {
    public static void main(String[] args) throws IOException {
        Work access = new Work();
        long began = System.currentTimeMillis();
        boolean cont = true, alike = true;
        for (int a = 3; a > 0; a--) {
            System.out.println(a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
        try (BufferedReader tmp = new BufferedReader(new InputStreamReader(
                System.in))) {
            do {
                access.setNumber();
                began = System.currentTimeMillis();
                while (System.currentTimeMillis() - began < 1500
                        && !tmp.ready()) {
                }
                if (!tmp.ready()) {
                    System.out.println("Too slow");
                    cont = false;
                    System.exit(0);
                }
                alike = Integer.parseInt(tmp.readLine()) == access.getNumber();
                if (!alike)
                    System.out.println("Wrong");

            } while (alike && cont);

        } catch (NumberFormatException exc) {
            System.out.println(exc);
        }
    }
}
