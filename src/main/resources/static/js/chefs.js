const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteChef)
}

async function handleDeleteChef(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const chefId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    const response = await fetch(`/api/chefs/${chefId}`, {
        method: "DELETE",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    if (response.status === 204) {
        const row = document.getElementById(`chef_${chefId}`);
        row.parentNode.removeChild(row);
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

const firstNameInput = document.getElementById("firstNameInput");
const lastNameInput = document.getElementById("lastNameInput");
const addButton = document.getElementById("addButton");
const chefTableBody = document.getElementById("chefTableBody");

async function addNewChef()
{
    const response = await fetch(`/api/chefs`, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
        })
    })
    if (response.status === 201) {
        /**
         * @type {{id: number, firstName: string, lastName: string, dateOfBirth: date, restaurant: String}}
         */
        const chef = await response.json()
        addChefToTable(chef);
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

/**
 * @param {{id: number, firstName: string, lastName: string, dateOfBirth: date, restaurant: string}} chef
 */
function addChefToTable(chef) {
    const tableRow = document.createElement("tr");
    tableRow.id = `chef_${chef.id}`;
    tableRow.innerHTML = `
        <td>${chef.firstName}</td>
        <td>${chef.lastName}</td>
        <td>${chef.dateOfBirth}</td>
        <td>${chef.restaurant ? chef.restaurant.name : 'N/A'}</td>
        <td><a href="/chef/chef?id=${chef.id}">Details</a></td>
        <td><button type="button" class="btn btn-danger btn-sm">Delete</i></button></td>
    `
    chefTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDeleteChef)
}

addButton.addEventListener("click", addNewChef);
