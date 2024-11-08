const TOKEN = "token";
const REFRESHTOKEN = "refreshToken";
const USER ="user";

export class StorageService {

    constructor() { }


    static saveToken(token){

        window.localStorage.removeItem(TOKEN);
        window.localStorage.setItem(TOKEN,token);

    }

    static saveUser(user){

        window.localStorage.removeItem(USER);
        window.localStorage.setItem(USER,JSON.stringify(user));

    }


    static getToken() {
        return window.localStorage.getItem(TOKEN);
    }

    static getUser() {
        const user = window.localStorage.getItem(USER);
        return user ? JSON.parse(user) : null;
    }

    static getUserId() {

        const user = this.getUser();
        if(user == null)  return '';
        return user.id;

    }

    static getUserRole(){
        const user = this.getUser();

        if(user == null) return "";
        return user.role;
    }
    static isAdminLoggedIn() {

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "ADMIN";
    }


    static isStudentLoggedIn() {

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "STUDENT";
    }



    static isWardenLoggedIn() {

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "WARDEN";
    }



    static isSubWardenLoggedIn(){

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "SUB_WARDEN";
    }


    static isMaintainLoggedIn(){

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "MAINTAIN_SUPERVISOR";
    }

    static isDeanLoggedIn(){

        if (this.getToken() == null) return false;

        const role = this.getUserRole();
        return role === "DEAN";
    }

    static logOut(){

        window.localStorage.removeItem(TOKEN);
        window.localStorage.removeItem(USER);

    }

}