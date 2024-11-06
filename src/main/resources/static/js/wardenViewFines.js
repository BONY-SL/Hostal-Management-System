document.addEventListener("DOMContentLoaded", function() {
    const finesTableBody = document.getElementById("finesTableBody");

    // Sample data - replace with actual data from API or backend
    const finesDetails = [
        { fineId: "1", amount: "$100", reason: "Late Payment", issuedDate: "2024-10-01", fineStatus: "Pending", tgNo: "12345" },
        { fineId: "2", amount: "$50", reason: "Violation of Rules", issuedDate: "2024-10-05", fineStatus: "Paid", tgNo: "67890" },
        // Add more sample data as needed
    ];

    // Populate table
    finesDetails.forEach(fine => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${fine.fineId}</td>
            <td>${fine.amount}</td>
            <td>${fine.reason}</td>
            <td>${fine.issuedDate}</td>
            <td>${fine.fineStatus}</td>
            <td>${fine.tgNo}</td>
        `;
        finesTableBody.appendChild(row);
    });
});
