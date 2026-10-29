# Student Management System

A web-based Student Management System developed using Java and Spring Boot to manage student records through a simple interface with secure administrator login.

## Features

- Secure administrator login and authentication
- Student record management
- Add and edit student details
- View student records
- MySQL database integration
- Responsive web interface using Thymeleaf and CSS

## Technologies Used

- **Backend:** Java, Spring Boot
- **Frontend:** Thymeleaf, HTML, CSS
- **Database:** MySQL
- **Security:** Spring Security
- **ORM:** Spring Data JPA, Hibernate
- **Build Tool:** Maven

## Prerequisites

- Java 21 or a compatible version supported by the project
- MySQL Server
- Maven (or the included Maven Wrapper)

## Setup and Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/harshadapadmar113/Student-Management-System.git
   ```

2. Open the project in Spring Tool Suite (STS) or another Java IDE.

3. Create a MySQL database named `student_db`.

4. Configure the database connection in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student_db
   spring.datasource.username=root
   spring.datasource.password=${DB_PASSWORD}
   ```

5. Set the `DB_PASSWORD` environment variable to your local MySQL password.

6. Run the application from the IDE, or use the Maven Wrapper:

   ```bash
   mvnw.cmd spring-boot:run
   ```

7. Open the application in your browser:

   http://localhost:8080

## Database Configuration

Ensure MySQL is running before starting the application. Use your own local database credentials and never publish passwords or other sensitive information in the repository.

## Author

**Harshada Padmar**

GitHub: [harshadapadmar113](https://github.com/harshadapadmar113)
