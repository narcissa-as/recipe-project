# Spring 5 Recipe Application

A fully functional Spring MVC web application for managing recipes, ingredients, units of measure, and related resources.  
The project demonstrates clean architecture, layered design, domain-driven modeling, and a complete CRUD workflow with form handling, image upload, validation,
and custom exception management.

---

## 🚀 Features

### 🔹 Recipe Management
- Create / Read / Update / Delete recipes  
- Add and manage ingredients  
- Add and display notes  
- Upload and store recipe images  
- View detailed recipe pages

### 🔹 Ingredient & Unit Management
- Manage ingredients for each recipe  
- Associate ingredients with Units of Measure  
- Many-to-many and one-to-many relationship handling  

### 🔹 UI Layer
- Server-side rendering with Thymeleaf  
- Form binding and validation  
- Custom error pages (400, 404)  
- Bootstrap-based responsive layout (CDN)

### 🔹 Backend Architecture
- Domain-driven entity modeling  
- Service layer abstraction  
- Repository pattern using Spring Data JPA  
- Command objects and converters for mapping between domain and web layers  
- Centralized exception handling

---

## 🛠️ Tech Stack

### **Backend**
- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- Hibernate  

### **Frontend**
- Thymeleaf  
- HTML / CSS  
- Bootstrap (via CDN)

### **Other**
- Lombok  
- H2 database (default)  
- MySQL compatible  
- Maven  
- JUnit 5 & Mockito

---

## 🧩 Domain Model
Includes several bidirectional and unidirectional relationships:

- **Recipe ↔ Ingredient** (One-to-Many)  
- **Ingredient ↔ UnitOfMeasure** (Many-to-One)  
- **Recipe ↔ Notes** (One-to-One)  
- **Recipe ↔ Category** (Many-to-Many)

The domain model is optimized for clarity, immutability where needed, and easy mapping to database tables via JPA/Hibernate.

---

## 🧠 Architecture Overview

### **Controllers**
Handle HTTP requests, perform validation, communicate with services, and determine view rendering.

### **Services**
Provide business logic for recipes, ingredients, units of measure, and image storage.

### **Repositories**
Extend Spring Data JPA to handle persistence with minimal boilerplate.

### **Command Objects**
Used to safely transfer data between controllers and templates.

### **Converters**
Map between domain objects and command objects for clean separation of concerns.

---

## 📁 Project Structure
/
├── java/com/my/spring5recipeapp
│ ├── controllers
│ ├── domain
│ ├── services
│ ├── repositories
│ ├── converters
│ ├── commands
│ ├── exceptions
│ └── bootstrap
└── resources
├── templates
├── static
├── application.properties
├── data.sql
└── messages.properties

---

## 🧪 Testing

- Unit tests for services  
- Converter tests  
- Integration tests loading Spring application context  
- Mockito used for mocking and verifying interactions  

Run all tests:
```bash
./mvnw test

Running the Application
Using Maven 
./mvnw spring-boot:run

Or build the JAR:

./mvnw clean package
java -jar target/spring5-recipe-app-0.0.1-SNAPSHOT.jar

Database:
Default: H2 in-memory
Can be switched to MySQL by updating application.properties

