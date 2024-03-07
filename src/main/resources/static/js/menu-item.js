const menuItemIdInput = document.getElementById("menuItemId");
const toggleChefsButton = document.getElementById("toggleChefInformation");
const chefsTable = document.getElementById("chefInformation");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("chefInformationBody");

async function toggleChefsTable() {
    if (chefsTable.style.display === "table") {
        hideChefsTable();
    } else {
        const response = await fetch(`/api/menu-items/${menuItemIdInput.value}/chefs`,
            { headers: { "Accept": "application/json" } });
        if (response.status === 200) {
            const chefs = await response.json();
            tableBody.innerHTML = '';
            for (const chef of chefs) {
                tableBody.innerHTML += `
                    <tr>
                        <td>${chef.firstName + ' ' + chef.lastName}</td>
                    </tr>
                `;
            }
            showChefsTable();
        }
        /*
        fetch(`/api/menu-items/${menuItemIdInput.value}/chefs`)
                        { headers: { "Accept": "application/json" } })
            .then((response) => {
                if (response.status === 200) {
                    return response.json(); // Parse the body please!
                }
            })
            .then((chefs) => {
                for (const chef of chefs) {
                    console.log(chef)
                }
            })
         */
    }
}

function hideChefsTable() {
    chefsTable.style.display = "none";
    buttonWrapper.classList.remove("dropup");
    if (!buttonWrapper.classList.contains("dropdown")) {
        buttonWrapper.classList.add("dropdown");
    }
}

function showChefsTable() {
    chefsTable.style.display = "table";
    buttonWrapper.classList.remove("dropdown");
    if (!buttonWrapper.classList.contains("dropup")) {
        buttonWrapper.classList.add("dropup");
    }
}

toggleChefsButton.addEventListener("click", toggleChefsTable);



const nameInput = document.getElementById("nameInput");

/**
 * @type {HTMLButtonElement}
 */
const updateButton = document.getElementById("updateButton");

async function changeMenuItem() {
    const response = await fetch(`/api/menu-items/${menuItemIdInput.value}`, {
        method: "PATCH",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: nameInput.value
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

updateButton.addEventListener("click", changeMenuItem);
nameInput.addEventListener("input", () => updateButton.disabled = false);
