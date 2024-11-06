document.addEventListener("DOMContentLoaded", async function() {
    const finesTableBody = document.getElementById("finesTableBody");

    try {
        // Fetch fines data from the backend API
        const response = await fetch("http://localhost:8080/hostalmanage/viewFines");
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        // Parse the JSON data into JavaScript objects
        const finesDetails = await response.json();

        // Clear any existing rows in the table body
        finesTableBody.innerHTML = "";

        // Populate table with fetched data
        finesDetails.forEach(fine => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${fine.fine_id}</td>
                <td>$${fine.amount.toFixed(2)}</td>
                <td>${fine.reason}</td>
                <td>${new Date(fine.issued_date).toLocaleDateString()}</td>
                <td>${fine.fine_status}</td>
                <td>${fine.tg_no}</td>
            `;
            finesTableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load fines details:", error);
        finesTableBody.innerHTML = "<tr><td colspan='6'>Failed to load fines data.</td></tr>";
    }
});
