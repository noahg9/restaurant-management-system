import {header, token} from './util/csrf.js'

const chefId = document.getElementById('chefId')
const toggleMenuItemsButton = document.getElementById('toggleMenuItemInformation')
const associatedMenuItems = document.getElementById('associatedMenuItems')
const firstNameInput = document.getElementById('firstNameField')
const lastNameInput = document.getElementById('lastNameField')
const dateOfBirthInput = document.getElementById('dateOfBirthField')
const usernameInput = document.getElementById('usernameField')
const saveButton = document.getElementById('saveButton')
const oldFirstName = firstNameInput?.value
const oldLastName = lastNameInput?.value
const oldDateOfBirth = dateOfBirthInput?.value
const oldUsername = usernameInput?.value

toggleMenuItemsButton?.addEventListener('click', toggleMenuItems)
saveButton?.addEventListener('click', saveChef)
firstNameInput?.addEventListener('input', saveChef)
lastNameInput?.addEventListener('input', saveChef)
dateOfBirthInput?.addEventListener('input', saveChef)
usernameInput?.addEventListener('input', saveChef)

async function toggleMenuItems() {
    if (associatedMenuItems.innerHTML !== '') {
        associatedMenuItems.innerHTML = ''
    } else {
        const response = await fetch(`/api/chefs/${chefId.value}/menu-items`, {
            headers: {
                'Accept': 'application/json', [header]: token
            }
        })
        if (response.status === 200) {
            const menuItems = await response.json()
            menuItems.forEach(menuItem => {
                const menuItemLink = document.createElement('a')
                menuItemLink.href = `/menu-item?id=${menuItem.id}`
                menuItemLink.classList.add('list-group-item', 'list-group-item-action')
                menuItemLink.textContent = menuItem.name
                associatedMenuItems.appendChild(menuItemLink)
            })
        }
    }
}

function saveChef() {
    saveButton.disabled = (firstNameInput.value === oldFirstName && lastNameInput.value === oldLastName &&
        dateOfBirthInput.value === oldDateOfBirth && usernameInput.value === oldUsername)
}
