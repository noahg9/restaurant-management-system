import {header, token} from "./util/csrf.js";

const menuItemIdInput = document.getElementById("menuItemId");
const toggleChefsButton = document.getElementById("toggleChefInformation");
const associatedChefs = document.getElementById("associatedChefs");

async function toggleChefs() {
    if (associatedChefs.innerHTML !== '') {
        associatedChefs.innerHTML = '';
    } else {
        const response = await fetch(`/api/menu-items/${menuItemIdInput.value}/chefs`, {
            headers: {
                "Accept": "application/json",
                [header]: token
            }
        });
        if (response.status === 200) {
            const chefs = await response.json();
            chefs.forEach(chef => {
                const chefLink = document.createElement("a");
                chefLink.href = `/chef?id=${chef.id}`;
                chefLink.classList.add("list-group-item", "list-group-item-action");
                chefLink.textContent = chef.firstName + " " + chef.lastName;
                associatedChefs.appendChild(chefLink);
            });
        }
    }
}

toggleChefsButton.addEventListener("click", toggleChefs);

const nameInput = document.getElementById("nameInputField");
const priceInput = document.getElementById("priceInputField");
const courseInput = document.getElementById("courseInputField");
const vegetarianInput = document.getElementById("vegetarianInputField");
const spiceLvlInput = document.getElementById("spiceLvlInputField");
const saveButton = document.getElementById("saveButton");

async function saveMenuItem() {
    const response = await fetch(`/api/menu-items/${menuItemIdInput.value}`, {
        method: "PATCH", headers: {
            "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: nameInput.value,
            price: priceInput.value,
            course: courseInput.value,
            vegetarian: vegetarianInput.checked,
            spiceLvl: spiceLvlInput.value
        }), redirect: "manual"
    })
    if (response.status === 204) {
        saveButton.disabled = true;
    }
}

saveButton.addEventListener("click", saveMenuItem);
nameInput.addEventListener("input", () => saveButton.disabled = false);
