// Function to handle redirection to the login page
function redirectToLogin() {
    window.location.href = 'index.html'; // Redirect to the login page; adjust the URL as needed
}

// Function to retry the activation process by refreshing the code form
function retryActivation() {
    document.getElementById('activation-error').style.display = 'none';
    document.getElementById('code-form').style.display = 'flex';
}

// Function to submit the activation code
function submitCode() {
    const activationCode = document.getElementById('activation-code').value;

    // Check if activation code is entered
    if (!activationCode || activationCode.length !== 6) {
        alert("Please enter a valid 6-digit activation code.");
        return;
    }

    // Send the activation code to the backend
    fetch(`http://localhost:8080/hostalmanage/auth/activate-account?code=${encodeURIComponent(activationCode)}`, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                // Show success message if activation is successful
                document.getElementById('code-form').style.display = 'none';
                document.getElementById('activation-success').style.display = 'flex';
            } else {
                // Show error message if activation fails
                response.text().then(errorMessage => {
                    document.getElementById('error-message').textContent =  "An error occurred during activation.";
                    document.getElementById('code-form').style.display = 'none';
                    document.getElementById('activation-error').style.display = 'flex';
                });
            }
        })
        .catch(error => {
            // Display a generic error message in case of a network or other error
            document.getElementById('error-message').textContent = "An unexpected error occurred. Please try again later.";
            document.getElementById('code-form').style.display = 'none';
            document.getElementById('activation-error').style.display = 'flex';
        });
}
