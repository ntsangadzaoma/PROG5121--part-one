/**
 * Registration and login validation for Part 1.
 */
public class Login {

    private final String username;
    private final String password;
    private final String cellPhoneNumber;
    private final String firstName;
    private final String lastName;

    private String loginUsername = "";
    private String loginPassword = "";

    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                hasCapital = true;
            } else if (Character.isDigit(character)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks for a South African international mobile number.
     *
     * Regex reference:
     * https://stackoverflow.com/questions/33477950/java-regex-phone-number
     */
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) {
            return false;
        }

        String regex = "^\\+27\\d{9}$";
        return cellPhoneNumber.matches(regex);
    }

    public String registerUser() {
        StringBuilder result = new StringBuilder();

        if (checkUserName()) {
            result.append("Username successfully captured.");
        } else {
            result.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        result.append(System.lineSeparator());

        if (checkPasswordComplexity()) {
            result.append("Password successfully captured.");
        } else {
            result.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        result.append(System.lineSeparator());

        if (checkCellPhoneNumber()) {
            result.append("Cell phone number successfully added.");
        } else {
            result.append("Cell phone number incorrectly formatted or does not contain international code.");
        }

        return result.toString();
    }

    public void setLoginCredentials(String username, String password) {
        this.loginUsername = username == null ? "" : username;
        this.loginPassword = password == null ? "" : password;
    }

    public boolean loginUser() {
        return username != null
                && password != null
                && username.equals(loginUsername)
                && password.equals(loginPassword);
    }

    public String returnLoginStatus() {
        if (loginUser()) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}
