// Function to load asset details into the table
import {AuthService} from "./authservice.js";

viewAssetDetails().then(r => null);
async function viewAssetDetails() {
    const tableBody = document.querySelector("#assetsTableBody");

    const auth = new AuthService();
    try {
        // Fetch asset data from the backend API and parse it as JSON
        const assets = await fetch("http://localhost:8080/hostalmanage/warden/getAssets", {
            method: "GET",
            headers: auth.createAuthorizationHeader(),
        }).then(response => response.json());

        // Clear existing rows (if any)
        tableBody.innerHTML = "";

        // Populate rows dynamically
        assets.forEach(asset => {
            const row = document.createElement("tr");

            // Create table cells for each asset property
            const idCell = document.createElement("td");
            idCell.textContent = asset.asset_id || "-";

            const roomNoCell = document.createElement("td");
            roomNoCell.textContent = asset.room_no || "-";

            const descriptionCell = document.createElement("td");
            descriptionCell.textContent = asset.description || "-";

            const locationCell = document.createElement("td");
            locationCell.textContent = asset.location || "-";

            const acquisitionDateCell = document.createElement("td");
            acquisitionDateCell.textContent = asset.acquisition_date || "-";

            const conditionCell = document.createElement("td");
            conditionCell.textContent = asset.asset_condition || "-";

            const tgNoCell = document.createElement("td");
            tgNoCell.textContent = asset.tg_no || "-";

            // Append cells to the row
            row.appendChild(idCell);
            row.appendChild(roomNoCell);
            row.appendChild(descriptionCell);
            row.appendChild(locationCell);
            row.appendChild(acquisitionDateCell);
            row.appendChild(conditionCell);
            row.appendChild(tgNoCell);

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to load asset details:", error);
        tableBody.innerHTML = "<tr><td colspan='7'>Failed to load asset data.</td></tr>";
    }
}
