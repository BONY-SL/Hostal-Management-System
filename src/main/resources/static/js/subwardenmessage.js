import { AuthService } from "./authservice.js";

// Function to fetch and display fine data
const authservice = new AuthService();

// Define pagination variables
const rowsPerPage = 5;
let currentPage = 1;
let studentData = [];

// Function to fetch student data from the backend
async function displayStudent() {
    try {
        // Fetch fine payment data from the backend
        const response = await fetch('/hostalmanage/subwarden/getFinepaymentDetails', {
            method: 'GET',
            headers: authservice.createAuthorizationHeader()
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        studentData = await response.json();
        console.log('Fetched student data:', studentData); // Log fetched data

        // Populate the fine payment table with the fetched data
        populateStudentTable(studentData);

        // Initialize pagination
        displayTable(currentPage);
        updatePagination();
    } catch (error) {
        console.error('Error fetching student details:', error);
    }
}

// Function to populate the fine payment table
function populateStudentTable(students) {
    const tbody = document.querySelector('.fine-table tbody');
    tbody.innerHTML = ''; // Clear any existing rows

    students.forEach(finepayment => {
        const row = document.createElement('tr');
        row.innerHTML = `
            
            <td>${finepayment.tg_no}</td>
            <td>${finepayment.issueDate}</td>
            <td>${finepayment.updateTime}</td>
            <td>${finepayment.message}</td>
        `;
        tbody.appendChild(row);
    });
}

// Pagination functions
function displayTable(page) {
    const tbody = document.querySelector('.fine-table tbody');
    tbody.innerHTML = ''; // Clear the table before adding new rows

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    // Only display the rows for the current page
    const paginatedData = studentData.slice(start, end);

    paginatedData.forEach(student => {
        const row = document.createElement('tr');
        row.innerHTML = `
           <td>${student.tg_no}</td>
            <td>${student.issueDate}</td>
            <td>${student.updateTime}</td>
            <td>${student.message}</td>
        `;
        tbody.appendChild(row);
    });
}

function updatePagination() {
    const totalPages = Math.ceil(studentData.length / rowsPerPage);
    const pageNumbers = document.getElementById("pageNumbers");
    pageNumbers.innerHTML = `Page ${currentPage} of ${totalPages}`;

    document.getElementById("prevPage").disabled = currentPage === 1;
    document.getElementById("nextPage").disabled = currentPage === totalPages;
}

function nextPage() {
    if (currentPage < Math.ceil(studentData.length / rowsPerPage)) {
        currentPage++;
        displayTable(currentPage);
        updatePagination();
    }
}

function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        displayTable(currentPage);
        updatePagination();
    }
}

// Attach event listeners for pagination buttons
document.getElementById("nextPage").addEventListener("click", nextPage);
document.getElementById("prevPage").addEventListener("click", prevPage);

// Call the function to display fine payment data and initialize pagination when the page loads
window.onload = displayStudent;
