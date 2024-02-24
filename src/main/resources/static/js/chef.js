const chefIdInput = document.getElementById("chefId");
const toggleMenuItemsButton = document.getElementById("toggleMenuItemInformation");
const menuItemsTable = document.getElementById("menuItemInformation");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("menuItemInformationBody");

async function toggleMenuItemsTable() {
    if (menuItemsTable.style.display === "table") {
        hideMenuItemsTable();
    } else {
        const response = await fetch(`/api/chefs/${chefIdInput.value}/menuitems`);
        if (response.status === 200) {
            const menuItems = await response.json();
            tableBody.innerHTML = '';
            for (const menuItem of menuItems) {
                tableBody.innerHTML += `
                    <tr>
                        <td>${menuItem.name}</td>
                        <td>${menuItem.price}</td>
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
