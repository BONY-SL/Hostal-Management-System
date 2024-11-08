import {StorageService} from "./storageservice.js";

// document.addEventListener("DOMContentLoaded", function () {
//     const complaintsCard = document.getElementById("complaintsCard");
//     const assetsCard = document.getElementById("assetsCard");
//     const studentsCard = document.getElementById("studentsCard");
//     const noticesCard = document.getElementById("noticesCard");
//     const finesCard = document.getElementById("finesCard");
//     const outgoingCard = document.getElementById("outgoingCard");
//
//     complaintsCard.addEventListener("click", function () {
//         window.location.href = "wardenViewComplaints.html";
//     });
//
//     assetsCard.addEventListener("click", function () {
//         window.location.href = "wardenViewAssets.html";
//     });
//
//     studentsCard.addEventListener("click", function () {
//         window.location.href = "wardenViewStudents.html";
//     });
//
//     noticesCard.addEventListener("click", function () {
//         window.location.href = "wardenManageNotices.html";
//     });
//
//     finesCard.addEventListener("click", function () {
//         window.location.href = "wardenViewFines.html";
//     });
//
//     outgoingCard.addEventListener("click", function () {
//         window.location.href = "wardenViewOutgoing.html";
//     });
// });


// Utility function to get token and role from storage
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
function checkWardenAccess() {
    const { token, role } = getAuthData();
    const tokenFromUrl = new URLSearchParams(window.location.search).get("token");

    if (!token || role !== "WARDEN" || token !== tokenFromUrl) {
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
        { id: "wardenDashboard", href: "wardenDashboard.html" },
        { id: "wardenManageNotices", href: "wardenManageNotices.html" },
        { id: "wardenViewAssets", href: "wardenViewAssets.html" },
        { id: "wardenViewComplaints", href: "wardenViewComplaints.html" },
        { id: "wardenViewFines", href: "wardenViewFines.html" },
        { id: "wardenViewOutgoing", href: "wardenViewOutgoing.html" },
        { id: "wardenViewStudents", href: "wardenViewStudents.html" },
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
    checkWardenAccess();
    appendTokenToLinks();
}

window.onload = init;