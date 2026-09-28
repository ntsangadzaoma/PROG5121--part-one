import java.util.Scanner;

/**
 * Console demonstration of Part 1.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registration ===");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cell phone number (e.g. +27838968976): ");
        String cellPhoneNumber = scanner.nextLine();

        Login login = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        System.out.println();
        System.out.println(login.registerUser());

        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {
            System.out.println();
            System.out.println("Registration could not be completed. Please correct the information and try again.");
            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("=== Login ===");

        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        login.setLoginCredentials(loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus());

        scanner.close();
    }
}
