async function fillMenuItemsTable() {
    const response = await fetch('/api/menu-items/all', {
        headers: {
            "Accept": "application/json"
        }});
    if (response.status === 200) {
        const menuItems = await response.json();
        menuItems.forEach(menuItem => {
            addMenuItemToTable(menuItem)
        })
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
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
        method: "DELETE",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    if (response.status === 204) {
        row.remove()
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

const nameInput = document.getElementById("nameInput");
const priceInput = document.getElementById("priceInput");
const addButton = document.getElementById("addButton");
const menuItemBody = document.getElementById("menuItemBody");

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
    const card = document.createElement("div");
    card.classList.add("card", "mb-3", "col-md-8"); // Adjusted width for two cards per row
    const vegetarianIndicator = menuItem.vegetarian ? "(V)" : ""; // "(V)" for vegetarian, empty string for non-vegetarian
    card.innerHTML = `
        <div class="card-body">
            <h5 class="card-title">${menuItem.name} ${vegetarianIndicator}</h5>
            <p class="card-text">€${menuItem.price}</p>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
        </div>
    `;
    card.dataset.menuItemId = menuItem.id;
    menuItemBody.appendChild(card);
    const newDeleteButton = card.querySelector('button');
    newDeleteButton.addEventListener("click", (event) => {
        event.stopPropagation(); // Prevent propagation to parent elements
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

