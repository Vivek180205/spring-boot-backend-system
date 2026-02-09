# Spring Boot Backend System

A Java Spring Boot backend application demonstrating real-world backend practices
including authentication, role-based access, REST APIs, and database integration.

## Features
- Session-based authentication
- Role-based authorization (ADMIN / USER)
- CRUD APIs
- PostgreSQL integration using JDBC/JdbcTemplate
- Centralized exception handling
- Clean layered architecture

## Authentication & Security

This application implements secure authentication and authorization using:

- JWT-based authentication for username/password login
- OAuth2 (Google) login for third-party authentication
- OAuth2 login issues a JWT for stateless API authorization
- Role-based access control (USER, ADMIN)

All secured endpoints require a valid JWT token in the `Authorization` header.


## Tech Stack
- Java
- Spring Boot
- PostgreSQL
- JDBC / JdbcTemplate
- Git & GitHub

## Project Structure
Controller → Service → DAO → Database

## How it works
The application exposes REST APIs that handle requests through controllers,
process business logic in services, interact with the database via DAO layer,
and return standardized JSON responses.
