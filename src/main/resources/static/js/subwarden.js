import {StorageService} from "./storageservice.js";
import {AuthService} from "./authservice.js";

document.addEventListener("DOMContentLoaded", () => {
    fetchTotalStudentCount();
    fetchOutgoingDetails();
});



const authservice = new AuthService();

function fetchTotalStudentCount() {
    fetch('/hostalmanage/subwarden/getTotalStudentCount', {
        method: 'GET',
        headers: authservice.createAuthorizationHeader()
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('totalStudents').innerText = data;
        })
        .catch(error => console.error('Error fetching student count:', error));
}


const rowsPerPage = 10; // Number of rows per page
let currentPage = 1;
let outgoingData = []; // Store fetched data

function fetchOutgoingDetails() {
    fetch('/hostalmanage/subwarden/getOutgoingDetails', {
        method: 'GET',
        headers: authservice.createAuthorizationHeader()
    })
        .then(response => response.json())
        .then(data => {
            outgoingData = data;
            renderTable();
        })
        .catch(error => console.error('Error fetching outgoing details:', error));
}

function renderTable() {
    const tableBody = document.querySelector("#outgoingDetailsTable tbody");
    tableBody.innerHTML = "";

    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageData = outgoingData.slice(start, end);

    pageData.forEach(outgoing => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${outgoing.tg_no}</td>
            <td>${outgoing.name}</td>
            <td>${outgoing.date_out ? new Date(outgoing.date_out).toLocaleDateString() : ''}</td>
            <td>${outgoing.return_date ? new Date(outgoing.return_date).toLocaleDateString() : ''}</td>
            <td>${outgoing.arrivalTime || ''}</td>
            <td>${outgoing.leaveTime || ''}</td>
        `;
        tableBody.appendChild(row);
    });

    document.getElementById('pageInfo').innerText = `Page ${currentPage} of ${Math.ceil(outgoingData.length / rowsPerPage)}`;
    document.getElementById('prevPage').disabled = currentPage === 1;
    document.getElementById('nextPage').disabled = currentPage === Math.ceil(outgoingData.length / rowsPerPage);
}

function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        renderTable();
    }
}

function nextPage() {
    if (currentPage < Math.ceil(outgoingData.length / rowsPerPage)) {
        currentPage++;
        renderTable();
    }
}


window.filterTable = function () {
    const filterInput = document.getElementById("filterInput").value.trim();
    fetch('/hostalmanage/subwarden/getOutgoingDetails', {
        method: 'GET',
        headers: authservice.createAuthorizationHeader()
    })
        .then(response => response.json())
        .then(data => {
            outgoingData = data.filter(outgoing =>
                String(outgoing.tg_no).includes(filterInput)
            );
            currentPage = 1;
            renderTable();
        })
        .catch(error => console.error('Error filtering outgoing details:', error));
};


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
function checkSubWardenAccess() {
    const { token, role } = getAuthData();
    const tokenFromUrl = new URLSearchParams(window.location.search).get("token");

    if (!token || role !== "SUB_WARDEN" || token !== tokenFromUrl) {
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
        { id: "subwardendashboard", href: "subwardendashboard.html" },
        { id: "assetmanage", href: "assetmanage.html" },
        { id: "finemanage", href: "finemanage.html" },
        { id: "assignroom", href: "assignroom.html" },
        { id: "subwardenmessage", href: "subwardenmessage.html" },
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
    checkSubWardenAccess();
    appendTokenToLinks();
}

window.onload = init;
