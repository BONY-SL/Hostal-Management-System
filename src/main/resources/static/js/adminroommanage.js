document.addEventListener("DOMContentLoaded", function() {
    toggleSections('viewUsers3'); // Show view users by default
});

document.getElementById('viewRooms').onclick = function() {
    toggleSections('viewUsers3');
};
document.getElementById('createRooms').onclick = function() {
    toggleSections('createUser3');
};
document.getElementById('updateRomeChanges').onclick = function() {
    toggleSections('deleteUser3');
};

function toggleSections(activeSection) {
    const sections = ['viewUsers3', 'createUser3', 'deleteUser3'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}