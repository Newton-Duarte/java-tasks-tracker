# Task Tracker API

A robust REST API for managing tasks and task lists to help users stay organized and boost productivity.

## Overview

The Task Tracker API is designed to help users organize their daily tasks, set priorities, and track their progress. This application provides a user-friendly way for creating, managing, and completing tasks, helping users to increase their productivity and stay organized.

## Key Concepts

- **Task**: A specific action item or to-do that a user wants to complete. It typically includes a title, description, due date, and priority level.
- **Task List**: A collection of related tasks, grouped together for organizational purposes (e.g., "Work Tasks", "Personal Errands", "Project X").

## Technology Stack

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **Spring JPA**: Data persistence
- **PostgreSQL**: Database

## Features

- Create, read, update, and delete tasks
- Organize tasks into customizable lists
- Set priority levels and due dates
- Track task completion status
- Filter and search tasks (TODO...)

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher
- PostgreSQL 14 or higher

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/newton-duarte/java-tasks-tracker.git
   cd java-tasks-tracker
   ```

2. Configure database connection in `application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/tasksapidb
   spring.datasource.username=docker
   spring.datasource.password=docker
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## API Endpoints

### Tasks

- `GET /tasks-lists/{task_list_id}/tasks` - Retrieve all tasks
- `GET /tasks-lists/{task_list_id}/tasks/{id}` - Get a specific task
- `POST /tasks-lists/{task_list_id}/tasks` - Create a new task
- `PUT /tasks-lists/{task_list_id}/tasks/{id}` - Update an existing task
- `DELETE /tasks-lists/{task_list_id}/tasks/{id}` - Delete a task

### Task Lists

- `GET /tasks-lists` - Retrieve all task lists
- `GET /tasks-lists/{id}` - Get a specific task list
- `POST /tasks-lists` - Create a new task list
- `PUT /tasks-lists/{id}` - Update an existing task list
- `DELETE /tasks-lists/{id}` - Delete a task list

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── newtonduarte.tasks/
│   │           ├── controllers/
│   │           ├── domain/
│   │           ├── repositories/
│   │           ├── services/
│   │           └── TasksApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── newtonduarte.tasks/
                └── ...
```

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Made with ❤️ by Newton Duarte