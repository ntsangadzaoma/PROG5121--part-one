# PROG5121 Programming 1A — Portfolio of Evidence

## Project Overview

This project implements the PROG5121 Programming 1A Portfolio of Evidence (POE), consisting of Part 1 (Registration and Login) and Part 2 (Sending Messages).

The application is a Java-based console application developed using Maven. It includes user registration, login authentication, message validation, message hashing, message sending and JSON message storage.

## Project Contents

### Part 1 — Registration and Login

- `Login.java` — user registration, validation and login methods.
- `LoginTest.java` — JUnit tests for registration and login validation.
- `Main.java` — console application containing the registration, login and QuickChat functionality.

### Part 2 — Sending Messages

- `Message.java` — message ID generation, recipient validation, message length validation, message hashing, sending, disregarding, printing and JSON storage.
- `MessageTest.java` — JUnit tests covering message validation, ID generation, hashing, sending and JSON storage.

### Project Configuration

- `pom.xml` — Maven configuration, including JUnit 5 and Gson dependencies.
- `.gitignore` — excludes generated build files, IDE files and local message storage.
- `.github/workflows/` — GitHub Actions workflow for automated testing.

## Part 1 — Required Methods

The `Login` class implements:

- `checkUserName()`
- `checkPasswordComplexity()`
- `checkCellPhoneNumber()`
- `registerUser()`
- `loginUser()`
- `returnLoginStatus()`

## Part 2 — Required Methods

The `Message` class implements:

- `checkMessageID()`
- `checkRecipientCell()`
- `createMessageHash()`
- `SentMessage()`
- `printMessages()`
- `returnTotalMessagess()`
- `storeMessage()`

## QuickChat Features

The application provides the following functionality:

1. User registration and login.
2. A QuickChat menu with three options:
   - Send Messages
   - Show recently sent messages (`Coming Soon.`)
   - Quit
3. Generation and validation of unique 10-digit message IDs.
4. Recipient cellphone number validation.
5. Message length validation (maximum 250 characters).
6. Automatic message hash generation.
7. Options to Send, Disregard or Store messages.
8. JSON storage of messages using Gson.
9. Display of message details and total messages sent.

## Technologies Used

- Java 17
- Apache Maven
- JUnit 5
- Gson 2.11.0
- Git and GitHub
- GitHub Actions

## Running the Application

### Requirements

- Java Development Kit (JDK) 17 or later.
- Apache Maven.
- An IDE such as NetBeans or Visual Studio Code.

### Run the application

Open the project directory in a terminal and execute:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="Main"
```

### Run automated tests

Execute:

```bash
mvn clean test
```

The automated tests cover registration, login, message validation, message ID generation, message hashing, message actions and JSON storage.

## JSON Message Storage

Messages selected for storage are saved in `messages.json` in the project directory.

The JSON file records:

- Message ID
- Message number
- Recipient
- Message content
- Message hash
- Message status

The application uses the Gson library for JSON processing.

## Testing

JUnit 5 is used for automated testing.

The project currently contains 23 automated tests covering Part 1 and Part 2 functionality.

The tests can be executed using Maven:

```bash
mvn clean test
```

## Version Control

The project was developed using Git and GitHub.

Part 2 was developed on the `part-2-development` branch and subsequently merged into `main` through a GitHub pull request.

GitHub Actions is configured to execute automated tests when changes are pushed or pull requests are created.

## References

1. Stack Overflow — Java regular expression for South African cellphone number validation:  
   https://stackoverflow.com/questions/33477950/java-regex-phone-number

2. Google Gson — User Guide:  
   https://github.com/google/gson/blob/main/UserGuide.md

3. JUnit 5 — User Guide:  
   https://junit.org/junit5/docs/current/user-guide/

4. Apache Maven — Documentation:  
   https://maven.apache.org/guides/

