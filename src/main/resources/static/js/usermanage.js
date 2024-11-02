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

