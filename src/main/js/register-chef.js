import * as Joi from 'joi'
import {header, token} from './util/csrf.js'

const firstNameInput = document.getElementById('firstName')
const lastNameInput = document.getElementById('lastName')
const dateOfBirthInput = document.getElementById('dateOfBirth')
const usernameInput = document.getElementById('username')
const passwordInput = document.getElementById('password')
const roleNameInput = document.getElementById('roleName')
const registerButton = document.getElementById('registerButton')
const newChefForm = document.getElementById('newChefForm')

registerButton?.addEventListener('click', addButtonClicked)
newChefForm?.addEventListener('submit', trySubmitForm)

const inputMap = new Map()
inputMap.set('firstName', firstNameInput)
inputMap.set('lastName', lastNameInput)
inputMap.set('dateOfBirth', dateOfBirthInput)
inputMap.set('username', usernameInput)
inputMap.set('password', passwordInput)

async function trySubmitForm() {
    const chefSchema = Joi.object({
        firstName: Joi.string()
            .min(2)
            .max(30)
            .required(),
        lastName: Joi.string()
            .min(2)
            .max(30)
            .required(),
        dateOfBirth: Joi.date()
            .min('1-1-1900')
            .max('now')
            .required(),
        username: Joi.string()
            .min(2)
            .max(30)
            .required(),
        password: Joi.string()
            .min(2)
            .max(30)
            .required()
    })

    const chefObject = {
        firstName: firstNameInput.value,
        lastName: lastNameInput.value,
        dateOfBirth: dateOfBirthInput.value,
        username: usernameInput.value,
        password: passwordInput.value
    }

    const validationResult = chefSchema.validate(chefObject, { abortEarly: false })

    firstNameInput.setCustomValidity('')
    lastNameInput.setCustomValidity('')
    dateOfBirthInput.setCustomValidity('')
    usernameInput.setCustomValidity('')
    passwordInput.setCustomValidity('')

    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            const inputElement = inputMap.get(errorDetail.context.key)
            inputElement.setCustomValidity(errorDetail.message)
            inputElement.nextElementSibling.innerHTML = errorDetail.message
        }
    }

    // https://getbootstrap.com/docs/5.3/forms/validation/#how-it-works
    newChefForm.classList.add('was-validated')

    if (!validationResult.error) {
        await addNewChef(chefObject)
        newChefForm.reset()
        newChefForm.classList.remove('was-validated')
    }
}

async function addNewChef() {
    await fetch('/api/chefs', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            dateOfBirth: dateOfBirthInput.value,
            username: usernameInput.value,
            password: passwordInput.value,
            roleName: roleNameInput.value
        })
    })
}

function addButtonClicked(event) {
    event.preventDefault()
    trySubmitForm().then(r => {
        console.log('Form submitted:', r)
    })
}
