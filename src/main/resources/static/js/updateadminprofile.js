
import {StorageService} from "./storageservice.js";
import {AdminModule} from "./adminmodule.js";

const firstname = document.getElementById("firstname").value;
const lastname = document.getElementById("lastname").value;
const email = document.getElementById("email").value;
const currentPassword = document.getElementById("currentPassword").value;
const newPassword = document.getElementById("newPassword").value;
const confirmNewPassword = document.getElementById("confirmNewPassword").value;

function showErrorAlert(message){

    const  errorMessage = document.getElementById("errorAlert");
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
function warningAlert(message){

    const  errorMessage = document.getElementById("wording");
    errorMessage.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}





// Load profile data and save a copy to track changes
async function getProfileDataByID() {

    const admin = new AdminModule();
    try {
        const id = StorageService.getUserId();
        const response = await admin.getAdminProfileDetails(id);

        // Populate fields
        document.getElementById("firstname").value = response.firstname || "";
        document.getElementById("lastname").value = response.lastname || "";
        document.getElementById("email").value = response.email || "";

    } catch (error) {
        console.log(error);
    }
}

function isValidEmail(email) {
    // Regular expression for validating an email address
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}

async function updateProfile() {

    // Get current values from the form
    const firstname = document.getElementById("firstname").value;
    const lastname = document.getElementById("lastname").value;
    const email = document.getElementById("email").value;

    console.log(firstname)
    console.log(lastname)
    console.log(email)


    if (!isValidEmail(email)) {
        console.log("Valid email address");
        showErrorAlert("Please Enter Valid Email Address")
        return;
    }
    if(firstname === '' || lastname === '' || email === ''){
        showErrorAlert("Please Enter All Details")
        return;
    }

    const id = StorageService.getUserId();

    const updateUser = { id, firstname, lastname, email };

    try {
        const admin = new AdminModule();
        const response = await admin.updateProfile(updateUser);

        console.log("Response received:", response); // Log the full response to inspect the structure

        // Check if the response contains an error message
        if (response.body && response.body.message === "Email already in use by another user") {

            getProfileDataByID().then(r => null);
            showSuccessAlert(response.body.message);

        } else if(response.body && response.body.message === "Profile updated successfully"){

            StorageService.saveToken(response.body.access_token);
            getProfileDataByID().then(r => null);
            showSuccessAlert(response.body.message);
        }else {
            showSuccessAlert(response.body.message);
            getProfileDataByID().then(r => null);
        }
    } catch (error) {
        showErrorAlert("An error occurred while updating the profile.")
        console.error("Error updating profile:", error);
        alert("An error occurred while updating the profile.");
    }


}


// Call the function to load data when the page loads
getProfileDataByID().then(r => null);

function togglePasswordFields() {
    const isChecked = document.getElementById("changePasswordCheckbox").checked;

    // Enable or disable password fields based on the checkbox state
    document.getElementById("currentPassword").disabled = !isChecked;
    document.getElementById("newPassword").disabled = !isChecked;
    document.getElementById("confirmNewPassword").disabled = !isChecked;

    // Enable or disable the Change Password button based on the checkbox state
    document.getElementById("updatepassword").disabled = !isChecked;
}


async function updatePassword(){

    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmNewPassword = document.getElementById("confirmNewPassword").value;

    const isChecked = document.getElementById("changePasswordCheckbox");

    if(!isChecked.checked){
        warningAlert("Please Select The Check Box First")
    }else {
        if(currentPassword === '' || newPassword === '' || confirmNewPassword === ''){
            showErrorAlert("Please Fill the All Fields !")
            return;
        }
        if(newPassword !== confirmNewPassword){
            showErrorAlert("New Password And Confirm Password are Not Matched !")
            return;
        }
        if(newPassword.length < 8){
            showErrorAlert("Password Must be Contain At Least 8 Characters !")
            return;
        }

        try {
            const id = StorageService.getUserId();
            const confirm ={
                id,
                currentPassword,
                newPassword
            }
            const admin = new AdminModule();
            const response = await admin.confirm(confirm)

            if(response.body && response.body.status === "Password matched"){

                const id = StorageService.getUserId();
                const updateRequst ={
                    id,
                    currentPassword,
                    newPassword
                }

                const admin = new AdminModule();
                const response2 = await admin.updatePassword(updateRequst);

                if(response2.message === "Password updated successfully") {

                    showSuccessAlert(response2.message);
                    document.getElementById("currentPassword").value ='';
                    document.getElementById("newPassword").value='';
                    document.getElementById("confirmNewPassword").value='';


                }else {

                    showErrorAlert(response2.message);
                }

            }else {
                    showErrorAlert(response.body.status);
            }

        }catch (error){
            console.log(error);
        }
    }


}
window.togglePasswordFields=togglePasswordFields

document.getElementById("changePasswordCheckbox").addEventListener("onclick", togglePasswordFields);

window.updateProfile=updateProfile

document.getElementById("userprofileupdate").addEventListener("onclick", updateProfile);

window.updatePassword=updatePassword

document.getElementById("updatepassword").addEventListener("onclick", updatePassword);