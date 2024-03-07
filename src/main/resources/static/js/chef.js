const chefIdInput = document.getElementById("chefId");
const toggleMenuItemsButton = document.getElementById("toggleMenuItemInformation");
const menuItemsTable = document.getElementById("menuItemInformation");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("menuItemInformationBody");

async function toggleMenuItemsTable() {
    if (menuItemsTable.style.display === "table") {
        hideMenuItemsTable();
    } else {
        const response = await fetch(`/api/chefs/${chefIdInput.value}/menu-items`,
            { headers: { "Accept": "application/json" } });
        if (response.status === 200) {
            const menuItems = await response.json();
            tableBody.innerHTML = '';
            for (const menuItem of menuItems) {
                tableBody.innerHTML += `
                    <tr>
                        <td>${menuItem.name}</td>
                        <td>${menuItem.price}</td>
                        <td>${menuItem.course}</td>
                        <td>${menuItem.vegetarian}</td>
                        <td>${menuItem.spiceLvl}</td>
                    </tr>
                `;
            }
            showMenuItemsTable();
        }
        /*
        fetch(`/api/chefs/${chefIdInput.value}/menu-items`)
                        { headers: { "Accept": "application/json" } })
            .then((response) => {
                if (response.status === 200) {
                    return response.json(); // Parse the body please!
                }
            })
            .then((menuItems) => {
                for (const menuItem of menuItems) {
                    console.log(menuItem)
                }
            })
         */
    }
}

function hideMenuItemsTable() {
    menuItemsTable.style.display = "none";
    buttonWrapper.classList.remove("dropup");
    if (!buttonWrapper.classList.contains("dropdown")) {
        buttonWrapper.classList.add("dropdown");
    }
}

function showMenuItemsTable() {
    menuItemsTable.style.display = "table";
    buttonWrapper.classList.remove("dropdown");
    if (!buttonWrapper.classList.contains("dropup")) {
        buttonWrapper.classList.add("dropup");
    }
}

toggleMenuItemsButton.addEventListener("click", toggleMenuItemsTable);

const firstNameInput = document.getElementById("firstNameInput");
const lastNameInput = document.getElementById("lastNameInput");

/**
 * @type {HTMLButtonElement}
 */
const updateButton = document.getElementById("updateButton");

async function changeChef() {
    const response = await fetch(`/api/chefs/${chefIdInput.value}`, {
        method: "PATCH",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value
        }),
        redirect: "manual"
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
