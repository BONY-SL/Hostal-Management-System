// Event listener for displaying the fine section
document.getElementById('displayFineButton').addEventListener('click', async function () {
    // Hide the existing content and show the display fine section
    document.querySelector('.content-wrapper').style.display = 'none';
    document.getElementById('displayFineSection').style.display = 'block';

    // Fetch and display fines
    await fetchAndDisplayFines();
});

// Event listener for the add fine button
document.getElementById('addFineButton').addEventListener('click', function () {
    // Hide the display fine section and show the content wrapper
    document.getElementById('displayFineSection').style.display = 'none';
    document.querySelector('.content-wrapper').style.display = 'flex';
});

// Function to fetch and display fine data
async function fetchAndDisplayFines() {
    try {
        // Fetch the fine data from the backend
        const response = await fetch('/hostalmanage/subwarden/getFine');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const fineData = await response.json();

        // Select the table body to insert rows
        const tbody = document.querySelector('#displayFineSection .fine-table tbody');
        tbody.innerHTML = ''; // Clear any existing rows

        // Populate the table with fine data
        fineData.forEach(fine => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${fine.tg_no}</td>
                <td>${fine.reason}</td>
                <td>${fine.amount.toFixed(2)}</td>
                <td>${new Date(fine.issued_date).toLocaleDateString()}</td>
                <td>${fine.fine_status}</td>
            `;
            tbody.appendChild(row);
        });

        // Display the fine section
        document.getElementById('displayFineSection').style.display = 'block';
    } catch (error) {
        console.error('Error fetching fine details:', error);
    }
}

// Call the function to hide fines when the page loads
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('displayFineSection').style.display = 'none'; // Hide fine section initially
    fetchAndDisplayFines(); // Fetch fines if needed (but it won't be displayed)
});

// Event listener for the fine form submission
document.addEventListener('DOMContentLoaded', function() {
    const fineForm = document.getElementById('fineForm');
    const clearFormButton = document.getElementById('clearFormButton');

    fineForm.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect data from the form fields
        const fineData = {

            tg_no: document.getElementById('studentID').value,
            amount: parseFloat(document.getElementById('amount').value),
            reason: document.getElementById('reason').value,
            issued_date: document.getElementById('issued_date').value,
            fine_status: document.getElementById('status').value

        };

        // Log fine details to the console
        console.log("Fine Details:");
        console.log(`Student ID: ${fineData.id}`);
        console.log(`Amount: ${fineData.amount.toFixed(2)}`);
        console.log(`Reason: ${fineData.reason}`);
        console.log(`Issued Date: ${fineData.issued_date}`);
        console.log(`Status: ${fineData.fine_status}`);

        try {
            // Send a POST request to add fine
            const response = await fetch('/hostalmanage/subwarden/addFine', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(fineData)
            });

            // Handle the response from the server
            if (response.ok) {
                const result = await response.text();
                alert(result); // Show success message from the backend
                fineForm.reset(); // Clear the form fields after successful submission
            } else {
                alert('Failed to add fine. Please try again.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred. Please try again.');
        }
    });

    // Event listener to clear the form fields
    clearFormButton.addEventListener('click', function() {
        fineForm.reset();
    });
});