# Customer Management API — RESTful API Design & Swagger

A layered **Spring Boot** RESTful application that provides full CRUD operations for
managing `Customer` data, backed by a real relational database (**Oracle Database XE**)
and documented with **OpenAPI / Swagger UI**.

> i2i Academy homework — *RESTful API Design & Swagger*.

## Tech Stack

| Concern            | Choice                                            |
|--------------------|---------------------------------------------------|
| Language / Runtime | Java 21                                            |
| Framework          | Spring Boot 3.3.x (Web, Data JPA, Validation)     |
| Database           | Oracle Database XE 21c (Docker, `gvenzl/oracle-xe`)|
| API Docs           | springdoc-openapi (Swagger UI)                    |
| Build              | Maven                                             |

## Architecture

The project follows a classic **layered architecture** with a strict separation
between the persistence model (JPA entity) and the API model (DTOs):

```
Controller  ->  Service (interface + impl)  ->  Repository  ->  Oracle XE
   |                    |                            |
 DTOs  <----  Mapper  ----+                       Entity
```

- **Controller** (`CustomerController`) — HTTP layer, request validation, Swagger annotations.
- **Service** (`CustomerService` / `CustomerServiceImpl`) — business rules, transactions, uniqueness checks.
- **Repository** (`CustomerRepository`) — Spring Data JPA data access.
- **Entity** (`Customer`) — internal DB model, never exposed to clients.
- **DTOs** (`CustomerRequestDTO`, `CustomerResponseDTO`) — API contract.
- **Mapper** (`CustomerMapper`) — entity ⇄ DTO translation.
- **Exception handling** (`GlobalExceptionHandler`) — consistent error responses.

## Endpoints

Base path: `/api/v1/customers`

| Method   | Path                        | Description               | Success |
|----------|-----------------------------|---------------------------|---------|
| `POST`   | `/api/v1/customers`         | Create a new customer     | 201     |
| `GET`    | `/api/v1/customers/{id}`    | Retrieve a customer by ID | 200     |
| `GET`    | `/api/v1/customers`         | Retrieve all customers    | 200     |
| `PUT`    | `/api/v1/customers/{id}`    | Update an existing customer | 200   |
| `DELETE` | `/api/v1/customers/{id}`    | Delete a customer         | 204     |

## Running

### 1. Start Oracle XE (Docker)

```bash
docker run -d --name i2i-oracle-xe \
  -p 1521:1521 \
  -e ORACLE_PASSWORD=oracle \
  -e APP_USER=i2i_app \
  -e APP_USER_PASSWORD=i2i_pass \
  gvenzl/oracle-xe:21-slim
```

Wait until the container reports `healthy` (`docker ps`).

### 2. Run the application

```bash
mvn spring-boot:run
```

Connection settings default to the values below and can be overridden with
environment variables (`ORACLE_URL`, `ORACLE_USER`, `ORACLE_PASSWORD`):

```
url:      jdbc:oracle:thin:@localhost:1521/XEPDB1
username: i2i_app
password: i2i_pass
```

The `CUSTOMERS` table is created automatically on startup
(`spring.jpa.hibernate.ddl-auto=update`). See
[`schema-reference.sql`](src/main/resources/schema-reference.sql) for the resulting DDL.

### 3. Open Swagger UI

- Swagger UI: <http://localhost:8082/swagger-ui.html>
- OpenAPI JSON: <http://localhost:8082/v3/api-docs>

## Example request

```bash
curl -X POST http://localhost:8082/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{
        "firstName": "Ada",
        "lastName": "Lovelace",
        "email": "ada.lovelace@example.com",
        "phone": "+90 555 123 45 67",
        "city": "Istanbul"
      }'
```

## Theoretical Knowledge

**What is the primary purpose of using Swagger (OpenAPI Specification) in modern RESTful applications?**
Swagger/OpenAPI provides a standardized, machine-readable contract that describes every
endpoint, parameter, and response, and renders it as interactive documentation so that
developers and consumers can explore and test the API without extra tooling, while also
enabling automatic client/server code generation.

**Why is it a best practice to use DTOs instead of exposing database Entities directly?**
DTOs decouple the API contract from the persistence model, so internal database changes do
not break clients and sensitive or irrelevant fields are never leaked; they also let each
endpoint accept and return exactly the shape it needs, with validation and documentation
applied independently of the entity.
