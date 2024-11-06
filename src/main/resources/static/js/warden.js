// Function to load student details into the table
async function loadStudentDetails() {
    const tableBody = document.querySelector("#student-table-body");

    try {
        // Fetch student data from the backend API
        const response = await fetch("http://localhost:8080/hostalmanage/getStudent/warden");
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        // Parse the JSON data
        const students = await response.json();

        // Clear existing rows (if any)
        tableBody.innerHTML = "";

        // Populate rows dynamically
        students.forEach(student => {
            const row = document.createElement("tr");

            // Create table cells for each student property
            const idCell = document.createElement("td");
            idCell.textContent = student.student_id;

            const indexNumberCell = document.createElement("td");
            indexNumberCell.textContent = student.index_number;

            const firstNameCell = document.createElement("td");
            firstNameCell.textContent = student.first_name;

            const lastNameCell = document.createElement("td");
            lastNameCell.textContent = student.last_name;

            const dobCell = document.createElement("td");
            dobCell.textContent = student.date_of_birth;

            const enrollmentDateCell = document.createElement("td");
            enrollmentDateCell.textContent = student.enrollment_date;

            const departmentCell = document.createElement("td");
            departmentCell.textContent = student.department;

            const phoneCell = document.createElement("td");
            phoneCell.textContent = student.phone;

            const emailCell = document.createElement("td");
            emailCell.textContent = student.email;

            const addressCell = document.createElement("td");
            addressCell.textContent = student.address;

            // Append cells to the row
            row.appendChild(idCell);
            row.appendChild(indexNumberCell);
            row.appendChild(firstNameCell);
            row.appendChild(lastNameCell);
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

// Load student details on page load
document.addEventListener("DOMContentLoaded", () => {
    loadStudentDetails();
});
