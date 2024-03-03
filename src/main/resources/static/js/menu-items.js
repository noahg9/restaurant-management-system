const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteMenuItem)
}

async function handleDeleteMenuItem(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const menuItemId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    const response = await fetch(`/api/menu-items/${menuItemId}`, {
        method: "DELETE",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    if (response.status === 204) {
        const row = document.getElementById(`menu_item_${menuItemId}`);
        row.parentNode.removeChild(row);
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

const nameInput = document.getElementById("nameInput");
const priceInput = document.getElementById("priceInput");
const addButton = document.getElementById("addButton");
const menuItemTableBody = document.getElementById("menuItemTableBody");

async function addNewMenuItem() {
    const response = await fetch(`/api/menu-items`, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: nameInput.value,
            price: priceInput.value
        })
    })
    if (response.status === 201) {
        /**
         * @type {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number, restaurant: string}}
         */
        const menuItem = await response.json()
        addMenuItemToTable(menuItem);
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

/**
 * @param {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number, restaurant: string}} menuItem
 */
function addMenuItemToTable(menuItem) {
    const tableRow = document.createElement("tr");
    tableRow.id = `menu_item_${menuItem.id}`;
    tableRow.innerHTML = `
        <td>${menuItem.name}</td>
        <td>${menuItem.price}</td>
        <td>${menuItem.course}</td>
        <td>${menuItem.vegetarian}</td>
        <td>${menuItem.spiceLvl}</td>
        <td>${menuItem.restaurant ? menuItem.restaurant.name : 'N/A'}</td>
        <td><a href="/menu-item?id=${menuItem.id}">Details</a></td>
        <td><button type="button" class="btn btn-danger btn-sm">Delete</button></td>
    `
    menuItemTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDeleteMenuItem)
}

addButton.addEventListener("click", addNewMenuItem);
