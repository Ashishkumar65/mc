# 🚆 Train Ticket Booking System - README

## 📜 Problem Statement
Design a Train Ticket Booking System that allows users to:

- 🔍 Search for trains from one station to another on a given date.
- 💺 View available seats for a train between two stations.
- 🎟 Book tickets from a source to a destination.
- 🔄 Handle partial bookings (e.g., Seat A1 booked from A→C can still be used from C→F).
- 🔐 Ensure thread safety when multiple users book concurrently.
- ❌ Prevent overbooking and manage seat allocation accurately.
- 🔀 Support multiple trains, users, and complex routes (5+ stations).
- 🧾 Track booking history.
- 🔎 Support querying for seat availability and train schedules.

---

## 📌 Functional Requirements

### ✅ Core Features

- **Train Route Management**:
    - Ordered list of stations that defines a train’s path.

- **Seat Classes**:
    - Support for different seat types (e.g., AC, Sleeper).

- **Train Schedules**:
    - Daily instances of trains with separate seat availability.

- **Train Search**:
    - Search trains by source station, destination station, and date.

- **Seat Booking**:
    - Book seat between any two valid route stations.
    - Avoid conflicts with overlapping bookings.

- **Seat Availability**:
    - Check which seats are free for a given segment of the route.

- **Concurrency Handling**:
    - Safe booking under concurrent requests using synchronized locking.

- **Booking History**:
    - Maintain user-level records of successful bookings.

---

## 🧱 System Design Overview

### 🧩 Components
- **Station**: Represents a physical train station.
- **Train**: Contains route and seat configuration.
- **Route**: Ordered list of `TrainRouteSegment` objects.
- **Seat**: Identified by seat number and class.
- **User**: Represents a customer making bookings.
- **TrainSchedule**: A train’s seat layout for a specific date.
- **BookingSegment**: A booking from one station to another.
- **Booking**: Represents a ticket reservation.

### 🔒 Thread Safety
- Uses `synchronized` methods in `TrainSchedule` to ensure atomic seat booking operations.

---

## 🔍 Usage Scenarios

- Search for all available trains between Station A and Station F on a date.
- View available seats for Train T1 between Station B and Station E.
- Book a seat between Station A and Station C.
- Prevent booking if overlapping segment is already booked.
- Handle concurrent booking attempts from multiple users.

---

## 🚫 Out of Scope (for current version)

- ❌ Payment and Refunds
- ❌ Booking Cancellations
- ❌ User Authentication
- ❌ Admin Panel for Train Management

---


Happy Booking! 🚄