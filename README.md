
# 🩺 Doctor Appointment Booking System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)

## 📖 Project Overview

The Doctor Appointment Booking System is a full-stack web application developed to streamline appointment scheduling and clinic management for a single-doctor clinic. It enables patients to register, log in, book appointments, reschedule or cancel existing appointments, and view their appointment history through a simple and user-friendly interface.
The system provides an administrative dashboard that allows the clinic administrator to create and manage appointment slots, monitor bookings, and manage patient information efficiently. To enhance the user experience, automated email notifications are sent whenever an appointment is booked, rescheduled, or cancelled.
The backend is built using Java, Spring Boot, Spring Data JPA (Hibernate), and MySQL, following a layered architecture with RESTful APIs. The frontend is developed using HTML, CSS, and JavaScript, while JavaMail is integrated to deliver appointment notifications via email.

## 🚀 Key Highlights
- 👤 Patient Registration & Login
- 📅 Appointment Booking, Rescheduling & Cancellation
- 👨‍⚕️ Admin Dashboard for Clinic Management
- 📧 Automated Email Notifications
- 🗓️ Appointment Slot Management
- 🗄️ MySQL Database with Hibernate (JPA)
- 🔗 RESTful API Architecture
- 🏗️ Layered Architecture (Controller → Service → Repository)

## ✨ Features

### Patient Module

- Secure patient registration and login
- JWT-based authentication
- Book doctor appointments
- Cancel booked appointments
- View appointment history

### Admin Module

- Secure administrator login
- Add and manage appointment slots
- Monitor booked appointments
- View patient details
- Dashboard for clinic management

### System Features

- RESTful API architecture
- Role-based authorization
- Layered architecture (Controller → Service → Repository)
- Hibernate (JPA) integration
- MySQL database support
- Exception handling

---

# 🛠️ Tech Stack

| Category | Technologies |
|----------|--------------|
| **Programming Language** | Java 17 |
| **Backend Framework** | Spring Boot |
| **Database** | MySQL |
| **ORM** | Hibernate (Spring Data JPA) |
| **Frontend** | HTML, CSS, JavaScript |
| **Build Tool** | Maven |
| **Email Service** | JavaMail Sender (SMTP) |
| **API Testing** | Postman |
| **Version Control** | Git & GitHub |
| **IDE** | IntelliJ IDEA |

---

# 🏗️ System Architecture

The application follows a Layered Architecture to maintain separation of concerns, improve code maintainability, and simplify future enhancements. Each layer has a well-defined responsibility and communicates only with its adjacent layer.

```text
                     👤 Patient / 👨‍⚕️ Admin
                               │
                               ▼
                      HTML • CSS • JavaScript
                               │
                               ▼
                     Spring Boot REST Controllers
                               │
                               ▼
                        Service Layer
                     (Business Logic)
                               │
                               ▼
                    Repository Layer (JPA)
                               │
                               ▼
                         MySQL Database
```

### 📌 Layer Responsibilities

### 🎮 Controller Layer

- Receives HTTP requests from the client.
- Validates incoming requests.
- Delegates business operations to the Service layer.
- Returns appropriate HTTP responses.

---

### ⚙️ Service Layer

- Implements the application's business logic.
- Handles appointment booking, cancellation, slot availability, and email notifications.
- Coordinates communication between Controllers and Repositories.

---

### 💾 Repository Layer

- Performs database operations using Spring Data JPA.
- Provides CRUD operations for Patients, Appointments, Available Slots, and Admin.

---

### 🗄️ Database Layer

- Stores all application data in MySQL.
- Maintains relationships between Patients, Appointments, Available Slots, and Admin.

---

# 🗄️ Database Design

The application uses **MySQL** as the relational database and **Spring Data JPA (Hibernate)** as the ORM framework to efficiently manage entity relationships and database operations.

## 📋 Core Entities

| Entity | Description |
|---------|-------------|
| **Patient** | Stores patient profile, login credentials, and contact information. |
| **Admin** | Represents the clinic administrator (Doctor) responsible for managing appointments and available slots. |
| **Appointment** | Stores appointment details including patient, slot, status, and booking information. |
| **AvailableSlot** | Stores available appointment dates, time slots, and booking capacity. |

## 🔗 Entity Relationships

```text
                Admin (Doctor)
                       │
                       │ 1
                       │
                       ▼
              Available Slots
                       │
                       │ 1
                       │
                       ▼
                Appointment
                       ▲
                       │
                       │ Many
                       │
                   Patient
```

### Relationship Summary

