// Utility function to get token and role from storage
import {StorageService} from "./storageservice.js";

checkStudentAccess();
function getAuthData() {
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

// Adds token as a URL parameter to each link in the navigation menu
function appendTokenToLinks() {
    const { token } = getAuthData();

    if (!token) {
        redirectToLogin();
        return;
    }

    const links = [
        { id: "studentdashboardLink", href: "studentDashboard.html" },
        { id: "studentComplainLink", href: "StudentComplain.html" },
        { id: "studentNotiesView", href: "studentNoticeView.html" },
        { id: "StudentPayment", href: "StudentPayment.html" },
        { id: "studentProfileView", href: "studentProfileView.html" },
        { id: "studentRoomList", href: "studentRoomList.html" }
    ];

    links.forEach(({ id, href }) => {
        const element = document.getElementById(id);
        if (element) {
            element.href = `${href}?token=${token}`;
        }
    });
}

// Initial setup to validate access and append tokens
function init() {
    checkStudentAccess();
    appendTokenToLinks();
}

window.onload = init;