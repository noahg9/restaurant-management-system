/******/ (() => { // webpackBootstrap
/******/ 	'use strict'
    /******/ 	var __webpack_modules__ = ({

        /***/ './src/main/js/util/csrf.js':
        /*!**********************************!*\
  !*** ./src/main/js/util/csrf.js ***!
  \**********************************/
        /***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

            __webpack_require__.r(__webpack_exports__)
            /* harmony export */ __webpack_require__.d(__webpack_exports__, {
                /* harmony export */   header: () => (/* binding */ header),
                /* harmony export */   token: () => (/* binding */ token)
                /* harmony export */ })
            const header = document.querySelector('meta[name="_csrf_header"]').content
            const token = document.querySelector('meta[name="_csrf"]').content


            /***/ })

        /******/ 	})
    /************************************************************************/
    /******/ 	// The module cache
    /******/ 	var __webpack_module_cache__ = {}
    /******/
    /******/ 	// The require function
    /******/ 	function __webpack_require__(moduleId) {
        /******/ 		// Check if module is in cache
        /******/ 		var cachedModule = __webpack_module_cache__[moduleId]
        /******/ 		if (cachedModule !== undefined) {
            /******/ 			return cachedModule.exports
            /******/ 		}
        /******/ 		// Create a new module (and put it into the cache)
        /******/ 		var module = __webpack_module_cache__[moduleId] = {
            /******/ 			// no module.id needed
            /******/ 			// no module.loaded needed
            /******/ 			exports: {}
            /******/ 		}
        /******/
        /******/ 		// Execute the module function
        /******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__)
        /******/
        /******/ 		// Return the exports of the module
        /******/ 		return module.exports
        /******/ 	}
    /******/
    /************************************************************************/
    /******/ 	/* webpack/runtime/define property getters */
    /******/ 	(() => {
        /******/ 		// define getter functions for harmony exports
        /******/ 		__webpack_require__.d = (exports, definition) => {
            /******/ 			for(var key in definition) {
                /******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
                    /******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] })
                    /******/ 				}
                /******/ 			}
            /******/ 		}
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
                /******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' })
                /******/ 			}
            /******/ 			Object.defineProperty(exports, '__esModule', { value: true })
            /******/ 		}
        /******/ 	})()
    /******/
    /************************************************************************/
    var __webpack_exports__ = {};
    // This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
    (() => {
        /*!******************************!*\
  !*** ./src/main/js/chefs.js ***!
  \******************************/
        __webpack_require__.r(__webpack_exports__)
        /* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./util/csrf.js */ './src/main/js/util/csrf.js')


        const chefBody = document.getElementById('chefBody')
        const deleteButtons = document.querySelectorAll('button.btn-danger')

        for (const deleteButton of deleteButtons) {
            deleteButton?.addEventListener('click', handleDeleteChef)
        }

        fillChefsTable().catch(error => {
            console.error('Error fetching chefs:', error)
        })

        async function fillChefsTable() {
            try {
                const response = await fetch('/api/chefs', {
                    headers: {
                        'Accept': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
                    }
                })
                if (response.status === 200) {
                    const chefs = await response.json()
                    chefs.forEach(chef => {
                        addChefToTable(chef)
                    })
                } else {
                    console.error('Failed to fetch chefs:', response.statusText)
                }
            } catch (error) {
                console.error('Error fetching chefs:', error)
            }
        }

        async function handleDeleteChef(event) {
            const row = event.target.closest('.card')
            const chefId = row.dataset.chefId
            const response = await fetch(`/api/chefs/${chefId}`, {
                method: 'DELETE', headers: {
                    [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
                }
            })
            if (response.status === 204) {
                row.remove()
            }
        }

        /**
 * @param {{id: string, firstName: string, lastName: string, dateOfBirth: string, username: string, password: string, role, string}} chef
 */
        function addChefToTable(chef) {
            const roleNames = {
                'HEAD_CHEF': 'Head Chef', 'SOUS_CHEF': 'Sous Chef', 'LINE_COOK': 'Line Cook'
            }

            const roleName = roleNames[chef.role]

            const roleGroup = document.getElementById(chef.role + '-group') // Check if group exists
            let cardGroup
            if (!roleGroup) {
                // Create a new group if it doesn't exist
                cardGroup = document.createElement('div')
                cardGroup.classList.add('role-group', 'mb-4', 'row', 'text-secondary') // Add Bootstrap classes for rows
                cardGroup.id = chef.role + '-group'

                const groupName = document.createElement('h2')
                groupName.textContent = roleName // Use role name as group header
                cardGroup.appendChild(groupName)

                chefBody.appendChild(cardGroup)
            } else {
                cardGroup = roleGroup
            }

            const cardColumn = document.createElement('div')
            cardColumn.classList.add('col-md-6')
            const card = document.createElement('div')
            card.classList.add('card', 'mb-3')
            const age = Math.floor((new Date() - new Date(chef.dateOfBirth)) / (1000 * 60 * 60 * 24 * 365))
            card.innerHTML = `
        <div class="card-body" onclick="location.href='/chef?id=${chef.id}';" style="cursor: pointer;">
            <h5 class="card-title">${chef.firstName + ' ' + chef.lastName}</h5>
            <p class="card-text">${age} years old</p>
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
        </div>
    `
            card.dataset.chefId = chef.id
            cardColumn.appendChild(card)
            cardGroup.appendChild(cardColumn)

            const newDeleteButton = card.querySelector('button')
            newDeleteButton?.addEventListener('click', (event) => {
                event.stopPropagation()
                handleDeleteChef(event).catch(error => {
                    console.error('Error deleting chef:', error)
                })
            })
        }

    })()

/******/ })()

//# sourceMappingURL=bundle-chefs.js.map
