import {header, token} from "./util/csrf.js";

async function fillChefsTable() {
    const response = await fetch('/api/chefs', {
        headers: {
            "Accept": "application/json", [header]: token
        }
    });
    if (response.status === 200) {
        const chefs = await response.json();
        chefs.forEach(chef => {
            addChefToTable(chef)
        })
    }
}

const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton?.addEventListener("click", handleDeleteChef)
}

async function handleDeleteChef(event) {
    const row = event.target.closest('.card');
    const chefId = row.dataset.chefId;
    const response = await fetch(`/api/chefs/${chefId}`, {
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.status === 204) {
        row.remove();
    }
}

const chefBody = document.getElementById("chefBody");

/**
 * @param {{id: number, firstName: string, lastName: string, dateOfBirth: date, username: string, password: string, role, string}} chef
 */
function addChefToTable(chef) {
    const roleNames = {
        "HEAD_CHEF": "Head Chef",
        "SOUS_CHEF": "Sous Chef",
        "LINE_COOK": "Line Cook"
    };

    const roleName = roleNames[chef.role];

    const roleGroup = document.getElementById(chef.role + "-group"); // Check if group exists
    let cardGroup;
    if (!roleGroup) {
        // Create a new group if it doesn't exist
        cardGroup = document.createElement("div");
        cardGroup.classList.add("role-group", "mb-4", "row", "text-secondary"); // Add Bootstrap classes for rows
        cardGroup.id = chef.role + "-group";

        const groupName = document.createElement("h2");
        groupName.textContent = roleName; // Use role name as group header
        cardGroup.appendChild(groupName);

        chefBody.appendChild(cardGroup);
    } else {
        cardGroup = roleGroup;
    }

    const cardColumn = document.createElement("div");
    cardColumn.classList.add("col-md-6"); // Bootstrap class for columns
    const card = document.createElement("div");
    card.classList.add("card", "mb-3");
    const age = Math.floor((new Date() - new Date(chef.dateOfBirth)) / (1000 * 60 * 60 * 24 * 365));
    card.innerHTML = `
        <div class="card-body" onclick="location.href='/chef?id=${chef.id}';" style="cursor: pointer;">
            <h5 class="card-title">${chef.firstName + ' ' + chef.lastName}</h5>
            <p class="card-text">${age} years old</p>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
        </div>
    `;
    card.dataset.chefId = chef.id;
    cardColumn.appendChild(card);
    cardGroup.appendChild(cardColumn);

    const newDeleteButton = card.querySelector('button');
    newDeleteButton?.addEventListener("click", (event) => {
        event.stopPropagation();
        handleDeleteChef(event);
    });
}

fillChefsTable().catch(error => {
    console.error('Error fetching chefs:', error);
});
