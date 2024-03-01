const chefIdInput = document.getElementById("chefId");
const toggleMenuItemsButton = document.getElementById("toggleMenuItemInformation");
const menuItemsTable = document.getElementById("menuItemInformation");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("menuItemInformationBody");

async function toggleMenuItemsTable() {
    if (menuItemsTable.style.display === "table") {
        hideMenuItemsTable();
    } else {
        const response = await fetch(`/api/chefs/${chefIdInput.value}/menuitems`,
            { headers: { Accept: "application/json" } });
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
        fetch(`/api/chefs/${chefIdInput.value}/menuitems`)
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

/*
function addMenuItemToTable(menuItems) {
    tableBody.innerHTML = '';
    for (const menuItem of menuItems) {
        tableBody.innerHTML += `<tr>
                                    <td>${menuItem.name}</td>
                                    <td>${menuItem.price}</td>
                                </tr>`
    }

    showMenuItemsTable();
}
 */

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



const dateInput = document.getElementById("dateInput");
/**
 * @type {HTMLButtonElement}
 */
const updateButton = document.getElementById("updateButton");

async function changeChef() {
    const response = await fetch(`/api/chefs/${chefIdInput.value}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            description: dateInput.value
        })
    })
    if (response.status === 204) {
        updateButton.disabled = true;
    } else {
        alert('Something went wrong!'); // Don't use alerts in a "real" application.
    }}

updateButton.addEventListener("click", changeChef);
dateInput.addEventListener("input", () => updateButton.disabled = false);
