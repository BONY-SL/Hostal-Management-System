// Utility function to get token and role from storage
import {StorageService} from "./storageservice.js";
import {AuthService} from "./authservice";

checkStudentAccess(); // Check if the user has student access
function getAuthData() { // Utility function to get token and role from storage
    return {
        token: StorageService.getToken(),
        role: StorageService.getUserRole()
    };
}

// Redirects to index.html if the user does not have admin access
function redirectToLogin() {
    window.location.href = "index.html";
}

// Checks if the user has admin access based on token and role
function checkStudentAccess() {
    const { token, role } = getAuthData();
    const tokenFromUrl = new URLSearchParams(window.location.search).get("token");

    if (!token || role !== "STUDENT" || token !== tokenFromUrl) {
        redirectToLogin();
    }
}

// Logs out the user
async function logOut() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You will be logged out!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#068f8f',
        cancelButtonColor: '#e38c8c',
        confirmButtonText: 'Yes, log me out!'
    }).then(async (result) => {

        if (result.isConfirmed) {
            try {
                const authService = new AuthService();
                const response = await authService.logOut();
                console.log('Logged out successfully', response);

                StorageService.logOut();

                window.location.href = "index.html";
            } catch (error) {
                console.error('Logout failed', error);

                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong while logging out!'
                });
            }
        }
    });
}


window.logOut = logOut;


document.getElementById("logoutButtonId").addEventListener("onclick", logOut);


// Adds token as a URL parameter to each link in the navigation menu
function appendTokenToLinks() {
    const { token } = getAuthData();

    if (!token) {
        redirectToLogin();
        return;
    }
   // Add the token to each link in the navigation menu
    const links = [
        { id: "studentdashboardLink", href: "studentDashboard.html" },
        { id: "studentComplainLink", href: "studentComplain.html" },
        { id: "studentNotiesView", href: "studentNoticeView.html" },
        { id: "StudentPayment", href: "StudentPayment.html" },
        { id: "studentProfileView", href: "studentProfileView.html" },
        { id: "studentRoomList", href: "studentRoomList.html" }
    ];

    // Add the token to each link in the navigation menu
    links.forEach(({ id, href }) => {
        const element = document.getElementById(id);
        if (element) {
            element.href = `${href}?token=${token}`;
        }
    });
}

// Initial setup to validate access and append tokens
function init() {
    checkStudentAccess(); // Check if the user has student access
    appendTokenToLinks(); // Add the token to each link in the navigation menu
}

window.onload = init; // Call the init function when the window loads