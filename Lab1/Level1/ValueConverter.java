import java.util.Scanner;

public class ConverterOfPhysicalQuantities {
    public static void main(String[] args) {
        System.out.println("Выберите что переводить: \n 1-масса \n 2-расстояние");

        Scanner scanner = new Scanner(System.in);
        int physicalQuantities = scanner.nextInt();

        if (physicalQuantities == 1) {
            System.out.println("Выберите единицу измерения: \n 1-кг \n 2-грамм \n 3-фунт \n 4-карат");
        } else {
            System.out.println("Выберите единицу измерения: \n 1-метр \n 2-миля \n 3-ярд \n 4-фут");
        }

        int unit = scanner.nextInt();

        System.out.println("Введите число: ");
        double userNumber = scanner.nextDouble();

        if (physicalQuantities == 1) {

            double kilogram = 0;
            double gram = 0;
            double lb = 0;
            double carat = 0;

            switch (unit) {
                case 1:
                    kilogram = userNumber;
                    gram = userNumber * 1000;
                    lb = userNumber * 2.2046223302272;
                    carat = userNumber * 5000;
                    break;
                case 2:
                    kilogram = userNumber / 1000;
                    gram = userNumber;
                    lb = userNumber / 453.592;
                    carat = userNumber * 5;
                    break;
                case 3:
                    kilogram = userNumber / 2.205;
                    gram = userNumber * 453.592;
                    lb = userNumber;
                    carat = userNumber * 2267.962;
                    break;
                case 4:
                    kilogram = userNumber / 5000;
                    gram = userNumber / 5;
                    lb = userNumber / 2267.962;
                    carat = userNumber;
                    break;
            }
            System.out.println("Результат: \n килограмм = " + kilogram + "\n грам = " + gram + "\n фут = " + lb + "\n карат = " + carat);

        } else {
            double meter = 0;
            double mile = 0;
            double yard = 0;
            double foot = 0;

            switch (unit) {
                case 1:
                    meter = userNumber;
                    mile = userNumber / 1609.34;
                    yard = userNumber * 1.094;
                    foot = userNumber * 3.281;
                    break;
                case 2:
                    meter = userNumber * 1609.34;
                    mile = userNumber;
                    yard = userNumber * 1760;
                    foot = userNumber * 5280;
                    break;
                case 3:
                    meter = userNumber / 1.094;
                    mile = userNumber / 1760;
                    yard = userNumber;
                    foot = userNumber * 3;
                    break;
                case 4:
                    meter = userNumber / 3.281;
                    mile = userNumber / 5280;
                    yard = userNumber / 3;
                    foot = userNumber;
                    break;
            }
            System.out.println("Результат: \n метр = " + meter + "\n миля = " + mile + "\n ярд = " + yard + "\n фут = " + foot);
        }
    }
}