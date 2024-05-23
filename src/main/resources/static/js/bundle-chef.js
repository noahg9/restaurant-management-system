/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/js/util/csrf.js":
/*!**********************************!*\
  !*** ./src/main/js/util/csrf.js ***!
  \**********************************/
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   header: () => (/* binding */ header),
/* harmony export */   token: () => (/* binding */ token)
/* harmony export */ });
const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;


/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
/*!*****************************!*\
  !*** ./src/main/js/chef.js ***!
  \*****************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./util/csrf.js */ "./src/main/js/util/csrf.js");


const chefId = document.getElementById('chefId')
const toggleMenuItemsButton = document.getElementById('toggleMenuItemInformation')
const associatedMenuItems = document.getElementById('associatedMenuItems')
const firstName = document.getElementById('firstNameField')
const lastName = document.getElementById('lastNameField')
const dateOfBirth = document.getElementById('dateOfBirthField')
const username = document.getElementById('usernameField')
const saveButton = document.getElementById('saveButton')

toggleMenuItemsButton?.addEventListener('click', toggleMenuItems)
saveButton?.addEventListener('click', saveChef)
firstName?.addEventListener('input', () => saveButton.disabled = false)
lastName?.addEventListener('input', () => saveButton.disabled = false)

async function toggleMenuItems() {
    if (associatedMenuItems.innerHTML !== '') {
        associatedMenuItems.innerHTML = ''
    } else {
        const response = await fetch(`/api/chefs/${chefId.value}/menu-items`, {
            headers: {
                'Accept': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
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

async function saveChef() {
    const response = await fetch(`/api/chefs/${chefId.value}`, {
        method: 'PATCH', headers: {
            'Content-Type': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }, body: JSON.stringify({
            firstName: firstName.value,
            lastName: lastName.value,
            dateOfBirth: dateOfBirth.value,
            username: username.value
        }), redirect: 'manual'
    })
    if (response.status === 204) {
        saveButton.disabled = true
    }
}

})();

/******/ })()
;
//# sourceMappingURL=bundle-chef.js.map