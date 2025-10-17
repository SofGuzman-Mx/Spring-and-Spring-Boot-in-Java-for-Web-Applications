## **Online Store - Order Management Service**
This project is a simple RESTful web service for managing orders in an online store, built with Spring Boot 3.

Features<br/>
- CRUD Operations: Create, Read, Update, and Delete orders.<br/>
- RESTful API: A clean and intuitive API for order management.<br/>
- Database Integration: Uses H2 for development and supports PostgreSQL for production.<br/>
- Layered Architecture: Follows a standard Controller-Service-Repository architecture.<br/>
- Java 17: Built using Java 17.<br/>

**Project Structure**<br/>
The project follows a standard Maven project structure:




## Menu
- [Sprint 1](#sprint-1)
- [Sprint 2](#sprint-2)
- [Sprint 3](#sprint-3)

## Project Structure
The project follows a standard Maven project structure:
````
.
├── pom.xml                # Maven configuration
├── startup.sh             # Script to run the application
├── postman_collection.json # Postman collection for testing
├── DECISIONS.md           # Documentation of key decisions
└── src
├── main
│   ├── java
│   │   └── com/example/onlinestore
│   │       ├── OnlineStoreApplication.java # Main application class
│   │       ├── controller                  # REST Controllers
│   │       ├── model                       # JPA Entities
│   │       ├── repository                  # Spring Data Repositories
│   │       └── service                     # Business logic services
│   └── resources
│       └── application.properties          # Application configuration
└── test                                    # Test sources
````
## Prerequisites

Java 17 or later

Apache Maven 3.6+

(Optional for Prod) PostgreSQL database installed and running.
## Sprint 1
**How to run**
1.Clone the repository
2.Build the project

````
mvn clean install
````

3.Run the Application
First, make the script executable:
````
chmod +x starup.sh
````
Then, run it:
````
./startup.sh
````
Running directly:
````
java -jar target/online-store-0.0.1-SNAPSHOT.jar
````

## API Endpoints
The base URL for the API is `/api/orders`
````
POST: Create a new order
GET: Get all orders
PUT: Get a single order by its ID
DELETE: Delete an order
````
## Sprint 2

## Sprint 3
