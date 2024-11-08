import {AuthService} from "./authservice.js";


document.addEventListener('DOMContentLoaded', function() {
    // Call the function to fetch and display request details once the page has loaded
    displayRequestDetails();
});


const authservice = new AuthService();
async function displayRequestDetails() {
    try {
        // Fetch room request details from the backend
        const response = await fetch('/hostalmanage/subwarden/getrequestDetails', {
            method: 'GET',
            headers: authservice.createAuthorizationHeader() // Assuming you have an auth service for headers
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const requestData = await response.json();
        console.log('Fetched request details:', requestData); // Log fetched data

        // Populate the request table with the fetched data
        populateRequestTable(requestData);
    } catch (error) {
        console.error('Error fetching request details:', error);
    }
}

// Function to populate the room request table
function populateRequestTable(requests) {
    const tbody = document.querySelector('.request-table tbody');
    tbody.innerHTML = ''; // Clear any existing rows

    requests.forEach(request => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${request.tg_no}</td>
            <td>${request.room_no}</td>
            <td>${new Date(request.request_date).toLocaleDateString()}</td>
            <td>${request.state}</td>
        `;
        tbody.appendChild(row);
    });
}


document.getElementById("updateForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const tgNo = document.getElementById("tgNo").value;
    const newState = document.getElementById("newState").value;

    try {
        const response = await fetch(`/hostalmanage/subwarden/updateRequestState?tgNo=${tgNo}&newState=${newState}`, {
            method: 'PUT',
            headers: {
                ...authservice.createAuthorizationHeader(),
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Failed to update the request state');
        }

        alert("Request state updated successfully!");
        document.getElementById("updateForm").reset();
        displayRequestDetails(); // Optionally reload the request details table
    } catch (error) {
        console.error("Error updating request state:", error);
        alert("Error updating request state. Please try again.");
    }
});