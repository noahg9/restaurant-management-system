async function fillChefsTable() {
    const response = await fetch('/api/chefs/all', {
        headers: {
            "Accept": "application/json"
        }});
    if (response.status === 200) {
        const chefs = await response.json();
        chefs.forEach(chef => {
            addChefToTable(chef)
        })
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteChef)
}

async function handleDeleteChef(event) {
    const row = event.target.closest('.card');
    const chefId = row.dataset.chefId;
    const response = await fetch(`/api/chefs/${chefId}`, {
        method: "DELETE",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    if (response.status === 204) {
        row.remove();
    } else {
        const errorMessage = await response.text();
        alert(`Error ${response.status}: ${errorMessage}`);
    }
}

const firstNameInput = document.getElementById("firstNameInput");
const lastNameInput = document.getElementById("lastNameInput");
const addButton = document.getElementById("addButton");
const chefBody = document.getElementById("chefBody");

async function addNewChef() {
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
    const card = document.createElement("div");
    card.classList.add("card", "mb-3", "col-md-8"); // Adjusted width for two cards per row
    card.innerHTML = `
        <div class="card-body">
            <h5 class="card-title">${chef.firstName + ' ' + chef.lastName}</h5>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
        </div>
    `;
    card.dataset.chefId = chef.id;
    chefBody.appendChild(card);
    const newDeleteButton = card.querySelector('button');
    newDeleteButton.addEventListener("click", (event) => {
        event.stopPropagation(); // Prevent propagation to parent elements
        handleDeleteChef(event);
    });
    card.addEventListener("click", () => {
        window.location.href = `/chef?id=${chef.id}`;
    });
}

fillChefsTable().catch(error => {
    console.error('Error fetching chefs:', error);
});

addButton.addEventListener("click", addNewChef);
