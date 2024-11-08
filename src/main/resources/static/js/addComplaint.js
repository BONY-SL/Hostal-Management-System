import { AuthService } from "./authservice.js"; // Import the AuthService class


document.getElementById('complaintForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission


    // Get the form data
    const complaintData = {
        roomNumber: document.getElementById('roomNumber').value,
        complaintType: document.getElementById('complaintType').value,
        description: document.getElementById('description').value,
        contact: document.getElementById('contact').value

    };
    console.log(complaintData);
    // Send the data to the server
    const authservice = new AuthService();
    // Use the fetch API to send the data to the server
    fetch('http://localhost:8080/hostalmanage/student/addcomplaint', {
        method: 'POST',
        headers: authservice.createAuthorizationHeader(),
        body: JSON.stringify(complaintData) // Convert JS object to JSON string
    })
        .then(response => response.json())
        .then(data => {
            if (data) {
                alert('Complaint submitted successfully!');
                document.getElementById('complaintForm').reset();
            } else {
                alert('Failed to submit the complaint.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error submitting the complaint.');
        });
});