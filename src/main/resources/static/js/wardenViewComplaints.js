document.addEventListener("DOMContentLoaded", function () {
    const complaintsTableBody = document.getElementById("complaintsTableBody");

    // Mock data to populate the complaints table
    const complaintsData = [
        {
            complain_id: "C001",
            title: "Broken Window",
            description: "The window in Room 102 is broken.",
            date_reported: "2024-11-01",
            status: "Pending",
            resolved_date: "N/A"
        },
        {
            complain_id: "C002",
            title: "Leaking Faucet",
            description: "The faucet in the common bathroom is leaking.",
            date_reported: "2024-11-02",
            status: "Resolved",
            resolved_date: "2024-11-04"
        }
    ];

    // Function to add complaints data to the table
    function populateComplaintsTable(complaints) {
        complaints.forEach(complaint => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${complaint.complain_id}</td>
                <td>${complaint.title}</td>
                <td>${complaint.description}</td>
                <td>${complaint.date_reported}</td>
                <td>${complaint.status}</td>
                <td>${complaint.resolved_date}</td>
            `;
            complaintsTableBody.appendChild(row);
        });
    }

    // Populate table with mock data
    populateComplaintsTable(complaintsData);
});
