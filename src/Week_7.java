import java.util.Scanner;
public class Week_7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base Number : ");
        double base = scanner.nextDouble();
        System.out.print("Enter exp Number : ");
        int exponent = scanner.nextInt();

        int resultInt = calculatePower((int) base, exponent);
        System.out.println("Result using int: " + resultInt);

        // Close the scanner
        scanner.close();
    }

    private static int calculatePower(int base, int exp) {
        int result = 1;

        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

}
