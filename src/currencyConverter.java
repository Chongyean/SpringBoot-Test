import java.util.Scanner;

public class currencyConverter {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount in USD: ");
        double amount = sc.nextDouble();

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    convertCurrencyEUR(amount);
                    break;
                case 2:
                    convertCurrencyKHR(amount);
                    break;
                case 3:
                    convertCurrencyINR(amount);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void convertCurrencyEUR(double amount) {
        double conversionRateOfEUR = 0.91;
        double convertedAmountEUR = amount * conversionRateOfEUR;
        System.out.printf("Converted amount in EUR: %.2f\n", convertedAmountEUR);
    }

    private static void convertCurrencyKHR(double amount) {
        double conversionRateOfKHR = 4100;
        double convertedAmountKHR = amount * conversionRateOfKHR;
        System.out.printf("Converted amount in KHR: %.2f\n", convertedAmountKHR);
    }

    private static void convertCurrencyINR(double amount) {
        double conversionRateOfINR = 83.2;
        double convertedAmountINR = amount * conversionRateOfINR;
        System.out.printf("Converted amount in INR: %.2f\n", convertedAmountINR);
    }

    private static void displayMenu() {
        System.out.println("Currency Converter Menu:");
        System.out.println("1. Convert to EUR");
        System.out.println("2. Convert to KHR");
        System.out.println("3. Convert to INR");
        System.out.println("4. Exit");
    }
}
