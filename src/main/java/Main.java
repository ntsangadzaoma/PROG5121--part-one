import java.util.Scanner;

/**
 * QuickChat console application for Part 1 and Part 2.
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

        Login login = new Login(username, password, cellPhoneNumber, firstName, lastName);
        System.out.println();
        System.out.println(login.registerUser());

        if (!login.checkUserName() || !login.checkPasswordComplexity() || !login.checkCellPhoneNumber()) {
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

        if (!login.loginUser()) {
            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages would you like to enter? ");
        int numberOfMessages;
        try {
            numberOfMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid number of messages.");
            scanner.close();
            return;
        }

        int messageNumber = 0;
        while (messageNumber < numberOfMessages) {
            System.out.println();
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Enter recipient cell number: ");
                String recipient = scanner.nextLine();
                System.out.print("Enter message: ");
                String text = scanner.nextLine();

                Message message = new Message(recipient, text, messageNumber);

                if (!message.checkMessageID()) {
                    System.out.println("Message ID is not valid.");
                    continue;
                }

                String recipientResult = message.checkRecipientCell();
                System.out.println(recipientResult);
                String lengthResult = message.checkMessageLength();
                System.out.println(lengthResult);

                if (!recipientResult.equals("Recipient successfully captured.")) {
                    continue;
                }
                if (!lengthResult.equals("Message ready to send.")) {
                    System.out.println("Please enter a message of less than 250 characters.");
                    continue;
                }

                message.createMessageHash();
                System.out.print("Choose Send, Disregard or Store: ");
                String action = scanner.nextLine();
                System.out.println(message.SentMessage(action));

                if (action.equalsIgnoreCase("send")) {
                    System.out.println();
                    System.out.println("Message ID: " + message.getMessageID());
                    System.out.println("Message Hash: " + message.getMessageHash());
                    System.out.println("Recipient: " + message.getRecipient());
                    System.out.println("Message: " + message.getMessage());
                    System.out.println("Total messages sent: " + message.returnTotalMessagess());
                }

                messageNumber++;
            } else if (option.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (option.equals("3")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid option. Please select 1, 2 or 3.");
            }
        }

        scanner.close();
    }
}
