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
document.getElementById('deleteUserBtn').onclick = function() {
    toggleSections('deleteUser');
};

function toggleSections(activeSection) {
    const sections = ['viewUsers', 'createUser', 'deleteUser'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}

async function createUser() {


    const firstname = document.getElementById("firstname").value;
    const lastname = document.getElementById("lastname").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    const user = {
        firstname: firstname,
        lastname: lastname,
        email: email,
        password: password,
        role: role,
    };

    const adminModule = new AdminModule();

    try {
        const response = await adminModule.createUser(user);

        console.log(response);

        if (response.message) {
            showSuccessAlert(response.message);
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