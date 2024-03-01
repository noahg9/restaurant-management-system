const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteChef)
}

async function handleDeleteChef(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const chefId = rowId.substring(rowId.indexOf('_') + 1);
    const response = fetch(`/api/chefs/${chefId}`, {
        method: "DELETE"
    });
    if (response === 204) {
        const row = document.getElementById(chefId);
        row.parentNode.removeChild(row);
    }
}

const firstNameInput = document.getElementById("inputFirstName");
const lastNameInput = document.getElementById("inputLastName");
const dateOfBirthInput = document.getElementById("inputDateOfBirth");
const addButton = document.getElementById("addButton");
const chefTableBody = document.getElementById("chefsTableBody");

async function addNewChef() {
    const response = await fetch(`/api/chefs`, {
        method: "POST",
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            dateOfBirth: dateOfBirthInput.value
        }),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
    if (response.status === 201) {
        const chef = await response.json()
        addChefToTable(chef);
    }
}

/**
 * @param {{id: number, firstName: string, lastName: string, dateOfBirth: date}} chef
 */
function addChefToTable(chef) {
    const tableRow = document.createElement("tr");
    tableRow.id = `issue_${chef.id}`;
    tableRow.innerHTML = `
        <td>${chef.firstName}</td>
        <td>${chef.lastName}</td>
        <td>${chef.dateOfBirth}</td>
        <td></td>
        <td><a href="/chef?id=${chef.id}">Details</a></td>
        <td><button type="button" class="btn btn-danger btn-sm">Delete</button></td>
    `
    chefTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDeleteChef)
}

addButton.addEventListener("click", addNewChef);