- One **Admin** manages multiple **Available Slots**.
- One **Available Slot** can be associated with multiple appointments (based on slot capacity).
- One **Patient** can book multiple appointments.
- Each **Appointment** belongs to one patient and one available slot.

---

# 📡 REST API Documentation

The application exposes RESTful APIs for patient management, appointment scheduling, slot management, and administrator operations.

## 👤 Patient APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/patient/all` | Retrieve all registered patients |
| GET | `/patient/{id}` | Retrieve patient details by ID |
| GET | `/patient/search` | Search patient |
| POST | `/patient/addPatient` | Register a new patient |
| POST | `/patient/login` | Patient login |
| GET | `/patient/logout` | Patient logout |
| GET | `/patient/appointments/book` | Open appointment booking page |
| POST | `/patient/appointments/book` | Submit appointment booking |
| GET | `/patient/appointments/my` | View patient's appointments |

---

## 📅 Appointment APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/BookAppointment/book` | Book an appointment |
| PUT | `/BookAppointment/update` | Reschedule an appointment |
| PUT | `/BookAppointment/cancel/{appointmentId}` | Cancel an appointment |
| GET | `/BookAppointment/history` | View appointment history |
| GET | `/BookAppointment/history/session` | View appointment history using current session |

---

## 🗓️ Slot Management APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/slot/add` | Add a new appointment slot |
| GET | `/slot/all` | Retrieve all appointment slots |
| GET | `/slot/freeSlots` | Retrieve available slots |
| DELETE | `/slot/delete/{id}` | Delete an appointment slot |

---

## 👨‍⚕️ Admin APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin/login` | Admin login page |
| POST | `/admin/login` | Authenticate administrator |
| GET | `/admin/logout` | Administrator logout |
| GET | `/admin/appointments` | View all appointments |
| GET | `/admin/bookedCount` | Retrieve total booked appointments |
| GET | `/admin/cancelledCount` | Retrieve total cancelled appointments |

---

# ⚙️ Installation & Setup

## Prerequisites

- Java 17
- Maven
- MySQL 8+
- IntelliJ IDEA (Recommended)

## Clone Repository

```bash
git clone https://github.com/Sarwar001/Doctor-Appointment-Booking-System.git
```

## Navigate to Project

```bash
cd Doctor-Appointment-Booking-System
```

## Configure Database

Create a MySQL database.

```sql
CREATE DATABASE doctor_appointment_system;
```

Update your `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/doctor_appointment_system
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

## Run the Project

```bash
mvn spring-boot:run
```

or run the project directly from IntelliJ IDEA.

The application will start on

```
http://localhost:8080
```

---

# 📂 Project Structure

```
Appointment-Booking-System
│
├── src
│   ├── main
│   │   ├── java
│   │   │    ├── Controller
│   │   │    ├── DTO
│   │   │    ├── Entity
│   │   │    ├── Repository
│   │   │    ├── Service
│   │   │    └── AppointmentBookingSystemApplication.java
│   │   │
│   │   └── resources
│   │        ├── static
│   │        ├── templates
│   │        └── application.properties
│
├── pom.xml
└── README.md
```

---

# 📧 Email Notification Workflow

The system automatically sends email notifications using **JavaMail Sender (SMTP)** for important appointment events.

### Supported Notifications

- ✅ Appointment Booked
- ✅ Appointment Rescheduled
- ✅ Appointment Cancelled

### Workflow

```
Patient Books Appointment
            │
            ▼
Appointment Stored in MySQL
            │
            ▼
Notification Service
            │
            ▼
JavaMail Sender (SMTP)
            │
            ▼
Confirmation Email Sent
```

This helps patients stay informed about their appointments and improves communication between the clinic and patients.

---
# 🔮 Future Enhancements

- JWT Authentication using Spring Security
- Role-Based Authorization
- Password Reset via Email
- Multi-Doctor Support
- Online Payment Integration
- SMS Notifications
- Docker Deployment
- Swagger API Documentation
- Unit & Integration Testing
- Cloud Deployment (AWS)

---

# 🎯 Learning Outcomes

During this project, I gained practical experience in:

- Spring Boot Development
- REST API Development
- Spring Data JPA & Hibernate
- MySQL Database Design
- Layered Architecture
- DTO Pattern
- CRUD Operations
- Session-Based Authentication
- JavaMail Sender Integration
- Git & GitHub
- API Testing with Postman

---

# 👨‍💻 Author

**Gulam Sarwar**

Java Backend Developer

📧 Email: gulamsarwar5032@gmail.com

🔗 LinkedIn: https://www.linkedin.com/in/

💻 GitHub: https://github.com/Sarwar001

---