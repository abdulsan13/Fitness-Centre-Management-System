# Fitness Centre Management System

A fitness centre management web application built with Java Spring Boot and Thymeleaf.

## Features

- View class timetable
- Register for fitness sessions
- Cancel registrations
- Add new sessions (Admin)

## Tech Stack

- Java 21
- Spring Boot 3.2
- Thymeleaf (HTML templates)
- Maven

## How to Run

1. Open the project in IntelliJ IDEA or VS Code
2. Ensure Java 21 is installed
3. Run `FitAllApplication.java`
4. Open http://localhost:8080 in your browser

## Project Structure

```
src/main/java/com/fitall/
├── FitAllApplication.java    # Main application
├── Session.java              # Session model
└── SessionController.java    # Web controller

src/main/resources/
├── application.properties     # Config
├── static/css/style.css      # Styles
└── templates/               # HTML pages
    ├── timetable.html
    └── add-session.html
```
