// Function to load student details into the table
import {AuthService} from "./authservice.js";

loadStudentDetails().then(r => null);
async function loadStudentDetails() {
    const tableBody = document.querySelector("#student-table-body");

    const auth = new AuthService();
    try {
        // Fetch student data from the backend API and parse it as JSON
        const students = await fetch("http://localhost:8080/hostalmanage/warden/getStudent", {
            method: "GET",
            headers: auth.createAuthorizationHeader(),
        }).then(response => response.json());

        // Clear existing rows (if any)
        tableBody.innerHTML = "";

        // Populate rows dynamically
        students.forEach(student => {
            const row = document.createElement("tr");

            // Create table cells for each student property
            const idCell = document.createElement("td");
            idCell.textContent = student.student_id;

            const indexNumberCell = document.createElement("td");
            indexNumberCell.textContent = student.tg_no || "-";

            const firstNameCell = document.createElement("td");
            firstNameCell.textContent = student.fname || "-";

            const dobCell = document.createElement("td");
            dobCell.textContent = student.dob || "-";

            const enrollmentDateCell = document.createElement("td");
            enrollmentDateCell.textContent = student.enrollmentDate || "-";

            const departmentCell = document.createElement("td");
            departmentCell.textContent = student.department || "-";

            const phoneCell = document.createElement("td");
            phoneCell.textContent = student.phoneNo || "-";

            const emailCell = document.createElement("td");
            emailCell.textContent = student.email || "-";

            const addressCell = document.createElement("td");
            addressCell.textContent = student.address || "-";

            // Append cells to the row
            row.appendChild(idCell);
            row.appendChild(indexNumberCell);
            row.appendChild(firstNameCell);
            row.appendChild(dobCell);
            row.appendChild(enrollmentDateCell);
            row.appendChild(departmentCell);
            row.appendChild(phoneCell);
            row.appendChild(emailCell);
            row.appendChild(addressCell);

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load student details:", error);
        tableBody.innerHTML = "<tr><td colspan='10'>Failed to load student data.</td></tr>";
    }
}
