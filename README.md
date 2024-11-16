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
            <li class="list-group-item">Java 17</li>
            <li class="list-group-item">Spring Boot </li>
            <li class="list-group-item">MySQL database</li>
            <li class="list-group-item">Maven for project dependencies</li>
        </ul>
        <h2>Installation Guide</h2>
        <ol class="list-group">
            <li class="list-group-item">Clone the repository: 
                <pre>https://github.com/BONY-SL/Hostal-Management-System</pre>
            </li>
            <li class="list-group-item">Set up your MySQL database and create a database named `hostel_management`.</li>
            <li class="list-group-item">Update the database credentials in the `application.properties` file located in `src/main/resources`:</li>
            <pre>
                spring.datasource.url=jdbc:mysql://localhost:3306/hostalmanagementsystem
                spring.datasource.username=root
                spring.datasource.password=yourpassword
                spring.jpa.hibernate.ddl-auto=update
                spring.jpa.show-sql=true
            </pre>
            <li class="list-group-item">Build the project using Maven:</li>
            <pre>mvn clean install</pre>
            <li class="list-group-item">Run the application using the InteliJ IDEA:</li>
            <li class="list-group-item">Access the application in your browser at <strong>http://localhost:8080</strong></li>
        </ol>
        <h2>Usage</h2>
        <h3>Authentication</h3>
        <p>Users must authenticate using the <code>/hostalmanage/auth/authenticate</code> endpoint by providing a username and password.</p>
        <pre>
            POST /hostalmanage/auth/authenticate
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
                "accessToken": "xxxxx",
                "Refresh_token": "xxxxx",
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
    </div>



![image](https://github.com/user-attachments/assets/2e5670b1-f84c-43f5-ba32-51f08ddcd95e)
![image](https://github.com/user-attachments/assets/46ac19b1-f097-425b-b428-3aeb2e55b7b8)
![image](https://github.com/user-attachments/assets/f53eef12-5aee-4e4d-807a-645b5c565869)
![image](https://github.com/user-attachments/assets/611c306c-fed9-4918-bed2-6344503439e2)
![image](https://github.com/user-attachments/assets/cd8ed472-b84e-4abe-9fa3-159e4be5574e)
