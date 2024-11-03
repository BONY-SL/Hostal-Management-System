document.getElementById('displayFineButton').addEventListener('click', function() {
    // Hide the existing content and show the display fine section
    document.querySelector('.content-wrapper').style.display = 'none';
    document.getElementById('displayFineSection').style.display = 'block';
});

document.getElementById('addFineButton').addEventListener('click', function() {
    // Hide the display fine section and show the content wrapper
    document.getElementById('displayFineSection').style.display = 'none';
    document.querySelector('.content-wrapper').style.display = 'flex';
});