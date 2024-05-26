import anime from 'animejs'
import * as Joi from 'joi'
import { Notyf } from 'notyf'
import 'notyf/notyf.min.css'
import {header, token} from './util/csrf.js'

const menuItemBody = document.getElementById('menuItemBody')
const nameInput = document.getElementById('name')
const priceInput = document.getElementById('price')
const courseNameSelect = document.getElementById('courseName')
const vegetarianCheckbox = document.getElementById('vegetarian')
const spiceLevelInput = document.getElementById('spiceLevel')
const chefsSelect = document.getElementById('chefs')
const addButton = document.getElementById('addButton')
const deleteButtons = document.querySelectorAll('button.btn-danger')
const newMenuItemForm = document.getElementById('newMenuItemForm')

const notyf = new Notyf({
    duration: 3000,
    position: {
        x: 'right',
        y: 'top'
    }
})

addButton?.addEventListener('click', addButtonClicked)
newMenuItemForm?.addEventListener('submit', trySubmitForm)

for (const deleteButton of deleteButtons) {
    deleteButton?.addEventListener('click', handleDeleteMenuItem)
}

fillMenuItemsTable().catch(error => {
    console.error('Error fetching menu items:', error)
})

fetchChefs().catch(error => {
    console.error('Error fetching chefs:', error)
})

const inputMap = new Map()
inputMap.set('name', nameInput)
inputMap.set('price', priceInput)
inputMap.set('spiceLevel', spiceLevelInput)

async function trySubmitForm() {
    const menuItemSchema = Joi.object({
        name: Joi.string()
            .min(2)
            .max(30)
            .required(), price: Joi.number()
            .precision(2)
            .required(), spiceLevel: Joi.number()
            .integer()
            .min(0)
            .max(5)
            .required()
    })

    const menuItemObject = {
        name: nameInput.value, price: priceInput.value, spiceLevel: spiceLevelInput.value
    }

    const validationResult = menuItemSchema.validate(menuItemObject, {abortEarly: false})

    nameInput.setCustomValidity('')
    priceInput.setCustomValidity('')
    spiceLevelInput.setCustomValidity('')

    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            const inputElement = inputMap.get(errorDetail.context.key)
            inputElement.setCustomValidity(errorDetail.message)
            inputElement.nextElementSibling.innerHTML = errorDetail.message
        }
    }

    // https://getbootstrap.com/docs/5.3/forms/validation/#how-it-works
    newMenuItemForm.classList.add('was-validated')

    if (!validationResult.error) {
        await addNewMenuItem(menuItemObject)
        newMenuItemForm.reset()
        newMenuItemForm.classList.remove('was-validated')
    }
}

async function fillMenuItemsTable() {
    try {
        const response = await fetch('/api/menu-items', {
            headers: {
                'Accept': 'application/json', [header]: token
            }
        })
        if (response.ok) {
            const menuItems = await response.json()
            menuItems.forEach(menuItem => {
                addMenuItemToTable(menuItem)
            })
        } else {
            console.error('Failed to fetch menu items:', response.statusText)
        }
    } catch (error) {
        console.error('Error fetching menu items:', error)
    }
}

async function handleDeleteMenuItem(event) {
    const card = event.target.closest('.card')
    const menuItemId = card.dataset.menuItemId
    const response = await fetch(`/api/menu-items/${menuItemId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        anime({
            targets: card, opacity: 0.0, easing: 'linear', duration: 600, complete: function () {
                card.remove()
            }
        })
    }
}

async function fetchChefs() {
    try {
        const response = await fetch('/api/chefs', {
            headers: {
                'Accept': 'application/json', [header]: token
            }
        })
        if (response.ok) {
            const chefs = await response.json()
            populateChefsSelect(chefs)
        } else {
            console.error('Failed to fetch chefs:', response.statusText)
        }
    } catch (error) {
        console.error('Error fetching chefs:', error)
    }
}

async function addNewMenuItem() {
    const selectedChefs = Array.from(chefsSelect.selectedOptions).map(option => option.value)
    try {
        const response = await fetch('/api/menu-items', {
            method: 'POST', headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
            }, body: JSON.stringify({
                name: nameInput.value,
                price: priceInput.value,
                courseName: courseNameSelect.value,
                vegetarian: vegetarianCheckbox.checked,
                spiceLevel: spiceLevelInput.value,
                chefs: selectedChefs
            })
        })
        if (response.ok) {
            const menuItem = await response.json()
            addMenuItemToTable(menuItem)
            notyf.success(menuItem.name + ' added successfully')
        } else {
            console.error('Failed to add menu item:', response.statusText)
        }
    } catch (error) {
        console.error('Error adding menu item:', error)
    }
}

function populateChefsSelect(chefs) {
    chefsSelect.innerHTML = ''
    chefs.forEach(chef => {
        const option = document.createElement('option')
        option.value = chef.id
        option.textContent = chef.firstName + ' ' + chef.lastName
        chefsSelect.appendChild(option)
    })
}

/**
 * @param {{id: string, name: string, price: number, course: string, vegetarian: boolean, spiceLevel: number}} menuItem
 */
function addMenuItemToTable(menuItem) {
    const courseNames = {
        'STARTER': 'Starter',
        'MAIN': 'Main',
        'APPETIZER': 'Appetizer',
        'DESSERT': 'Dessert',
        'BEVERAGE': 'Beverage',
        'OTHER': 'Other'
    }

    const courseName = courseNames[menuItem.course]

    const courseGroup = document.getElementById(menuItem.course + '-group') // Check if group exists
    let cardGroup
    if (!courseGroup) {
        // Create a new group if it doesn't exist
        cardGroup = document.createElement('div')
        cardGroup.classList.add('course-group', 'mb-4', 'row', 'text-secondary') // Add Bootstrap classes for rows
        cardGroup.id = menuItem.course + '-group'

        const groupName = document.createElement('h2')
        groupName.textContent = courseName // Use course name as group header
        cardGroup.appendChild(groupName)

        menuItemBody.appendChild(cardGroup)
    } else {
        cardGroup = courseGroup
    }

    const cardColumn = document.createElement('div')
    cardColumn.classList.add('col-md-6')
    const card = document.createElement('div')
    card.classList.add('card', 'mb-3')
    const vegetarianIndicator = menuItem.vegetarian ? '(V)' : '' // "(V)" for vegetarian, empty string for non-vegetarian
    card.innerHTML = `
        <div class="card-body" style="cursor: pointer;">
            <h5 class="card-title">${menuItem.name} ${vegetarianIndicator}</h5>
            <p class="card-text">€${menuItem.price}</p>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="bi bi-trash3"></i></button>
        </div>
    `
    card.dataset.menuItemId = menuItem.id
    cardColumn.appendChild(card)
    cardGroup.appendChild(cardColumn)

    const newDeleteButton = card.querySelector('button')
    newDeleteButton?.addEventListener('click', (event) => {
        event.stopPropagation()
        handleDeleteMenuItem(event).catch(error => {
            console.error('Error deleting menu item:', error)
        })
    })
    card?.addEventListener('click', () => {
        window.location.href = `/menu-item?id=${menuItem.id}`
    })
}

function addButtonClicked(event) {
    event.preventDefault()
    trySubmitForm().then(r => {
        console.log('Form submitted:', r)
    })
}
