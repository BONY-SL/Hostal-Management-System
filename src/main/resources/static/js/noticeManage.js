document.addEventListener("DOMContentLoaded", function() {
    toggleSections('viewNotices'); // Show view users by default
});

document.getElementById('viewUsersBtn').onclick = function() {
    toggleSections('viewNotices');
};

document.getElementById('addNoticeBtn').onclick = function() {
    toggleSections('AddNotices');
};

document.getElementById('updateNoticeBtn').onclick = function() {
    toggleSections('UpdateNotice');
};

document.getElementById('deleteNoticeBtn').onclick = function() {
    toggleSections('DeleteNotice');
};

function toggleSections(activeSection) {
    const sections = ['viewNotices','AddNotices', 'UpdateNotice', 'DeleteNotice'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}