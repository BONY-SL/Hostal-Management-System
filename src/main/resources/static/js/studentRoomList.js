import { AuthService } from "./authservice.js";

const itemsPerPage = 6;
let currentPage = 1;
let filteredData = [];
let roomsData = []; // Added this line to define roomsData

gerRooms(); // Call the function to get rooms data

// Get rooms data
function gerRooms() {
  // Create an instance of AuthService to get the authorization header
  const authservice = new AuthService();

  fetch("http://localhost:8080/hostalmanage/student/rooms", {
    method: "GET",
    headers: authservice.createAuthorizationHeader(),
  })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch rooms data");
        }
        return response.json();
      })
      .then((data) => {
        roomsData = data;
        filteredData = [...roomsData];
        renderPage(currentPage);
        setupPagination();
      })
      .catch((error) => console.error("Error fetching room data:", error));
}

// Render the current page of cards
function renderPage(page) {
  const cardsContainer = document.querySelector("#roomCards");
  cardsContainer.innerHTML = ""; // Clear existing cards
  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const roomsToDisplay = filteredData.slice(startIndex, endIndex);

  // Create a card for each room
  roomsToDisplay.forEach((room) => {
    const card = document.createElement("div");
    card.classList.add("col-md-4", "mb-4");
    card.innerHTML = `
      <div class="card">
        <div class="card-header">
          <h5 class="card-title">Room Number: ${room.roomNumber}</h5>
        </div>
        <div class="card-body">
          <p class="card-text"><strong>Floor Number:</strong> ${room.floorNumber}</p>
          <p class="card-text"><strong>Capacity:</strong> ${room.roomCapacity}</p>
          <p class="card-text"><strong>Description:</strong> ${room.description}</p>
          <p class="card-text"><strong>Building ID:</strong> ${room.buildingId}</p>
        </div>
        <button type="button" class="btn btn-secondary" onclick="openModal()">Request Room</button>
      </div>
    `;
    cardsContainer.appendChild(card);
  });
}

// Create pagination buttons dynamically based on filtered data
function setupPagination() {
  const pagination = document.querySelector("#pagination");
  pagination.innerHTML = ""; // Clear previous pagination buttons
  const totalPages = Math.ceil(filteredData.length / itemsPerPage);

  for (let i = 1; i <= totalPages; i++) {
    const li = document.createElement("li");
    li.classList.add("page-item");

    const link = document.createElement("a");
    link.classList.add("page-link");
    link.href = "#";
    link.textContent = i;
    link.addEventListener("click", () => changePage(i));

    li.appendChild(link);
    pagination.appendChild(li);
  }
}

// Change the page
function changePage(page) {
  currentPage = page;
  renderPage(currentPage);
}

window.searchRoomById=searchRoomById;

document.getElementById("searchRoomById").addEventListener("onclick", searchRoomById);


// Search for a room by ID
function searchRoomById(event) {
  event.preventDefault();
  const roomId = document.getElementById("roomIdInput").value.trim();

  if (roomId) {
    const authservice = new AuthService();

    fetch(`http://localhost:8080/hostalmanage/student/room/${roomId}`, {
      method: "GET",
      headers: authservice.createAuthorizationHeader(),
    })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Room not found");
          }
          return response.json();
        })
        .then((room) => {
          filteredData = [room]; // Use only the searched room for display
          currentPage = 1;
          renderPage(currentPage);
          setupPagination(); // Reset pagination to a single page if only one result
        })
        .catch((error) => {
          // If room not found, show "Not Found" message
          filteredData = [
            {
              roomNumber: "Not Found",
              floorNumber: "-",
              roomCapacity: "-",
              description: "No matching room found",
              buildingId: "-",
            },
          ];
          currentPage = 1;
          renderPage(currentPage);
          setupPagination();
        });
  } else {
    // Reset filtered data to the full list if search input is empty
    filteredData = [...roomsData];
    currentPage = 1;
    renderPage(currentPage);
    setupPagination();
  }
}

// Open modal function
function openModal() {
  document.getElementById("myModal").style.display = "block";
}


// Close modal function
function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

// Ensure modal closes when clicking outside of it
window.onclick = function(event) {
  const modal = document.getElementById("myModal");
  if (event.target === modal) {
    modal.style.display = "none";
  }
};


