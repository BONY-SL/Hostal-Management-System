const itemsPerPage = 6; // Number of cards per page
let currentPage = 1;
let roomsData = []; // Holds the full dataset
let filteredData = []; // Holds search results

document.addEventListener("DOMContentLoaded", function () {
  fetch("/hostalmanage/rooms")
    .then((response) => response.json())
    .then((data) => {
      roomsData = data;
      filteredData = [...roomsData]; // Initialize filteredData with all rooms
      renderPage(currentPage);
      setupPagination();
    })
    .catch((error) => console.error("Error fetching room data:", error));
});

// Render the current page of cards
function renderPage(page) {
  const cardsContainer = document.querySelector("#roomCards");
  cardsContainer.innerHTML = ""; // Clear existing cards
  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const roomsToDisplay = filteredData.slice(startIndex, endIndex);

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
    li.innerHTML = `
      <a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
    `;
    pagination.appendChild(li);
  }
}

function changePage(page) {
  currentPage = page;
  renderPage(currentPage);
}

// Search for a room by ID
function searchRoomById(event) {
  event.preventDefault();
  const roomId = document.getElementById("roomIdInput").value.trim();

  if (roomId) {
    fetch(`/hostalmanage/room/${roomId}`)
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

// Open and close modal functions
function openModal() {
  document.getElementById("myModal").style.display = "block";
}

function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

window.onclick = function (event) {
  const modal = document.getElementById("myModal");
  if (event.target === modal) {
    modal.style.display = "none";
  }
};
