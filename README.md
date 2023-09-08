# Demo Application - REST Web Service with CQRS Architecture

This is a demo application that simulates a REST web service built with the CQRS (Command Query Responsibility Segregation) architecture.

## Getting Started

To start up the application, follow these steps:

1. Start the Axon Server using Docker Compose:
   ```
   docker-compose up
   ```

   This command will launch the Axon Server, which is used for managing events and commands in the CQRS architecture.

2. Run the application using Maven (Make sure you have Maven installed):
   ```
   mvn spring-boot:run
   ```

   This command will start the demo application.

## Exposed Endpoints

The following endpoints are exposed by the application:

### GET http://localhost:8080/api/v1/transactions

Use this endpoint to retrieve a list of transactions.

**Example Response:**

```json
[
    {
        "id": "2eebd1e1-e6c7-42a4-ab86-2814091fd58a",
        "amount": "300.00",
        "date": "2023-09-08T16:40:07+0000",
        "maskedPan": "71159-539XX-XXX-53508",
        "status": "NEW",
        "transactionRef": "9aa5bfff-4eb0-46ac-96d3-2630dbcae39b"
    }
]
```

### POST http://localhost:8080/api/v1/transactions

Use this endpoint to create a new transaction.

**Example Request Body:**

```json
{
    "amount": 300
}
```

**Example Response:**

```json
370603b7-c600-49d7-aa1f-91ca05dcf609
```

---

This README provides the necessary information to run and use the demo application, including the endpoints, request and response examples. Make sure you have Docker Compose and Maven installed to successfully run the application.
