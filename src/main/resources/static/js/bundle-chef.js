/******/ (() => { // webpackBootstrap
/******/ 	'use strict'
    /******/ 	// The require scope
    /******/ 	var __webpack_require__ = {};
    /******/
    /************************************************************************/
    /******/ 	/* webpack/runtime/make namespace object */
    /******/ 	(() => {
        /******/ 		// define __esModule on exports
        /******/ 		__webpack_require__.r = (exports) => {
            /******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
                /******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' })
                /******/ 			}
            /******/ 			Object.defineProperty(exports, '__esModule', { value: true })
            /******/ 		}
        /******/ 	})()
    /******/
    /************************************************************************/
    var __webpack_exports__ = {}
    /*!*****************************!*\
  !*** ./src/main/js/chef.js ***!
  \*****************************/
    __webpack_require__.r(__webpack_exports__)
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

/******/ })()

//# sourceMappingURL=bundle-chef.js.map
