# JavaFX Login System

This project is a simple JavaFX application that implements a user login system with database integration. Users can create new accounts, log in with existing credentials, and access a profile page displaying their information.

## Features

*   **User Registration:** Users can create new accounts by providing their name, email, and phone number.
*   **User Login:** Existing users can log in using their email and password.
*   **Profile Display:** Upon successful login, users are redirected to a profile page displaying their name, email, and phone number.
*   **Database Integration:** User data is stored and retrieved from a database (JDBC).
*   **Input Validation:** Basic input validation to ensure data integrity.
*   **Password Management:** Secure password handling (implementation details to be specified).

## Technologies Used

*   JavaFX
*   JDBC (Java Database Connectivity)
*   MySQLconnector jar

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   MySQL Workbench

### Installation

1.  Clone the repository.
2.  Set up the database:
    *   Create a database schema with the necessary tables for users (provide SQL script if possible).
    *   Configure the database connection in `MyJDBC.java` with the correct credentials.
3.  Import the project into your IDE (IntelliJ IDEA, Eclipse, etc.).
4.  Add any required dependencies to the project.

### Compilation and Execution

1.  Compile the Java code using your IDE or a build tool like Maven (`mvn compile`).
2.  Run the application from your IDE or using the command line (`java com.example.javafxapp.HelloApplication`).

## Usage

1.  Launch the application.
2.  You will be presented with the login page.
3.  If you are a new user, click on the "Register" or "Create Account" button (if implemented).
4.  Fill in the required information and click "Register."
5.  If you are an existing user, enter your email and password and click "Login."
6.  Upon successful login, you will be redirected to your profile page.

## Author

ANEKE Ikemdinachi
