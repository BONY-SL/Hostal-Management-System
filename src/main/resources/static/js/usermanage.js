import {AdminModule} from "./adminmodule.js";

document.addEventListener("DOMContentLoaded", function() {
    toggleSections('viewUsers'); // Show view users by default
});

document.getElementById('viewUsersBtn').onclick = function() {
    toggleSections('viewUsers');
};
document.getElementById('createUserBtn').onclick = function() {
    toggleSections('createUser');
};


function toggleSections(activeSection) {
    const sections = ['viewUsers', 'createUser'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}

async function getSystemUser() {
    const adminModule = new AdminModule();
    const userList = await adminModule.getSystemUser(); // Await the fetch request

    // Find the tbody element in the table
    const tbody = document.querySelector("#viewUsers tbody");

    // Clear any existing rows in the table
    tbody.innerHTML = "";

    // Populate the table with user data
    userList.forEach(user => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.firstname} ${user.lastname}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
        `;

        // Append the row to the table body
        tbody.appendChild(row);
    });
}


getSystemUser().then(r => null);

async function createUser() {


    const firstname = document.getElementById("firstname").value;
    const lastname = document.getElementById("lastname").value;
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;

    const user = {
        firstname: firstname,
        lastname: lastname,
        email: email,
        password: null,
        role: role,
    };

    const adminModule = new AdminModule();

    try {
        const response = await adminModule.createUser(user);

        console.log(response);

        if (response.message) {
            showSuccessAlert(response.message);
            getSystemUser().then(r => null);
            console.log(response.message);
        } else {
            showErrorAlert("Email Already Exsist")
            console.error("Unexpected response format:", response);
        }
    } catch (error) {
        console.error("Error occurred:", error);
    }
}

function showSuccessAlert(message){

    const  errorMessage = document.getElementById("successAlert");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}

function showErrorAlert(message){

    const  errorMessage = document.getElementById("errorAlert");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}


window.createUser = createUser;


document.getElementById("createuserId").addEventListener("onclick", createUser);