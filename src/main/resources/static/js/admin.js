// add hovered class to selected list item
import {StorageService} from "./storageservice.js";
import {AuthService} from "./authservice.js";

let list = document.querySelectorAll(".navigation li");

checkAdminAccess();

function activeLink() {
    list.forEach((item) => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

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



// Utility function to get token and role from storage
function getAuthData() {

    return {role: StorageService.getUserRole(),
        userId: StorageService.getUserId()
        };
}

// Redirects to index.html if the user does not have admin access
function redirectToLogin() {
    window.location.href = "index.html";
}

// Checks if the user has admin access based on token and role
function checkAdminAccess() {

    const {role,userId}= getAuthData();
    const userRole = new URLSearchParams(window.location.search).get("userRole");

    if (!role || role !== "ADMIN" || userRole !== role) {
        redirectToLogin();
    }
}

// Adds token as a URL parameter to each link in the navigation menu
function appendTokenToLinks() {

    const {role } = getAuthData();

    if (!role) {
        redirectToLogin();
        return;
    }

    const links = [
        { id: "dashboardLink", href: "admindashboard.html" },
        { id: "userManageLink", href: "manageuser.html" },
        { id: "roomManageLink", href: "roommanage.html" },
        { id: "studentManageLink", href: "studentmanage.html" },
        { id: "adminMessageLink", href: "adminmessage.html" },
        { id: "adminProfileSettingsLink", href: "adminprofilesetting.html" }
    ];

    links.forEach(({ id, href }) => {
        const element = document.getElementById(id);
        if (element) {
            element.href = `${href}?userRole=${role}`;
        }
    });
}

// Initial setup to validate access and append tokens
function init() {
    checkAdminAccess();
    appendTokenToLinks();
}

window.onload = init;



