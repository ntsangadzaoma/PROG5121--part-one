# PROG5121 Part 1 — Registration and Login

This project implements the Part 1 registration and login requirements from the supplied POE.

## Contents
- `Login.java` — required validation and login methods.
- `Main.java` — console application demonstrating registration and login.
- `LoginTest.java` — JUnit tests for the required valid/invalid cases.
- `pom.xml` — Maven project configuration for JUnit 5.

## Required methods
- `checkUserName()`
- `checkPasswordComplexity()`
- `checkCellPhoneNumber()`
- `registerUser()`
- `loginUser()`
- `returnLoginStatus()`

## Running
Open the project in NetBeans as a Maven project. Run `Main` for the console demonstration and run the JUnit tests from the test source.

The phone-number regular expression is attributed in `Login.java` to the Stack Overflow reference used for the South African `+27` pattern:
https://stackoverflow.com/questions/33477950/java-regex-phone-number
