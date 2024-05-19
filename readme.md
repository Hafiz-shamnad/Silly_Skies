# Silly Skies

## Overview
Silly Skies is an airline management system developed using Java and MySQL. It provides comprehensive features to manage airline operations, including booking management, flight scheduling, passenger information, and more. This README file outlines the installation, usage, and contribution guidelines for the Silly Skies project.

## Features
- **User Authentication**: Secure login and registration system for passengers and staff.
- **Flight Management**: Schedule, update, and manage flights.
- **Booking System**: Book, cancel, and manage reservations.
- **Passenger Management**: Manage passenger details and booking history.
- **Payment Processing**: Integrate with payment gateways for booking payments.
- **Reporting**: Generate reports for flights, bookings, and financials.
- **Admin Panel**: Manage airline operations, including staff and aircraft.

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven (for dependency management)
- MySQL Server
- Git

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/sillyskies.git
   cd sillyskies
   ```

2. **Set up the MySQL database**
   - Create a new MySQL database:
     ```sql
     CREATE DATABASE sillyskies;
     ```
   - Import the database schema:
     ```bash
     mysql -u yourusername -p sillyskies < database/schema.sql
     ```

3. **Configure the application**
   - Update the `src/main/resources/application.properties` file with your MySQL database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/sillyskies
     spring.datasource.username=yourusername
     spring.datasource.password=yourpassword
     ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## Usage
1. **Login or Register**
   - Access the application at `http://localhost:8080`.
   - Log in with your credentials or create a new account.

2. **Manage Flights**
   - Use the admin panel to schedule, update, and manage flights.

3. **Book Flights**
   - Search for available flights and book tickets.

4. **Manage Bookings**
   - View and manage your bookings through the user dashboard.

5. **Generate Reports**
   - Access the reports section to generate and view various reports on flights and bookings.

## Contributing

### How to Contribute
1. **Fork the repository**
   - Click on the "Fork" button on the top right of the repository page.

2. **Clone your fork**
   ```bash
   git clone https://github.com/yourusername/sillyskies.git
   cd sillyskies
   ```

3. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

4. **Make your changes and commit**
   ```bash
   git commit -m "Add your commit message here"
   ```

5. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a pull request**
   - Go to the original repository and create a pull request from your forked repository.

### Code of Conduct
We expect all contributors to adhere to our [Code of Conduct](CODE_OF_CONDUCT.md). Please read it to understand the expected behavior when interacting with the project.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements
- Java and MySQL for providing the foundational technologies for this project.

## Contact
For any inquiries or issues, please contact us at [support@sillyskies.com](mailto:haafizshamnad@gmail.com).

---

Thank you for using Silly Skies! We hope it helps streamline your airline management processes.
