Exam Portal Project This Exam Portal is a full-stack application designed to facilitate the creation, management, and participation in quizzes. The front end is developed using Angular, while the backend is powered by Spring Boot. PostgreSQL is used as the database for storing data. Below is a detailed description of the backend functionalities and my contributions to the project.

Key Features Admin Functionality:

Create and manage categories of quizzes. Organize quizzes and add questions to them. Assign different roles and permissions. User Functionality:

User registration and login. Participation in quizzes created by administrators. Automatic evaluation upon quiz completion. My Contributions to the Backend I was responsible for developing the backend of this project. Below are the key components and functionalities I worked on:

Data Transfer Objects (DTOs):

Created DTOs to encapsulate data and simplify data transfer between different layers of the application. Repositories:

Designed and implemented repository classes for data persistence and retrieval using Spring Data JPA. Entities:

Defined entity classes representing the core data structures stored in the PostgreSQL database. Services:

Developed service classes that contain business logic and interact with repositories to manage data operations. Controllers:

Created controller classes to handle HTTP requests and map them to appropriate service methods. Custom Exception Handling:

Implemented multiple custom exception classes to handle specific error scenarios. Developed a global exception handler to manage exceptions and provide meaningful error messages. Security:

Applied role-based security to control access to different parts of the application. Configured Spring Security to enforce authentication and authorization rules. Validation:

Applied necessary validations to ensure data integrity and consistency. By focusing on the backend, I ensured the application is robust, secure, and scalable, providing a solid foundation for the front-end developers to build upon.
