<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hostel Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 30px;
        }
        h1, h2 {
            margin-bottom: 20px;
        }
        .list-group-item {
            padding: 10px 20px;
        }
        .section-title {
            font-size: 1.5em;
            margin-top: 20px;
            color: #007bff;
        }
        pre {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Hostel Management System</h1>
        <p class="lead text-center">A web-based application to manage hostel operations with role-based authentication and authorization using JWT.</p>
        <h2>Project Overview</h2>
        <p>The **Hostel Management System** allows administrators, staff, and students to interact with the system to manage hostel operations. The system is built using **Spring Boot** for the back-end, **HTML**, **CSS**, **JavaScript**, and **Bootstrap** for the front-end, and a **MySQL** database for data storage. Authentication and authorization are handled through **JWT (JSON Web Token)** to ensure that only authorized users can access specific parts of the system based on their roles.</p>
        <h2>Features</h2>
        <ul class="list-group">
            <li class="list-group-item"><strong>User Authentication:</strong> JWT-based token authentication for secure login and role-based access control.</li>
            <li class="list-group-item"><strong>Role-Based Access Control (RBAC):</strong> Different user roles (Admin, Student, Dean, Warden, Sub-Warden) have different levels of access.</li>
            <li class="list-group-item"><strong>Admin Panel:</strong> Full access for managing students, rooms, assignments, and roles.</li>
            <li class="list-group-item"><strong>Student Dashboard:</strong> Students can view room allocations and request room assignments.</li>
            <li class="list-group-item"><strong>Room Management:</strong> Admin and Warden roles can manage room availability and assignments.</li>
        </ul>
        <h2>Technologies Used</h2>
        <ul class="list-group">
            <li class="list-group-item"><strong>Back-End:</strong> Spring Boot, Spring Security, JPA/Hibernate, JWT, MySQL</li>
            <li class="list-group-item"><strong>Front-End:</strong> HTML, CSS, JavaScript, Bootstrap</li>
            <li class="list-group-item"><strong>Database:</strong> MySQL</li>
            <li class="list-group-item"><strong>Tools:</strong> Maven for dependency management, Postman for API testing</li>
        </ul>
        <h2>System Requirements</h2>
        <ul class="list-group">
            <li class="list-group-item">Java 11 or higher</li>
            <li class="list-group-item">Spring Boot 2.x</li>
            <li class="list-group-item">MySQL or PostgreSQL database</li>
            <li class="list-group-item">Maven for project dependencies</li>
        </ul>
        <h2>Installation Guide</h2>
        <ol class="list-group">
            <li class="list-group-item">Clone the repository: 
                <pre>git clone https://github.com/yourusername/hostel-management-system.git</pre>
            </li>
            <li class="list-group-item">Set up your MySQL database and create a database named `hostel_management`.</li>
            <li class="list-group-item">Update the database credentials in the `application.properties` file located in `src/main/resources`:</li>
            <pre>
                spring.datasource.url=jdbc:mysql://localhost:3306/hostel_management
                spring.datasource.username=root
                spring.datasource.password=yourpassword
                spring.jpa.hibernate.ddl-auto=update
                spring.jpa.show-sql=true
            </pre>
            <li class="list-group-item">Build the project using Maven:</li>
            <pre>mvn clean install</pre>
            <li class="list-group-item">Run the application using the command:</li>
            <pre>mvn spring-boot:run</pre>
            <li class="list-group-item">Access the application in your browser at <strong>http://localhost:8080</strong></li>
        </ol>
        <h2>Usage</h2>
        <h3>Authentication</h3>
        <p>Users must authenticate using the <code>/api/auth/login</code> endpoint by providing a username and password.</p>
        <pre>
            POST /api/auth/login
            Request Body:
            {
                "username": "admin",
                "password": "password123"
            }
        </pre>
        <p>Upon successful authentication, a JWT token will be returned in the response:</p>
        <pre>
            Response:
            {
                "accessToken": "your_jwt_token_here"
            }
        </pre>
        <p>The token should be included in the `Authorization` header as a Bearer token in all subsequent requests to access protected routes.</p>
        <h2>Roles and Permissions</h2>
        <p>The system includes the following roles:</p>
        <ul class="list-group">
            <li class="list-group-item"><strong>Admin:</strong> Full access to manage students, rooms, and assignments.</li>
            <li class="list-group-item"><strong>Student:</strong> Can view their own room assignments and make requests.</li>
            <li class="list-group-item"><strong>Dean:</strong> Can manage room assignments for students and oversee hostel operations.</li>
            <li class="list-group-item"><strong>Warden:</strong> Can assign rooms to students and manage room availability.</li>
            <li class="list-group-item"><strong>Sub-Warden:</strong> Limited permissions under a specific warden's supervision.</li>
        </ul>
        <h2>Contributing</h2>
        <p>We welcome contributions to improve this project! To contribute:</p>
        <ol class="list-group">
            <li class="list-group-item">Fork the repository on GitHub.</li>
            <li class="list-group-item">Clone your fork locally and create a new branch.</li>
            <li class="list-group-item">Make your changes and commit them with descriptive messages.</li>
            <li class="list-group-item">Push your changes to your fork and create a pull request.</li>
        </ol>
        <h2>License</h2>
        <p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for more information.</p>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>


![image](https://github.com/user-attachments/assets/2e5670b1-f84c-43f5-ba32-51f08ddcd95e)
![image](https://github.com/user-attachments/assets/46ac19b1-f097-425b-b428-3aeb2e55b7b8)
![image](https://github.com/user-attachments/assets/f53eef12-5aee-4e4d-807a-645b5c565869)
![image](https://github.com/user-attachments/assets/611c306c-fed9-4918-bed2-6344503439e2)
![image](https://github.com/user-attachments/assets/cd8ed472-b84e-4abe-9fa3-159e4be5574e)
