document.addEventListener("DOMContentLoaded", function() {
    const complaintsTableBody = document.getElementById("complaintsTableBody");

    // Sample data - replace with actual data from API or backend
    const complaintsDetails = [
        { complaintId: "1", title: "Noise Disturbance", description: "Loud noises in the dorm after 10 PM", dateReported: "2024-10-01", status: "Open", resolvedDate: "N/A" },
        { complaintId: "2", title: "Facility Issue", description: "Broken window in room 203", dateReported: "2024-10-05", status: "Resolved", resolvedDate: "2024-10-06" },
        // Add more sample data as needed
    ];

    // Populate table
    complaintsDetails.forEach(complaint => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${complaint.complaintId}</td>
            <td>${complaint.title}</td>
            <td>${complaint.description}</td>
            <td>${complaint.dateReported}</td>
            <td>${complaint.status}</td>
            <td>${complaint.resolvedDate}</td>
        `;
        complaintsTableBody.appendChild(row);
    });
});
