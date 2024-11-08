import {AuthService} from "./authservice.js";

document.addEventListener("DOMContentLoaded", function() {
    toggleSections('viewUsers3'); // Show view users by default
});

document.getElementById('viewRooms').onclick = function() {
    toggleSections('viewUsers3');
};
document.getElementById('createRooms').onclick = function() {
    toggleSections('createUser3');
};

function toggleSections(activeSection) {
    const sections = ['viewUsers3', 'createUser3'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}

document.getElementById("createRoomForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const roomData = {
        roomNumber: document.getElementById("roomNumber").value,
        floorNumber: parseInt(document.getElementById("floorNumber").value, 10),
        roomCapacity: parseInt(document.getElementById("roomCapacity").value, 10),
        description: document.getElementById("description").value,
        buildingId: parseInt(document.getElementById("buildingId").value, 10)
    };

    const auth = new AuthService();

    const response = await fetch("http://localhost:8080/hostalmanage/admin/createRoom" ,{
        method: "POST",
        headers: auth.createAuthorizationHeader(),
        body: JSON.stringify(roomData),
    }).then(response => response.json());
});