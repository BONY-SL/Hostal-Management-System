document.addEventListener("DOMContentLoaded", function () {
    const addNoticeBtn = document.getElementById("addNoticeBtn");
    const addNoticeSection = document.getElementById("addNoticeSection");
    const updateNoticeModal = document.getElementById("updateNoticeModal");
    const closeModal = document.getElementById("closeModal");
    const updateNoticeForm = document.getElementById("updateNoticeForm");
    const noticesTableBody = document.getElementById("noticesTableBody");
    const noticesTableContainer = document.getElementById("noticesTableContainer");

    // Toggle the form visibility when the "Add Notice" button is clicked
    addNoticeBtn.addEventListener("click", function () {
        addNoticeSection.style.display = (addNoticeSection.style.display === "none" || addNoticeSection.style.display === "") ? "block" : "none";
    });

    // Close the modal
    closeModal.addEventListener("click", function () {
        updateNoticeModal.style.display = "none";
    });

    // Add Notice Form Submission
    const addNoticeForm = document.getElementById("addNoticeForm");
    addNoticeForm.addEventListener("submit", function (event) {
        event.preventDefault();

        // Gather form data
        const formData = {
            id: document.getElementById("noticeId").value,
            title: document.getElementById("noticeTitle").value,
            content: document.getElementById("noticeContent").value,
            publishDate: document.getElementById("publishDate").value,
            publishTime: document.getElementById("publishTime").value
        };

        // Create a new row in the notices table
        addNoticeToTable(formData);

        // Clear the form and hide it
        addNoticeForm.reset();
        addNoticeSection.style.display = "none";
    });

    // Function to add notice to the table
    function addNoticeToTable(notice) {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${notice.id}</td>
            <td>${notice.title}</td>
            <td>${notice.content}</td>
            <td>${notice.publishDate}</td>
            <td>${notice.publishTime}</td>
            <td><button class="update-btn" data-id="${notice.id}">Update</button></td>
        `;
        noticesTableBody.appendChild(row);
        noticesTableContainer.style.display = "block";

        // Add event listener for update button
        row.querySelector(".update-btn").addEventListener("click", function () {
            openUpdateModal(notice);
        });
    }

    // Function to open the update modal
    function openUpdateModal(notice) {
        document.getElementById("updateNoticeId").value = notice.id;
        document.getElementById("updateNoticeTitle").value = notice.title;
        document.getElementById("updateNoticeContent").value = notice.content;
        document.getElementById("updatePublishDate").value = notice.publishDate;
        document.getElementById("updatePublishTime").value = notice.publishTime;
        updateNoticeModal.style.display = "block";
    }

    // Update Notice Form Submission
    updateNoticeForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const updatedNotice = {
            id: document.getElementById("updateNoticeId").value,
            title: document.getElementById("updateNoticeTitle").value,
            content: document.getElementById("updateNoticeContent").value,
            publishDate: document.getElementById("updatePublishDate").value,
            publishTime: document.getElementById("updatePublishTime").value
        };

        // Update the notice in the table
        updateNoticeInTable(updatedNotice);
        updateNoticeModal.style.display = "none";
    });

    // Function to update notice in the table
    function updateNoticeInTable(notice) {
        const rows = noticesTableBody.querySelectorAll("tr");
        rows.forEach(row => {
            const idCell = row.querySelector("td:first-child");
            if (idCell.innerText === notice.id) {
                row.querySelector("td:nth-child(2)").innerText = notice.title;
                row.querySelector("td:nth-child(3)").innerText = notice.content;
                row.querySelector("td:nth-child(4)").innerText = notice.publishDate;
                row.querySelector("td:nth-child(5)").innerText = notice.publishTime;
            }
        });
    }
});
