import {AuthService} from "./authservice.js";


const BASE_URL = "http://localhost:8080";


export class AdminModule{


    createUser(request) {

        const authservice = new AuthService();
        return fetch(`${BASE_URL}/hostalmanage/admin/createuser`, {
            method: "POST",
            headers: authservice.createAuthorizationHeader(),
            body: JSON.stringify(request),
        }).then(response => response.json());
    }

    getSystemUser(){

        const authservice = new AuthService();
        return fetch(`${BASE_URL}/hostalmanage/admin/getSystemUsers`, {
            method: "GET",
            headers: authservice.createAuthorizationHeader(),
        }).then(response => response.json());
    }

     submitList(studentEntries) {
         const authservice = new AuthService();
         return fetch(`${BASE_URL}/hostalmanage/admin/savedstudentemail`, {
             method: "POST",
             headers: authservice.createAuthorizationHeader(),
             body: JSON.stringify(studentEntries),
         }).then(response => response.json());
    }

    async getRegisterdStudents() {

            const response = await fetch(`${BASE_URL}/hostalmanage/auth/getAllRegisterdStudents`);
            const textResponse = await response.text(); // Get raw text for inspection
            console.log("Raw Response:", textResponse); // Log the raw response
            // Attempt to parse if it looks like JSON
            return textResponse;
    }

    async getAdminProfileDetails(id) {
        const authservice = new AuthService();
        return fetch(`${BASE_URL}/hostalmanage/admin/getadmin?id=${id}`, {  // Use ${id} to insert the id value correctly
            method: "GET",
            headers: authservice.createAuthorizationHeader(),
        }).then(response => response.json());
    }

    async updateProfile(updateUser) {

        const authservice = new AuthService();
        return fetch(`${BASE_URL}/hostalmanage/admin/updateprofile`, {
            method: "PUT",
            headers: authservice.createAuthorizationHeader(),
            body: JSON.stringify(updateUser),
        }).then(response => response.json());
    }

    async confirm(confirmold){

        const authservice = new AuthService();

        return fetch(`${BASE_URL}/hostalmanage/admin/confirmold`,{
            method:"POST",
            headers : authservice.createAuthorizationHeader(),
            body: JSON.stringify(confirmold),
        }).then(response => response.json())
    }


    async updatePassword(updateRequest){

        const authservice = new AuthService();

        return fetch(`${BASE_URL}/hostalmanage/admin/updateps`,{
            method:"PUT",
            headers : authservice.createAuthorizationHeader(),
            body: JSON.stringify(updateRequest),
        }).then(response => response.json())
    }
}