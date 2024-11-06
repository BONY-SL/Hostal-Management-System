document.addEventListener("DOMContentLoaded", function() {
    loadAssets();
});

function loadAssets() {
    fetch('http://localhost:8080/hostalmanage/getAssets') // Update with your API endpoint
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById("complaintsTableBody");
            tableBody.innerHTML = ""; // Clear any existing rows

            data.forEach(asset => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${asset.asset_id}</td>
                    <td>${asset.room_no}</td>
                    <td>${asset.description}</td>
                    <td>${asset.location}</td>
                    <td>${new Date(asset.acquisition_date).toLocaleDateString()}</td>
                    <td>${asset.asset_condition}</td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching assets:', error);
        });
}
