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

function createUser() {

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



    // You could send this user object to a server here using fetch or axios.
}
