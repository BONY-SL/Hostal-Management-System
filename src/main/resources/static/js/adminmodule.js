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

}