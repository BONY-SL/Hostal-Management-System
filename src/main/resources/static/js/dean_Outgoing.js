// Sample data for the outgoing register (this can be fetched dynamically from a database)
const outgoingData = [
    {
        outgoing_id: 'OUT001',
        arrival_time: '08:00 AM',
        date: '2024-11-06',
        leave_time: '05:00 PM',
        location: 'Library',
        student_name: 'John Doe'
    },
    {
        outgoing_id: 'OUT002',
        arrival_time: '09:30 AM',
        date: '2024-11-06',
        leave_time: '06:00 PM',
        location: 'Cafeteria',
        student_name: 'Jane Smith'
    }
    // Add more data as necessary
];

// Function to render the outgoing data into the table
function renderOutgoingTable() {
    const tableBody = document.querySelector("#outgoingTable tbody");
    outgoingData.forEach(outgoing => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${outgoing.outgoing_id}</td>
            <td>${outgoing.arrival_time}</td>
            <td>${outgoing.date}</td>
            <td>${outgoing.leave_time}</td>
            <td>${outgoing.location}</td>
            <td>${outgoing.student_name}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Initialize the table when the page loads
document.addEventListener("DOMContentLoaded", renderOutgoingTable);
