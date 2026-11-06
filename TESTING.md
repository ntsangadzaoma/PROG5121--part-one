# PROG5121 — Testing Documentation

This document describes the manual and automated testing performed for Part 1 (Registration and Login) and Part 2 (Sending Messages) of the PROG5121 Programming 1A POE.

## Part 1 — Registration and Login

### 1. Username Validation

Valid username:
```text
kyl_1
```

Expected result: Username successfully captured.

Invalid username:
```text
kyle!!!!!!!
```

Expected result: Username is rejected because it does not contain an underscore and exceeds five characters.

### 2. Password Validation

Valid password:
```text
Ch&&sec@ke99!
```

Expected result: Password successfully captured.

Invalid password:
```text
password
```

Expected result: Password is rejected because it does not meet the password complexity requirements.

### 3. Cell Phone Validation

Valid cellphone number:
```text
+27838968976
```

Expected result: Cell phone number successfully added.

Invalid cellphone number:
```text
08966553
```

Expected result: Cellphone number is rejected because it does not use the required international format.

### 4. Login Testing

The login tests verify that the registered username and password match the credentials entered during login.

The tests cover:
- Successful login with correct credentials.
- Failed login with incorrect credentials.

## Part 2 — Sending Messages

### 1. Message ID Validation

The application generates a random 10-digit message ID.

Tests verify:
- A generated 10-digit ID is accepted.
- An ID that is too short is rejected.
- An ID that is too long is rejected.
- An ID containing letters is rejected.

### 2. Recipient Cellphone Validation

Valid recipient:
```text
+27718693002
```

Expected result: Cell phone number successfully captured.

Invalid recipient:
```text
08575975889
```

Expected result: Recipient number is rejected because it does not contain the required international code.

### 3. Message Length Validation

Valid message:
- A message containing 250 characters.

Expected result: Message ready to send.

Invalid message:
- A message containing 251 characters.

Expected result: Message is rejected and the number of excess characters is displayed.

### 4. Message Hash Testing

The message hash is generated using:
- The first two digits of the message ID.
- The message number.
- The first and last words of the message.

Example expected hash:
```text
00:0:HITONIGHT
```

The test verifies that the generated hash matches the expected value.

### 5. Message Actions

The application supports three message actions:

**Send**

Expected result:
```text
Message successfully sent.
```

The total number of sent messages increases.

**Disregard**

Expected result:
```text
Press 0 to delete the message.
```

The disregarded message is not counted as sent.

**Store**

Expected result:
```text
Message successfully stored.
```

The message is saved to `messages.json` and is not counted as sent.

### 6. JSON Storage Testing

The JSON storage tests verify that:
- A JSON file is created when a message is stored.
- Multiple messages can be stored.
- Message IDs and message details are preserved.
- Special characters and line breaks are correctly preserved.
- Stored messages have the correct status.

### 7. Message Printing

The test verifies that `printMessages()` returns the message details, including:
- Message ID.
- Message hash.
- Recipient.
- Message content.

### 8. Total Messages Testing

The tests verify that successfully sent messages increase the total message count, while stored and disregarded messages do not increase the sent-message count.

## Running Automated Tests

The project uses JUnit 5 for automated testing.

To run all tests, execute the following command from the project directory:

```bash
mvn clean test
```

### Latest Test Results

The final local test run completed successfully:

```text
Tests run: 23
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

The tests consist of:
- 8 Part 1 tests.
- 15 Part 2 tests.

GitHub Actions is also configured to run the automated tests when changes are pushed or pull requests are created.

