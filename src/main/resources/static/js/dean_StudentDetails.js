function populateStudentTable() {
    console.log("Populating student table..."); // Test log

    const tableBody = document.querySelector("#studentTable tbody");
    if (!tableBody) {
        console.error("Table body not found");
        return;
    }
    tableBody.innerHTML = ""; // Clear existing rows

    students.forEach(student => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${student.studentID}</td>
            <td>${student.address}</td>
            <td>${student.department}</td>
            <td>${student.dob}</td>
            <td>${student.email}</td>
            <td>${student.enrollmentDate}</td>
            <td>${student.phoneNo}</td>
            <td>${student.tg_no}</td>
        `;

        tableBody.appendChild(row);
    });

    console.log("Table populated successfully");
}

document.addEventListener("DOMContentLoaded", populateStudentTable);
