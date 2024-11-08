import {StorageService} from "./storageservice.js";
const BASE_URL = "http://localhost:8080";

export class AuthService {

    // Register method
    register(request) {
        return fetch(`${BASE_URL}/hostalmanage/auth/register`, {
            method: "POST",
            headers: this.createAuthorizationHeader(),
            body: JSON.stringify(request),
        }).then(response => response.json());
    }

    // Login method
    login(request) {
        return fetch(`${BASE_URL}/hostalmanage/auth/authenticate`, {
            method: "POST",
            headers: this.createAuthorizationHeader(),
            body: JSON.stringify(request),
        }).then(response => response.json());
    }

// Logout method
    logOut() {
        return fetch(`${BASE_URL}/hostalmanage/auth/logout`, {
            method: "POST",
            headers: this.createAuthorizationHeader(),
        }).then(async (response) => {
            if (response.ok && response.headers.get("content-type")?.includes("application/json")) {
                return await response.json();
            } else {
                return { message: "Logged out successfully (no content)" };
            }
        });
    }

    refreshProfileToken() {
        return fetch(`${BASE_URL}/hostalmanage/auth/refresh-token`, {
            method: "POST",
            headers: this.createAuthorizationHeader(),
        }).then(response => response.json());
    }


    // Method to create the authorization header
    createAuthorizationHeader() {
        const token = StorageService.getToken();
        if (token) {
            return {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            };
        } else {
            return { 'Content-Type': 'application/json' };
        }
    }
}
