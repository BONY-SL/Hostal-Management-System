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

            const nameCell = document.createElement("td");
            nameCell.textContent = student.student_name;

            const dobCell = document.createElement("td");
            dobCell.textContent = student.DOB;

            const yearCell = document.createElement("td");
            yearCell.textContent = student.academic_year;

            // Append cells to the row
            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(dobCell);
            row.appendChild(yearCell);

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load student details:", error);
        tableBody.innerHTML = "<tr><td colspan='4'>Failed to load student data.</td></tr>";
    }
}

// Load student details and initialize notice button events on page load
document.addEventListener("DOMContentLoaded", () => {
    loadStudentDetails();


});
