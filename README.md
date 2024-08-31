# Todo-It Application with JDBC Integration

## Overview

This project is a Java-based Todo application refactored to use JDBC (Java Database Connectivity) for database operations. The primary goal was to implement the People and TodoItems interfaces, which interact with a MySQL database to perform CRUD (Create, Read, Update, Delete) operations on Person and TodoItem entities.

Assignment Overview
The assignment focused on refactoring the existing Todo application to utilize JDBC for database interactions. The specific tasks included:

Create Database: Set up the MySQL database using the provided todoit.sql script.
Clone/Branch Application: Work on a cloned or branched version of the existing Todo application.
Ensure JDBC Drivers: Verify that the necessary JDBC drivers are included in the project.
Refactor Models: Align the Person and TodoItem models with the database schema.
Implement Interfaces: Implement the People and TodoItems interfaces according to the provided UML diagram.
Test the Application: Ensure that all CRUD operations function correctly with the database.
Workshop Overview
In addition to the assignment, a workshop exercise was completed to further solidify understanding of JDBC concepts. This exercise involved:

Creating a CityDaoJDBC Class: Implemented a class that interacts with the "world" database provided by MySQL.
Implementing CRUD Operations: Developed methods for creating, reading, updating, and deleting City records using JDBC.
Using PreparedStatements: Ensured that all queries with parameters were handled using PreparedStatement to prevent SQL injection and improve performance.


## UML Diagram
The project structure follows the UML diagram provided below:



```plaintext
+---------------------------------+       +-------------------------------------+
| <<interface>>                   |       | <<interface>>                       |
|            People               |       |             TodoItems               |
|---------------------------------|       |-------------------------------------|
| + create(Person): Person        |       | + create(Todo): Todo                |
| + findAll(): Collection<Person> |       | + findAll(): Collection<Todo>       |
| + findById(int): Person         |       | + findById(int): Todo               |
| + findByName(String): Collection|       | + findByDoneStatus(boolean):        |
|   <Person>                      |       |   Collection<Todo>                  |
| + update(Person): Person        |       | + findByAssignee(int):              |
| + deleteById(int): boolean      |       |   Collection<Todo>                  |
+---------------------------------+       | + findByAssignee(Person):           |
                                          |   Collection<Todo>                  |
                                          | + findByUnassignedTodoItems():       |
                                          |   Collection<Todo>                  |
                                          | + update(Todo): Todo                |
                                          | + deleteById(int): boolean          |
                                          +-------------------------------------+

