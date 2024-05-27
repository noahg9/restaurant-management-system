const firstNameInput = document.getElementById('firstNameField')
const lastNameInput = document.getElementById('lastNameField')
const dateOfBirthInput = document.getElementById('dateOfBirthField')
const usernameInput = document.getElementById('usernameField')
const saveButton = document.getElementById('saveButton')
const oldFirstName = firstNameInput?.value
const oldLastName = lastNameInput?.value
const oldDateOfBirth = dateOfBirthInput?.value
const oldUsername = usernameInput?.value

saveButton?.addEventListener('click', saveChef)
firstNameInput?.addEventListener('input', saveChef)
lastNameInput?.addEventListener('input', saveChef)
dateOfBirthInput?.addEventListener('input', saveChef)
usernameInput?.addEventListener('input', saveChef)

function saveChef() {
    saveButton.disabled = (firstNameInput.value === oldFirstName && lastNameInput.value === oldLastName &&
        dateOfBirthInput.value === oldDateOfBirth && usernameInput.value === oldUsername)
}
