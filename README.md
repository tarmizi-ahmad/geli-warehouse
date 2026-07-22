# Warehouse Management API

A RESTful API built with Spring Boot 3 for managing warehouse inventory.

This project was created as a technical assessment for a Java Backend Developer position.

---

# Features

- Manage Items
- Manage Item Variants
- Track Inventory Stock
- Product Pricing
- Prevent Selling Out-of-Stock Items
- Global Exception Handling
- Request Validation
- Swagger/OpenAPI Documentation
- H2 Database
- Sample Data Seeder

---

# Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Lombok
- Spring Validation
- Springdoc OpenAPI (Swagger)

---

# Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
H2 Database
```

Project Structure

```
src
└── main
    ├── java
    │   └── com.geli.warehouse
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       │   ├── request
    │       │   └── response
    │       ├── entity
    │       ├── exception
    │       ├── mapper
    │       ├── repository
    │       ├── service
    │       │   └── impl
    │       └── util
    └── resources
```

---

# Database Design

## Item

| Column | Type |
|---------|------|
| id | Long |
| name | String |
| description | String |
| created_at | Timestamp |
| updated_at | Timestamp |

Relationship

```
One Item
      │
      │ One-To-Many
      ▼
Many Variants
```

## Variant

| Column | Type |
|---------|------|
| id | Long |
| sku | String |
| color | String |
| size | String |
| price | BigDecimal |
| stock | Integer |
| item_id | Long |

---

# Business Rules

- One Item can have multiple Variants.
- Every Variant has its own SKU.
- SKU must be unique.
- Stock is managed per Variant.
- Price is managed per Variant.
- Selling more than available stock is not allowed.
- Quantity sold must be greater than zero.

---

# API Endpoints

## Item

| Method | Endpoint |
|---------|----------|
| GET | /api/items |
| GET | /api/items/{id} |
| POST | /api/items |
| PUT | /api/items/{id} |
| DELETE | /api/items/{id} |

---

## Variant

| Method | Endpoint |
|---------|----------|
| GET | /api/items/{itemId}/variants |
| GET | /api/variants/{id} |
| POST | /api/items/{itemId}/variants |
| PUT | /api/variants/{id} |
| DELETE | /api/variants/{id} |

---

## Stock

| Method | Endpoint |
|---------|----------|
| POST | /api/variants/{id}/sell |

---

# Sample Request

## Create Item

POST

```
/api/items
```

```json
{
  "name":"Nike Air Max",
  "description":"Running Shoes"
}
```

---

## Create Variant

POST

```
/api/items/1/variants
```

```json
{
  "sku":"NIKE-BLK-42",
  "color":"Black",
  "size":"42",
  "price":750000,
  "stock":10
}
```

---

## Sell Product

POST

```
/api/variants/1/sell
```

```json
{
  "quantity":2
}
```

---

# Sample Error Response

```json
{
    "timestamp":"2026-07-22T20:30:00",
    "status":400,
    "error":"Bad Request",
    "message":"Stock is insufficient",
    "path":"/api/variants/1/sell"
}
```

---

# Sample Data

The application automatically inserts sample data during startup.

Items

- Nike Air Max

Variants

- Black Size 40
- Black Size 41
- White Size 40

---

# Swagger

After the application starts:

```
http://localhost:8080/swagger-ui/index.html
```

---

# H2 Console

```
http://localhost:8080/h2-console
```

JDBC URL

```
jdbc:h2:file:./data/warehouse
```

Username

```
sa
```

Password

```
(empty)
```

---

# How to Run

Clone repository

```
git clone <repository-url>
```

Go to project

```
cd warehouse-management
```

Run application

```
mvn spring-boot:run
```

Application

```
http://localhost:8080
```

---

# Design Decisions

- Layered Architecture (Controller → Service → Repository)
- DTO used to separate API models from persistence models.
- Entity Mapper used to convert Entity to DTO.
- Global Exception Handler for consistent error responses.
- Spring Validation for request validation.
- H2 Database for lightweight persistence.
- Swagger/OpenAPI for API documentation.
- CommandLineRunner used to generate sample data automatically.

---

# Assumptions

- One Item can have multiple Variants.
- Stock is stored per Variant.
- SKU is unique across all Variants.
- Selling reduces stock immediately.
- Authentication and authorization are out of scope for this assessment.

---

# Future Improvements

- Authentication with Spring Security.
- Pagination and sorting.
- Search and filtering.
- Unit and Integration Tests.
- Docker support.
- Audit logging.
- Inventory transaction history.
- Stock adjustment endpoint.

---

# Author

Java Backend Technical Assessment

Developed using Spring Boot 3 and Java 21.