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
        /*!***********************************!*\
  !*** ./src/main/js/menu-items.js ***!
  \***********************************/
        __webpack_require__.r(__webpack_exports__)
        /* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./util/csrf.js */ './src/main/js/util/csrf.js')


        const menuItemBody = document.getElementById('menuItemBody')
        const nameInput = document.getElementById('name')
        const priceInput = document.getElementById('price')
        const courseNameSelect = document.getElementById('courseName')
        const vegetarianCheckbox = document.getElementById('vegetarian')
        const spiceLevelInput = document.getElementById('spiceLevel')
        const chefsSelect = document.getElementById('chefs')
        const addButton = document.getElementById('addButton')
        const deleteButtons = document.querySelectorAll('button.btn-danger')

        addButton?.addEventListener('click', addNewMenuItem)

        for (const deleteButton of deleteButtons) {
            deleteButton?.addEventListener('click', handleDeleteMenuItem)
        }

        fillMenuItemsTable().catch(error => {
            console.error('Error fetching menu items:', error)
        })

        fetchChefs().catch(error => {
            console.error('Error fetching chefs:', error)
        })

        async function fillMenuItemsTable() {
            try {
                const response = await fetch('/api/menu-items', {
                    headers: {
                        'Accept': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
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
            const row = event.target.closest('.card')
            const menuItemId = row.dataset.menuItemId
            const response = await fetch(`/api/menu-items/${menuItemId}`, {
                method: 'DELETE', headers: {
                    [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
                }
            })
            if (response.status === 204) {
                row.remove()
            }
        }

        async function fetchChefs() {
            try {
                const response = await fetch('/api/chefs', {
                    headers: {
                        'Accept': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
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
                        'Accept': 'application/json', 'Content-Type': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
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
            <button type="button" class="btn btn-danger btn-sm delete-button"><i class="fas fa-trash-alt"></i></button>
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

    })()

/******/ })()

//# sourceMappingURL=bundle-menu-items.js.map
