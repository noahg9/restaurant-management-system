const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteMenuItem)
}

async function handleDeleteMenuItem(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const menuItemId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    const response = await fetch(`/api/menu-items/${menuItemId}`, {
        method: "DELETE"
    });
    if (response.status === 204) {
        const row = document.getElementById(`menu_item_${menuItemId}`);
        row.parentNode.removeChild(row);
    }
}

const nameInput = document.getElementById("inputName");
const priceInput = document.getElementById("inputPrice");
const courseInput = document.getElementById("inputCourse");
const vegetarianInput = document.getElementById("inputVegetarian");
const spiceLvlInput = document.getElementById("inputSpiceLvl");
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
            price: priceInput.value,
            course: courseInput.value,
            vegetarian: vegetarianInput.value,
            spiceLvl: spiceLvlInput.value
        })
    })
    if (response.status === 201) {
        /**
         * @type {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number}}
         */
        const menuItem = await response.json()
        addMenuItemToTable(menuItem);
    } else {
        alert("Something went wrong!");
    }}

/**
 * @param {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number}} menuItem
 */
function addMenuItemToTable(menuItem) {
    const tableRow = document.createElement("tr");
    tableRow.id = `issue_${menuItem.id}`;
    tableRow.innerHTML = `
        <td>${menuItem.name}</td>
        <td>${menuItem.price}</td>
        <td>${menuItem.course}</td>
        <td>${menuItem.vegetarian}</td>
        <td>${menuItem.spiceLvl}</td>
        <td></td>
        <td><a href="/menu-item?id=${menuItem.id}">Details</a></td>
        <td><button type="button" class="btn btn-danger btn-sm">Delete</button></td>
    `
    menuItemTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDeleteMenuItem)
}

addButton.addEventListener("click", addNewMenuItem);
