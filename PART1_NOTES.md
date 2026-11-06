# Programming 1A POE — Part 1 and Part 2

This project implements the PROG5121 Programming 1A Portfolio of Evidence (POE).

The application is a Java console application developed using Maven. It includes the registration and login functionality from Part 1 and the message-sending functionality from Part 2.

## Part 1 — Registration and Login

### Features

- Username validation.
- Password complexity validation.
- South African cellphone number validation.
- User registration messages.
- Login validation.
- Login success and error messages.
- JUnit unit tests.

### Main Classes

`Login.java` contains the registration, validation and login methods.

`Main.java` runs the console application, including registration and login.

`LoginTest.java` contains the Part 1 unit tests.

## Part 2 — Sending Messages

### Features

- Random 10-digit message ID generation and validation.
- Recipient cellphone number validation.
- Message length validation (maximum 250 characters).
- Automatic message hash generation.
- Send, Disregard and Store message options.
- JSON message storage using Gson.
- Message details display.
- Total sent-message counting.
- QuickChat menu and application exit functionality.
- JUnit unit tests.

### Main Classes

`Message.java` contains the message validation, hashing, sending, printing and JSON storage methods.

`Main.java` integrates the QuickChat functionality with the existing Part 1 registration and login system.

`MessageTest.java` contains the Part 2 unit tests.

## Technologies

- Java 17
- Apache Maven
- JUnit 5
- Gson 2.11.0
- Git and GitHub
- GitHub Actions

## Testing

The project contains 23 automated JUnit tests:

- 8 Part 1 tests.
- 15 Part 2 tests.

The final local test execution completed successfully with zero failures and zero errors.

Run the tests using:

```bash
mvn clean test
```

## Version Control

Part 2 was developed on a separate Git branch named `part-2-development`.

After development and testing, the branch was merged into `main` through a GitHub pull request.

GitHub Actions was used to verify the automated tests during the pull-request process.