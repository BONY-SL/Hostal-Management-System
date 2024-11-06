// Example for dean_Assets.js

const assets = [
    { asset_id: "A001", acquisition_date: "2024-01-10", condition: "Good", description: "Laptop", location: "Building A", room_no: "101", tg_no: "T001" },
    { asset_id: "A002", acquisition_date: "2023-08-05", condition: "Excellent", description: "Projector", location: "Building B", room_no: "202", tg_no: "T002" },
    // Add more assets as needed
];

const tableBody = document.querySelector("#assetsTable tbody");

assets.forEach(asset => {
    const row = document.createElement("tr");
    Object.values(asset).forEach(value => {
        const cell = document.createElement("td");
        cell.textContent = value;
        row.appendChild(cell);
    });
    tableBody.appendChild(row);
});
