import {header, token} from "./util/csrf.js";

const chefId = document.getElementById("chefId");
const toggleMenuItemsButton = document.getElementById("toggleMenuItemInformation");
const associatedMenuItems = document.getElementById("associatedMenuItems");

async function toggleMenuItems() {
    if (associatedMenuItems.innerHTML !== '') {
        associatedMenuItems.innerHTML = '';
    } else {
        const response = await fetch(`/api/chefs/${chefId.value}/menu-items`, {
            headers: {
                "Accept": "application/json",
                [header]: token
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

const firstName = document.getElementById("firstNameField");
const lastName = document.getElementById("lastNameField");
const dob = document.getElementById("dobField");
const username = document.getElementById("usernameField");
const saveButton = document.getElementById("saveButton");

async function saveChef() {
    const response = await fetch(`/api/chefs/${chefId.value}`, {
        method: "PATCH", headers: {
            "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            firstName: firstName.value, lastName: lastName.value, dateOfBirth: dob.value, username: username.value, role: 'Chef'
        }), redirect: "manual"
    })
    if (response.status === 204) {
        saveButton.disabled = true;
    }
}

saveButton.addEventListener("click", saveChef);
firstName.addEventListener("", () => saveButton.disabled = false);
lastName.addEventListener("", () => saveButton.disabled = false);