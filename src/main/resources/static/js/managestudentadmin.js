import {AdminModule} from "./adminmodule.js";

document.addEventListener("DOMContentLoaded", function() {
    toggleSections('viewUsers2'); // Show view users by default
});

document.getElementById('viewUsersBtn2').onclick = function() {
    toggleSections('viewUsers2');
};
document.getElementById('createUserBtn2').onclick = function() {
    toggleSections('createUser2');
};

function toggleSections(activeSection) {
    const sections = ['viewUsers2', 'createUser2'];
    sections.forEach(section => {
        document.getElementById(section).style.display = (section === activeSection) ? 'block' : 'none';
    });
}


let entryCount = 1;
const studentEntries = [];

getRegisterdStudents().then(r => null);

// Function to add a new student entry row
function addEntry() {
    const entryFields = document.getElementById("entryFields");

    if (!entryFields) {
        console.error("Cannot find 'entryFields' element.");
        return;
    }

    const newEntry = document.createElement("div");
    newEntry.className = "form-group";
    newEntry.id = `entry-${entryCount}`;
    newEntry.innerHTML = `
        <label style="text-align: left">TG Number</label>
        <input type="text" id="tgnumber-${entryCount}" name="tgnumber" required maxlength="5">

        <label style="text-align: left">Student Email</label>
        <input type="email" id="email-${entryCount}" name="email" required>

        <small id="error-${entryCount}" class="error-message" style="color: red; display: none;"></small>
        <small id="success-${entryCount}" class="sucess-message" style="color: #0e850f; display: none;"></small>
        <button type="button" class="createuserbutton2" id="removemail" onclick="removeEntry(${entryCount})">Remove</button>
    `;

    entryFields.appendChild(newEntry);
    entryCount++;
}

// Function to remove a specific student entry row
function removeEntry(id) {
    const entry = document.getElementById(`entry-${id}`);
    if (entry) {
        entry.remove();
        studentEntries.splice(id, 1);  // Remove from an array
    }
}

// Function to submit the list of student entries to the backend
async function submitList() {
    // Gather data from all inputs in the form
    studentEntries.length = 0; // Clear previous entries

    for (let i = 0; i < entryCount; i++) {
        const tgnumberField = document.getElementById(`tgnumber-${i}`);
        const emailField = document.getElementById(`email-${i}`);
        const errorField = document.getElementById(`error-${i}`);

        // Reset styles and error messages
        if (tgnumberField && emailField) {
            tgnumberField.style.borderColor = "";
            emailField.style.borderColor = "";
            errorField.style.display = "none";
        }

        if (tgnumberField && emailField) {
            studentEntries.push({
                tgnumber: tgnumberField.value,
                email: emailField.value
            });
        }


    }

    // SweetAlert2 confirmation dialog
    const confirmation = await Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to submit the list?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#068f8f',
        cancelButtonColor: '#f56f6f',
        confirmButtonText: 'Yes, submit it!'
    });
    console.log(studentEntries)

    const adminModule = new AdminModule();

    if (confirmation.isConfirmed) {

        try {
            const response = await adminModule.submitList(studentEntries);

            if (response.message) {
                console.log(response.message);

                for (let i = 0; i < entryCount; i++) {

                    const emailField = document.getElementById(`email-${i}`);
                    const tgnumberField = document.getElementById(`tgnumber-${i}`);
                    const successField = document.getElementById(`success-${i}`);

                    if (emailField && tgnumberField) {
                        emailField.style.borderColor = "green";
                        tgnumberField.style.borderColor = "green";
                        successField.style.display = "block";
                        successField.textContent = "Email and TG saved successfully.";
                    }
                }
            } else if (response.errors) {

                response.errors.forEach(errorMessage => {
                    const emailMatch = errorMessage.match(/email: (.+)$/);
                    const duplicateEmail = emailMatch ? emailMatch[1] : null;

                    for (let i = 0; i < entryCount; i++) {

                        const emailField = document.getElementById(`email-${i}`);
                        const tgnumberField = document.getElementById(`tgnumber-${i}`);
                        const errorField = document.getElementById(`error-${i}`);
                        const successField = document.getElementById(`success-${i}`);

                        if (emailField && emailField.value === duplicateEmail) {
                            emailField.style.borderColor = "red";
                            tgnumberField.style.borderColor = "red";
                            errorField.style.display = "block";
                            errorField.textContent = errorMessage;

                        } else if (emailField && tgnumberField) {

                            emailField.style.borderColor = "green";
                            tgnumberField.style.borderColor = "green";
                            successField.style.display = "block";
                            successField.textContent = "Add Success";
                        }
                    }
                });
            }
        } catch (error) {
            console.error("Error occurred:", error);
        }
    }
}

async function getRegisterdStudents() {
    const adminModule = new AdminModule();

    try {
        const response = await adminModule.getRegisterdStudents();
        const textResponse = response.toString();

        console.log("Raw Response:", textResponse);

        // Split the concatenated JSON arrays
        const jsonParts = textResponse.split("]["); // Splits at the ][

        // Parse each part as JSON
        const firstArray = JSON.parse(jsonParts[0] + "]"); // Adds the closing bracket
        const secondArray = JSON.parse("[" + jsonParts[1]); // Adds the opening bracket

        // Combine the lists into one and remove duplicates based on student_id
        const mergedList = [...firstArray, ...secondArray];
        const uniqueUserList = mergedList.filter((student, index, self) =>
            index === self.findIndex((s) => s.student_id === student.student_id)
        );

        // Find the tbody element in the table
        const tbody = document.querySelector("#viewUsers2 tbody");
        tbody.innerHTML = "";

        // Populate the table with user data
        uniqueUserList.forEach(student => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${student.student_id}</td>
                <td>${student.tg_no}</td>
                <td>${student.fullname}</td>
                <td>${student.email}</td>
                <td><p style="background-color: #34a639;border-radius: 10px;padding: 3px 3px 3px 3px;text-align: center">Registerd</p></td>
            `;

            // Append the row to the table body
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to fetch registered students:", error);
    }
}







window.submitList = submitList;
document.getElementById("createStudentId").addEventListener("onclick", submitList);

window.addEntry = addEntry;
document.getElementById("addItem").addEventListener("onclick", addEntry);

window.removeEntry = removeEntry;