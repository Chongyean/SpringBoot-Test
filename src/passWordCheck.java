import java.util.Scanner;

public class passWordCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (isSecuredPassword(password)) {
            System.out.println("Password is Secured.");
        } else {
            System.out.println("Password is Not Secured.");
        }
        sc.close();
    }
    static boolean isSecuredPassword(String password) {
        if (password.length() < 8) {
            return false; // Password must be at least 8 characters long
        }
        if (!password.matches(".*[A-Z].*")) {
            return false; // Must contain at least one uppercase letter
        }
        if (!password.matches(".*[a-z].*")) {
            return false; // Must contain at least one lowercase letter
        }
        if (!password.matches(".*\\d.*")) {
            return false; // Must contain at least one digit
        }
        return password.matches(".*[@#$%^&+=!].*"); // Must contain at least one special character

    }
}
