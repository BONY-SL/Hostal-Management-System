// Function to load fine details into the table
import {AuthService} from "./authservice.js";

viewFinesDetails().then(r => null);
async function viewFinesDetails() {
    const tableBody = document.querySelector("#finesTableBody");

    const auth = new AuthService();
    try {
        // Fetch fine data from the backend API and parse it as JSON
        const fines = await fetch("http://localhost:8080/hostalmanage/warden/viewFines", {
            method: "GET",
            headers: auth.createAuthorizationHeader(),
        }).then(response => response.json());

        // Clear existing rows (if any)
        tableBody.innerHTML = "";

        // Populate rows dynamically
        fines.forEach(fine => {
            const row = document.createElement("tr");

            // Create table cells for each fine property
            const fineIdCell = document.createElement("td");
            fineIdCell.textContent = fine.fine_id || "-";

            const amountCell = document.createElement("td");
            amountCell.textContent = fine.amount || "-";

            const reasonCell = document.createElement("td");
            reasonCell.textContent = fine.reason || "-";

            const issuedDateCell = document.createElement("td");
            issuedDateCell.textContent = fine.issued_date || "-";

            const statusCell = document.createElement("td");
            statusCell.textContent = fine.fine_status || "-";

            const tgNoCell = document.createElement("td");
            tgNoCell.textContent = fine.tg_no || "-";

            // Append cells to the row
            row.appendChild(fineIdCell);
            row.appendChild(amountCell);
            row.appendChild(reasonCell);
            row.appendChild(issuedDateCell);
            row.appendChild(statusCell);
            row.appendChild(tgNoCell);

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load fine details:", error);
        tableBody.innerHTML = "<tr><td colspan='6'>Failed to load fine data.</td></tr>";
    }
}
