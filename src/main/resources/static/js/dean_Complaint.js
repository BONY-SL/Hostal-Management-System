// dean_complaint.js

// Sample data - replace this with actual data fetching logic
const complaintsData = [
    { complaintID: 'C001', contactNumber: '0712345678', description: 'Room light not working', complaintType: 'Electrical', roomNumber: 'B12', status: 'Pending' },
    { complaintID: 'C002', contactNumber: '0723456789', description: 'Water leakage', complaintType: 'Plumbing', roomNumber: 'A21', status: 'In Progress' },
    { complaintID: 'C003', contactNumber: '0734567890', description: 'Bed broken', complaintType: 'Furniture', roomNumber: 'C34', status: 'Delivered' },
    { complaintID: 'C004', contactNumber: '0745678901', description: 'Air conditioner issue', complaintType: 'Electrical', roomNumber: 'D45', status: 'Pending' }
];

// Function to populate complaints table
function populateComplaintsTable() {
    const tableBody = document.getElementById('complaintsTable').getElementsByTagName('tbody')[0];
    tableBody.innerHTML = ''; // Clear existing rows

    complaintsData.forEach((complaint) => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${complaint.complaintID}</td>
            <td>${complaint.contactNumber}</td>
            <td>${complaint.description}</td>
            <td>${complaint.complaintType}</td>
            <td>${complaint.roomNumber}</td>
            <td><span class="status ${complaint.status.replace(' ', '').toLowerCase()}">${complaint.status}</span></td>
        `;

        tableBody.appendChild(row);
    });
}

// Function to toggle the navigation bar
function toggleNavigation() {
    const navigation = document.querySelector('.navigation');
    const main = document.querySelector('.main');
    navigation.classList.toggle('active');
    main.classList.toggle('active');
}

// Add event listener to the toggle button
document.querySelector('.toggle').addEventListener('click', toggleNavigation);

// Populate table on page load
window.onload = populateComplaintsTable;
