import {AuthService} from "./authservice.js";


document.getElementById('displayFineButton').addEventListener('click', function() {
    // Hide the existing content and show the display fine section
    document.querySelector('.content-wrapper').style.display = 'none';
    document.getElementById('displayFineSection').style.display = 'block';
});

document.getElementById('addFineButton').addEventListener('click', function() {
    // Hide the display fine section and show the content wrapper
    document.getElementById('displayFineSection').style.display = 'none';
    document.querySelector('.content-wrapper').style.display = 'flex';
});

const authservice = new AuthService();
async function fetchAndDisplayAsset() {
    try {
        // Fetch the fine data from the backend
        const response = await fetch('/hostalmanage/subwarden/getAsset', {
            method: 'GET',
            headers: authservice.createAuthorizationHeader()
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const fineData = await response.json();

        // Select the table body to insert rows
        const tbody = document.querySelector('#displayFineSection .fine-table tbody');
        tbody.innerHTML = ''; // Clear any existing rows

        // Populate the table with fine data
        fineData.forEach(asset => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${asset.tg_no}</td>
                <td>${asset.room_no}</td>
                <td>${asset.description}</td>
                <td>${asset.location}</td>
                <td>${new Date(asset.acquisition_date).toLocaleDateString()}</td>
                <td>${asset.asset_condition}</td>
            `;
            tbody.appendChild(row);
        });

        // Display the fine section
        document.getElementById('displayFineSection').style.display = 'block';
    } catch (error) {
        console.error('Error fetching fine details:', error);
    }
}

// Call the function to hide fines when the page loads
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('displayFineSection').style.display = 'none'; // Hide fine section initially
    fetchAndDisplayAsset(); // Fetch fines if needed (but it won't be displayed)
});






// Event listener for the fine form submission
document.addEventListener('DOMContentLoaded', function() {
    const fineForm = document.getElementById('fineForm');
    const clearFormButton = document.getElementById('clearFormButton');

    fineForm.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect data from the form fields
        const fineData = {
            tg_no: document.getElementById('studentid').value,
            room_no: parseInt(document.getElementById('roomNo').value),
            description: document.getElementById('description').value,
            location: document.getElementById('location').value,
            acquisition_date: document.getElementById('acquisitionDate').value,
            asset_condition: document.getElementById('condition').value
        };

        try {
            // Send a POST request to add fine
            const response = await fetch('/hostalmanage/subwarden/addAsset', {
                method: 'POST',
                headers: {
                    ...authservice.createAuthorizationHeader(),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(fineData)
            });

            // Handle the response from the server
            if (response.ok) {
                const result = await response.text();
                alert(result); // Show success message from the backend
                fineForm.reset(); // Clear the form fields after successful submission
            } else {
                alert('Failed to add fine. Please try again.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred. Please try again.');
        }
    });

    // Event listener to clear the form fields
    clearFormButton.addEventListener('click', function() {
        fineForm.reset();
    });
});

let studentData = [];

// Function to display student data
document.addEventListener('DOMContentLoaded', function() {
    displayStudent(); // Call the function when the page loads
});

async function displayStudent() {
    try {
        // Fetch student data from the backend
        const response = await fetch('/hostalmanage/subwarden/getStudent', {
            method: 'GET',
            headers: authservice.createAuthorizationHeader()
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        studentData = await response.json();
        console.log('Fetched student data:', studentData); // Log fetched data

        // Populate the student table with the fetched data
        populateStudentTable(studentData);
    } catch (error) {
        console.error('Error fetching student details:', error);
    }
}

// Function to populate the student table
function populateStudentTable(students) {
    const tbody = document.querySelector('.fine-table tbody');
    tbody.innerHTML = ''; // Clear any existing rows

    students.forEach(student => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${student.tg_no}</td>
            <td>${student.firstname}</td>
            <td>${student.department}</td>
            <td>${student.phoneNo}</td>
            <td>${student.email}</td>
            <td>${student.address}</td>
        `;
        tbody.appendChild(row);
    });
}









