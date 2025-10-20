## **Online Store - Order Management Service**
This project is a simple RESTful web service for managing orders in an online store, built with Spring Boot 3.

Technologies Used:
- **Java 17**
- **Spring Boot 3.0**
- **Spring Data JPA**
- **Apache Maven 3.6+**
- **H2 Database** (for Development)
- **PostgreSQL** (for Production)
- **OpenAPI (Swagger)** for API documentation
- **Project Structure**<br/>

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

## How to Run the Application

### 1. Clone the Repository
```bash
git clone <your-github-repo-url>
cd order-service
```
### 2. Running with the `dev` Profile (H2 Database)
This is the simplest way to run the application locally. It uses an in-memory H2 database.

```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```
The application will be available at `http://localhost:8080`.

### 3. Running with the `prod` Profile (PostgreSQL)
This profile requires a running PostgreSQL instance and environment variables to be set.

**Required Environment Variables:**
- `DB_USER`: The username for the PostgreSQL database.
- `DB_PASSWORD`: The password for the PostgreSQL database.

**Set Environment Variables (example for Linux/macOS):**
```bash
export DB_USER=your_db_user
export DB_PASSWORD=your_secret_password
```

**Run the application:**
```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```
## API Documentation
Once the application is running, you can access the interactive Swagger UI documentation here:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

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
- `POST /api/orders`: Create a new order.
- `GET /api/orders`: Get a list of all orders.
- `GET /api/orders/{id}`: Get a single order by its ID.
- `PUT /api/orders/{id}`: Update an existing order.
- `DELETE /api/orders/{id}`: Delete an order.

