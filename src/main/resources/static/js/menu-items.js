import {header, token} from "./util/csrf.js";

async function fillMenuItemsTable() {
    const response = await fetch('/api/menu-items', {
        headers: {
            "Accept": "application/json", [header]: token
        }
    });
    if (response.status === 200) {
        const menuItems = await response.json();
        menuItems.forEach(menuItem => {
            addMenuItemToTable(menuItem)
        })
    }
}

const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteMenuItem)
}

async function handleDeleteMenuItem(event) {
    const row = event.target.closest('.card');
    const menuItemId = row.dataset.menuItemId;
    const response = await fetch(`/api/menu-items/${menuItemId}`, {
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.status === 204) {
        row.remove()
    }
}

const nameInput = document.getElementById("nameInput");
const priceInput = document.getElementById("priceInput");
const courseInput = document.getElementById("courseInput");
const vegetarianInput = document.getElementById("vegetarianInput");
const spiceLvlInput = document.getElementById("spiceLvlInput");
const addButton = document.getElementById("addButton");
const menuItemBody = document.getElementById("menuItemBody");

async function addNewMenuItem() {
    const response = await fetch(`/api/menu-items`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: nameInput.value,
            price: priceInput.value,
            course: courseInput.value,
            vegetarian: vegetarianInput.checked,
            spiceLvl: spiceLvlInput.value
        })
    })
    if (response.status === 201) {
        /**
         * @type {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number}}
         */
        const menuItem = await response.json()
        addMenuItemToTable(menuItem);
    }
}

/**
 * @param {{id: number, name: string, price: number, course: string, vegetarian: boolean, spiceLvl: number}} menuItem
 */
function addMenuItemToTable(menuItem) {
    const courseGroup = document.getElementById(menuItem.course + "-group"); // Check if group exists
    let cardGroup;
    if (!courseGroup) {
        // Create a new group if it doesn't exist
        cardGroup = document.createElement("div");
        cardGroup.classList.add("course-group", "mb-4", "row"); // Add Bootstrap classes for rows
        cardGroup.id = menuItem.course + "-group";

        const groupName = document.createElement("h2");
        groupName.textContent = menuItem.course; // Use course name as group header
        cardGroup.appendChild(groupName);

        menuItemBody.appendChild(cardGroup);
    } else {
        cardGroup = courseGroup;
    }

    const cardColumn = document.createElement("div");
    cardColumn.classList.add("col-md-6"); // Bootstrap class for columns
    const card = document.createElement("div");
    card.classList.add("card", "mb-3"); // Adjusted width for two cards per row
    const vegetarianIndicator = menuItem.vegetarian ? "(V)" : ""; // "(V)" for vegetarian, empty string for non-vegetarian
    card.innerHTML = `
        <div class="card-body" style="cursor: pointer;">
            <h5 class="card-title">${menuItem.name} ${vegetarianIndicator}</h5>
            <p class="card-text">€${menuItem.price}</p>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
        </div>
    `;
    card.dataset.menuItemId = menuItem.id;
    cardColumn.appendChild(card);
    cardGroup.appendChild(cardColumn);

    const newDeleteButton = card.querySelector('button');
    newDeleteButton.addEventListener("click", (event) => {
        event.stopPropagation();
        handleDeleteMenuItem(event);
    });
    card.addEventListener("click", () => {
        window.location.href = `/menu-item?id=${menuItem.id}`;
    });
}


fillMenuItemsTable().catch(error => {
    console.error('Error fetching menu items:', error);
});

addButton.addEventListener("click", addNewMenuItem);
