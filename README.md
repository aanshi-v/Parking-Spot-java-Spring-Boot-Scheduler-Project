# Parking Spot Management System
---


---
This application is designed to manage office parking spots, where:

Employees can release their parking spot if they’re not using it for a few days (for example, if they’re on vacation).

Other employees can raise a 1-day booking request for those released spots.

A scheduler automatically approves or rejects these booking requests based on availability and date match.

---

### 1) Release Spot API
The employee who owns a parking spot can release it for a specific date range by providing:

Spot number

Employee ID

Start date

End date

Reason

This makes the spot temporarily available to others.

### 2) Raise Booking Request API
Another employee can raise a booking request for exactly 1 day — for example, 10 July to 11 July.
The system ensures only 1-day bookings are allowed and rejects any longer durations.

### 3) Auto Allocation (Scheduler)
A Spring Boot scheduler runs in the background every 30 seconds (for testing) or at a fixed time (like 9 AM daily).

It automatically checks all pending booking requests, and:

Approves them if the spot is available and within the release window

Rejects them otherwise

---

### Tech Stack
Java 17+

Spring Boot 3.x

JPA (Hibernate)

MySQL

REST API

Spring Scheduler

Lombok

Global Exception Handling

---

### Example Flow
I’ll now demonstrate:

Creating an employee

Assigning a parking spot

Releasing the spot

Booking that spot for a single day

Running the scheduler to auto-approve the request
