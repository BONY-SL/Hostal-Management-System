document.addEventListener("DOMContentLoaded", function () {
    const addNoticeBtn = document.getElementById("addNoticeBtn");
    const updateNoticeBtn = document.getElementById("updateNoticeBtn");
    const deleteNoticeBtn = document.getElementById("deleteNoticeBtn");
    const addNoticeSection = document.getElementById("addNoticeSection");
    const updateNoticeModal = document.getElementById("updateNoticeModal");
    const addNoticeForm = document.getElementById("addNoticeForm");
    const noticesTableContainer = document.querySelector(".noticeTableContainer");

    // Hide sections initially
    addNoticeSection.style.display = "none";
    updateNoticeModal.style.display = "none";
    noticesTableContainer.style.display = "block"; // Make sure the table is visible initially

    // Add Notice Button functionality
    addNoticeBtn.addEventListener("click", function () {
        // Show Add Notice form and hide Update Notice table/modal
        addNoticeSection.style.display = "block";
        updateNoticeModal.style.display = "none";
        noticesTableContainer.style.display = "none"; // Hide the full table
    });

    // Update Notice Button functionality
    updateNoticeBtn.addEventListener("click", function () {
        // Hide Add Notice form and show Update Notice modal
        addNoticeSection.style.display = "none";
        updateNoticeModal.style.display = "block";
        noticesTableContainer.style.display = "block"; // Show the full table
    });

    // Delete Notice Button functionality
    deleteNoticeBtn.addEventListener("click", function () {
        // Add logic to delete notices
        alert("Delete Notice functionality is under construction.");
    });

    // Add Notice Form Submission
    addNoticeForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const formData = {
            id: document.getElementById("noticeId").value,
            title: document.getElementById("noticeTitle").value,
            content: document.getElementById("noticeContent").value,
            publishDate: document.getElementById("publishDate").value,
            publishTime: document.getElementById("publishTime").value
        };

        try {
            // Send data to the backend
            const response = await fetch("http://localhost:8080/hostalmanage/warden/addNewNotice", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                // Successfully added notice
                const result = await response.json();
                alert("Notice added successfully!");

                addNoticeToTable(formData); // Add the notice to the table
                addNoticeForm.reset(); // Reset form fields
                addNoticeSection.style.display = "none"; // Hide form after submission
                noticesTableContainer.style.display = "block"; // Show the table again
            } else {
                alert("Failed to add notice. Please try again.");
            }
        } catch (error) {
            console.error("Error adding notice:", error);
            alert("Error connecting to the server.");
        }
    });

    // Function to add notice to the table
    function addNoticeToTable(notice) {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${notice.id}</td>
            <td>${notice.title}</td>
            <td>${notice.content}</td>
            <td>${notice.publishDate}</td>
            <td><button class="btn update">Update</button></td>
        `;
        const updateButton = row.querySelector(".btn.update");

        // Attach event listener to the Update button in the new row
        updateButton.addEventListener("click", function () {
            showUpdateNoticeModal(notice);
        });

        noticesTableContainer.querySelector("tbody").appendChild(row);
    }

    // Function to show update modal with existing notice data
    function showUpdateNoticeModal(notice) {
        document.getElementById("updateNoticeId").value = notice.id;
        document.getElementById("updateNoticeTitle").value = notice.title;
        document.getElementById("updateNoticeContent").value = notice.content;
        document.getElementById("updatePublishDate").value = notice.publishDate;

        updateNoticeModal.style.display = "block"; // Show the modal
    }

    // Close the modal functionality
    const closeModalBtn = document.getElementById("closeModal");
    closeModalBtn.addEventListener("click", function () {
        updateNoticeModal.style.display = "none"; // Close the modal
    });
});
