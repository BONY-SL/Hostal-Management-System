document.addEventListener("DOMContentLoaded", function () {
    const complaintsCard = document.getElementById("complaintsCard");
    const assetsCard = document.getElementById("assetsCard");
    const studentsCard = document.getElementById("studentsCard");
    const noticesCard = document.getElementById("noticesCard");
    const finesCard = document.getElementById("finesCard");
    const outgoingCard = document.getElementById("outgoingCard");

    complaintsCard.addEventListener("click", function () {
        window.location.href = "wardenViewComplaints.html";
    });

    assetsCard.addEventListener("click", function () {
        window.location.href = "wardenViewAssets.html";
    });

    studentsCard.addEventListener("click", function () {
        window.location.href = "wardenViewStudents.html";
    });

    noticesCard.addEventListener("click", function () {
        window.location.href = "wardenManageNotices.html";
    });

    finesCard.addEventListener("click", function () {
        window.location.href = "wardenViewFines.html";
    });

    outgoingCard.addEventListener("click", function () {
        window.location.href = "wardenViewOutgoing.html";
    });
});
