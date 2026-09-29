# PROG5121 Part 2 — QuickChat Messages

This project continues the Part 1 registration/login application and adds the Part 2 QuickChat message functionality.

## Main files
- `Login.java` — Part 1 registration and login validation.
- `Main.java` — console application and Part 2 menu/message flow.
- `Message.java` — message validation, ID/hash generation, send/disregard/store actions, JSON storage, and sent-message tracking.
- `LoginTest.java` — Part 1 tests.
- `MessageTest.java` — Part 2 tests.
- `pom.xml` — Maven/JUnit/Gson configuration.

## Running
Run `mvn test` to execute the automated tests. Run `Main` as a Java application to use the console program.

Stored messages are written to `messages.json` in the application's working directory when the Store option is selected.


Part 2 notes:
- JSON is written as a JSON array to `messages.json` by the `storeMessage()` method.
- The project uses JUnit 5 for unit testing and GitHub Actions (`TestJava.yml`) to run `mvn test`.
