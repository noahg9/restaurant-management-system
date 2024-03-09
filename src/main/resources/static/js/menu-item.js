const menuItemIdInput = document.getElementById("menuItemId");
const toggleChefsButton = document.getElementById("toggleChefInformation");
const associatedChefs = document.getElementById("associatedChefs");

async function toggleChefs() {
    if (associatedChefs.innerHTML !== '') {
        associatedChefs.innerHTML = '';
    } else {
        const response = await fetch(`/api/menu-items/${menuItemIdInput.value}/chefs`, {
            headers: {
                "Accept": "application/json"
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
const saveButton = document.getElementById("saveButton");

async function changeMenuItem() {
    const response = await fetch(`/api/menu-items/${menuItemIdInput.value}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        }), redirect: "manual"
    })
    if (response.status === 204) {
        saveButton.disabled = true;
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

saveButton.addEventListener("click", changeMenuItem);
nameInput.addEventListener("input", () => saveButton.disabled = false);
