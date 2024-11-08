import { AuthService } from "./authservice.js";
import { StorageService } from "./storageservice.js";



const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
function showErrorAlert(message){

    const  errorMessage = document.getElementById("errorAlert");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}
function showErrorAlert2(message){

    const  errorMessage = document.getElementById("errorAlert2");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}


function showSuccessAlert(message){

    const  errorMessage = document.getElementById("successAlert");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}



registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

async function signUp() {


    const firstname = document.getElementById("firstname").value;
    const lastname = document.getElementById("lastname").value;
    const email = document.getElementById("email2").value;
    const password = document.getElementById("password2").value;

    if (firstname === "") {
        showErrorAlert("First Name is required!");
        return false;
    }
    if (lastname === "") {
        showErrorAlert("Last Name is required!");
        return false;
    }
    if (email === "") {
        showErrorAlert("Email is required!");
        return false;
    }
    if (password === "") {
        showErrorAlert("Password is required!");
        return false;
    }
    if (password.length < 8) {
        showErrorAlert("Password must be at least 8 characters long!");
        return false;
    }
    if(!email.match(validRegex)){
        showErrorAlert("Please Enter Valid Email Address");
        return false;
    }

    const registerUser ={
        firstname:firstname,
        lastname:lastname,
        email:email,
        password:password
    };

    const authService = new AuthService();
    try{
        const response = await authService.register(registerUser);
        if(response.body.message !== "OTP"){
            showErrorAlert(response.body.message);
        }else {
            showSuccessAlert("Please Check Your Email Inbox To Activate Your Account");
        }

        console.log(response.body.message)
    }catch (error) {
        console.error("Error occurred:", error);
    }
}

async function signIn() {

    const email = document.getElementById("_email").value;
    const password = document.getElementById("_password").value;

    if (email === "" || password === "") {
        showErrorAlert2("Email And Password Required!");
        return false;
    }

    const authService = new AuthService();

    const user = {
        email: email,
        password: password
    };

    try {
        const response = await authService.login(user);

        if (response.userId) {

            const user = {id: response.userId, role: response.userRole};
            StorageService.saveUser(user);
            StorageService.saveToken(response.access_token);

            const userRole = StorageService.getUserRole();

            const token = StorageService.getToken();

            // Check if the user is an admin and redirect
            if (StorageService.isAdminLoggedIn()) {
                window.location.href = `admindashboard.html?userRole=${userRole}`;
            }else if(StorageService.isDeanLoggedIn()){
                window.location.href = '';
            }else if(StorageService.isStudentLoggedIn()){
                window.location.href = `studentDashboard.html?token=${token}`;
            }else if(StorageService.isMaintainLoggedIn()){
                window.location.href = "";
            }else if(StorageService.isWardenLoggedIn()){
                window.location.href = `wardenDashboard.html?token=${token}`;
            }else if(StorageService.isSubWardenLoggedIn()){
                window.location.href = `subwardendashboard.html?token=${token}`;
            }else {
                alert("Bad Credentials");
            }
        } else {
            showErrorAlert2("Login Error");
        }
    } catch (error) {
        console.error(error);
        showErrorAlert2("An error occurred. Please try again.");
    }
}

window.signIn = signIn;

document.getElementById("loginButtonId").addEventListener("onclick", signIn);

window.signUp = signUp;

document.getElementById("signButtonId").addEventListener("onclick", signUp);