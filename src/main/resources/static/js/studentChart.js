import { AuthService } from "./authservice.js"; // Import the AuthService class

// Fetch data from the API for power consumption
const authservice = new AuthService();
fetch('http://localhost:8080/hostalmanage/student/powerConsumption/101', {
    method: "GET",
    headers: authservice.createAuthorizationHeader(),
})
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        // Extracting the labels (dates) and data (power consumption) from the API response
        const labels = data.map(item => {
            const date = new Date(item[1]); // Extracting the date string and creating a Date object
            return date.toLocaleDateString(); // Format the date as a string (e.g., 'MM/DD/YYYY')
        });
        const consumptionData = data.map(item => item[0]); // Power consumption values are at index 0

        // Initialize the line chart for power consumption
        var ctx1 = document.getElementById('salesChart').getContext('2d');
        var salesChart = new Chart(ctx1, {
            type: 'line',
            data: {
                labels: labels, // Set labels dynamically from the API data
                datasets: [{
                    label: 'Power Consumption (kWh)',
                    data: consumptionData, // Set data dynamically from the API data
                    backgroundColor: 'rgba(6, 143, 143, 0.2)',
                    borderColor: '#068f8f',
                    borderWidth: 2,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: { title: { display: true, text: 'Date' } },
                    y: { title: { display: true, text: 'Power Consumption (kWh)' } }
                }
            }
        });

        // Generate data for the histogram
        // For example, we could sum consumption for each weekday and use it for the histogram
        const weekdayConsumption = [0, 0, 0, 0, 0, 0, 0]; // Array to store total consumption for each weekday (Mon-Sun)

        // Loop through each data item and sum the consumption for the corresponding weekday
        data.forEach(item => {
            const date = new Date(item[1]);
            const dayOfWeek = date.getDay(); // Get the day of the week (0 = Sunday, 6 = Saturday)
            weekdayConsumption[dayOfWeek] += item[0]; // Add the consumption for the corresponding day
        });

        // Initialize the histogram (bar chart) for daily activity
        var ctx2 = document.getElementById('histogramChart').getContext('2d');
        var histogramChart = new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'], // Days of the week
                datasets: [{
                    label: 'Power Consumption by Day',
                    data: weekdayConsumption, // Daily consumption data from the API
                    backgroundColor: 'rgba(255, 99, 132, 0.5)', // Color of bars
                    borderColor: 'rgba(255, 99, 132, 1)', // Border color of bars
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: { title: { display: true, text: 'Day of the Week' } },
                    y: { title: { display: true, text: 'Power Consumption (kWh)' } }
                }
            }
        });
    })
    .catch(error => console.error('Error fetching data:', error));
