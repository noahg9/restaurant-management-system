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
/*!**********************************!*\
  !*** ./src/main/js/menu-item.js ***!
  \**********************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./util/csrf.js */ "./src/main/js/util/csrf.js");


const menuItemId = document.getElementById('menuItemId')
const toggleChefsButton = document.getElementById('toggleChefInformation')
const associatedChefs = document.getElementById('associatedChefs')
const name = document.getElementById('nameField')
const price = document.getElementById('priceField')
const vegetarian = document.getElementById('vegetarianField')
const spiceLevel = document.getElementById('spiceLevelField')
const saveButton = document.getElementById('saveButton')

toggleChefsButton?.addEventListener('click', toggleChefs)
saveButton?.addEventListener('click', saveMenuItem)
name?.addEventListener('input', () => saveButton.disabled = false)

async function toggleChefs() {
    if (associatedChefs.innerHTML !== '') {
        associatedChefs.innerHTML = ''
    } else {
        const response = await fetch(`/api/menu-items/${menuItemId.value}/chefs`, {
            headers: {
                'Accept': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
            }
        })
        if (response.status === 200) {
            const chefs = await response.json()
            chefs.forEach(chef => {
                const chefLink = document.createElement('a')
                chefLink.href = `/chef?id=${chef.id}`
                chefLink.classList.add('list-group-item', 'list-group-item-action')
                chefLink.textContent = chef.firstName + ' ' + chef.lastName
                associatedChefs.appendChild(chefLink)
            })
        }
    }
}

async function saveMenuItem() {
    const response = await fetch(`/api/menu-items/${menuItemId.value}`, {
        method: 'PATCH', headers: {
            'Content-Type': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }, body: JSON.stringify({
            name: name.value, price: price.value, vegetarian: vegetarian.checked, spiceLevel: spiceLevel.value
        }), redirect: 'manual'
    })
    if (response.status === 204) {
        saveButton.disabled = true
    }
}

})();

/******/ })()
;
//# sourceMappingURL=bundle-menu-item.js.map