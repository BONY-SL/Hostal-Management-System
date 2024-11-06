document.addEventListener("DOMContentLoaded", function() {
    const outgoingTableBody = document.getElementById("outgoingTableBody");

    // Sample data - replace with real data from API or backend
    const outgoingDetails = [
        { outgoingId: "OUT001", studentName: "John Doe", indexNumber: "ST1234", location: "Library", date: "2024-11-05", arrivalTime: "10:00 AM", leaveTime: "02:00 PM" },
        { outgoingId: "OUT002", studentName: "Jane Smith", indexNumber: "ST5678", location: "Cafeteria", date: "2024-11-06", arrivalTime: "12:00 PM", leaveTime: "03:00 PM" },
        // More sample data...
    ];

    // Populate table
    outgoingDetails.forEach(detail => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${detail.outgoingId}</td>
            <td>${detail.studentName}</td>
            <td>${detail.indexNumber}</td>
            <td>${detail.location}</td>
            <td>${detail.date}</td>
            <td>${detail.arrivalTime}</td>
            <td>${detail.leaveTime}</td>
        `;
        outgoingTableBody.appendChild(row);
    });
});
