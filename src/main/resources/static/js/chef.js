const chefIdInput = document.getElementById("chefId");
const toggleMenuItemsButton = document.getElementById("toggleMenuItemInformation");
const associatedMenuItems = document.getElementById("associatedMenuItems");

async function toggleMenuItems() {
    if (associatedMenuItems.innerHTML !== '') {
        associatedMenuItems.innerHTML = '';
    } else {
        const response = await fetch(`/api/chefs/${chefIdInput.value}/menu-items`, {
            headers: {
                "Accept": "application/json"
            }
        });
        if (response.status === 200) {
            const menuItems = await response.json();
            menuItems.forEach(menuItem => {
                const menuItemLink = document.createElement("a");
                menuItemLink.href = `/menu-item?id=${menuItem.id}`;
                menuItemLink.classList.add("list-group-item", "list-group-item-action");
                menuItemLink.textContent = menuItem.name;
                associatedMenuItems.appendChild(menuItemLink);
            });
        }
    }
}

toggleMenuItemsButton.addEventListener("click", toggleMenuItems);

const firstNameInput = document.getElementById("firstNameInput");
const lastNameInput = document.getElementById("lastNameInput");
const updateButton = document.getElementById("updateButton");

async function changeChef() {
    const response = await fetch(`/api/chefs/${chefIdInput.value}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            firstName: firstNameInput.value, lastName: lastNameInput.value
        }), redirect: "manual"
    })
    if (response.status === 204) {
        updateButton.disabled = true;
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

updateButton.addEventListener("click", changeChef);
firstNameInput.addEventListener("input", () => updateButton.disabled = false);
lastNameInput.addEventListener("input", () => updateButton.disabled = false);
