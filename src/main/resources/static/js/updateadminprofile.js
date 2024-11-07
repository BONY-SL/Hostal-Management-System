
import {StorageService} from "./storageservice.js";
import {AdminModule} from "./adminmodule.js";

const firstname = document.getElementById("firstname").value;
const lastname = document.getElementById("lastname").value;
const email = document.getElementById("email").value;
const currentPassword = document.getElementById("currentPassword").value;
const newPassword = document.getElementById("newPassword").value;
const confirmNewPassword = document.getElementById("confirmNewPassword").value;





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
        return;
    }
    if(firstname === '' || lastname === '' || email === ''){
        console.log("Please Enter All Details");
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
            alert("Error: " + response.body.message);

        } else if(response.body && response.body.message === "Profile updated successfully"){

            StorageService.saveToken(response.body.access_token);
            getProfileDataByID().then(r => null);
            alert(response.body.message);
        }else {
            alert(response.body.message);
            getProfileDataByID().then(r => null);
        }
    } catch (error) {
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


function updatePassword(){

    alert(1)

}
window.togglePasswordFields=togglePasswordFields

document.getElementById("changePasswordCheckbox").addEventListener("onclick", togglePasswordFields);

window.updateProfile=updateProfile

document.getElementById("userprofileupdate").addEventListener("onclick", updateProfile);

window.updatePassword=updatePassword

document.getElementById("updatepassword").addEventListener("onclick", updatePassword);