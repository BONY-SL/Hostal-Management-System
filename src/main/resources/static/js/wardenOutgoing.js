// Function to load outgoing details into the table
import {AuthService} from "./authservice.js";

viewOutgoingDetails().then(r => null);
async function viewOutgoingDetails() {
    const tableBody = document.querySelector("#outgoingTableBody");

    const auth = new AuthService();
    try {
        // Fetch outgoing data from the backend API and parse it as JSON
        const outgoingRecords = await fetch("http://localhost:8080/hostalmanage/warden/getOutgoingDetails", {
            method: "GET",
            headers: auth.createAuthorizationHeader(),
        }).then(response => response.json());

        // Clear existing rows (if any)
        tableBody.innerHTML = "";

        // Populate rows dynamically
        outgoingRecords.forEach(record => {
            const row = document.createElement("tr");

            // Create table cells for each outgoing property
            const outgoingIdCell = document.createElement("td");
            outgoingIdCell.textContent = record.outgoingId || "-";

            const nameCell = document.createElement("td");
            nameCell.textContent = record.name || "-";


            const tgNoCell = document.createElement("td");
            tgNoCell.textContent = record.tg_no || "-";

            const locationCell = document.createElement("td");
            locationCell.textContent = record.location || "-";

            const dateOutCell = document.createElement("td");
            dateOutCell.textContent = record.date_out || "-";

            const returnDateCell = document.createElement("td");
            returnDateCell.textContent = record.return_date || "-";

            const arrivalTimeCell = document.createElement("td");
            arrivalTimeCell.textContent = record.arrivalTime || "-";

            const leaveTimeCell = document.createElement("td");
            leaveTimeCell.textContent = record.leaveTime || "-";

            // Append cells to the row
            row.appendChild(outgoingIdCell);
            row.appendChild(nameCell);
            row.appendChild(tgNoCell);
            row.appendChild(locationCell);
            row.appendChild(dateOutCell);
            row.appendChild(returnDateCell);
            row.appendChild(arrivalTimeCell);
            row.appendChild(leaveTimeCell);

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load outgoing details:", error);
        tableBody.innerHTML = "<tr><td colspan='7'>Failed to load outgoing data.</td></tr>";
    }
}
