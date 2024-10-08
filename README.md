# Statement API

This project is a Spring Boot application that provides an API for managing account statements. It is built using Maven.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Features

- Retrieve all accounts
- Fetch statements for an account over the past three months
- Fetch statements for an account by date and amount range
- Error handling for invalid parameters

## Requirements

- Java 11+
- Maven 3.6+
- Spring Boot 2.5+

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/statement-api.git
   cd statement-api
   ```

2. **Build the project using Maven:**

   ```bash
   mvn clean install
   ```

## Running the Application

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Get All Accounts

- **URL:** `/api/statement/`
- **Method:** `GET`
- **Authorization:** Basic Auth (user/admin roles)

### Get Statements for Account

- **URL:** `/api/statement/{id}`
- **Method:** `GET`
- **Authorization:** Basic Auth (user/admin roles)

### Get Statements by Date and Amount Range

- **URL:** `/api/statement/{id}/by-amount-date-range`
- **Method:** `GET`
- **Query Parameters:**
  - `fromAmount` (optional)
  - `toAmount` (optional)
  - `fromDate` (optional, format: `dd.MM.yyyy`)
  - `toDate` (optional, format: `dd.MM.yyyy`)
- **Authorization:** Basic Auth (admin role)

## Testing

Run the tests using:

```bash
mvn test
```


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
