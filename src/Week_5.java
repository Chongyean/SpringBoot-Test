import java.util.Scanner;

public class Week_5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        int evenNumberCount = 0;
        int oddNumberCount = 0;

        do {
            System.out.println("\n======= Even Odd Counter =======");
            System.out.println("1. Input number");
            System.out.println("2. Show even number");
            System.out.println("3. Show odd number");
            System.out.println("4. Exit");
            System.out.println("====================================");

            System.out.print("Choose an option  : ");
             choice = sc.nextInt();

             //Clear buffer
            sc.nextLine();
            switch (choice) {
                case 1: {
                    System.out.print("Enter number : ");
                    int number = sc.nextInt();

                    if (number % 2 == 0) {
                        evenNumberCount++;
                    }
                    else {
                        oddNumberCount++;
                    }
                }
                case 2:
                    System.out.println("Even number : " + evenNumberCount);
                case 3:
                    System.out.println("Odd number : " + oddNumberCount);
                case 4:
                    System.out.println("Exit......");
                    default:
                        System.out.println("Invalid choice");
            }
        }
        while (choice != 4);
    }
}
