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
    /*!*************************************!*\
  !*** ./src/main/js/search-chefs.js ***!
  \*************************************/
    __webpack_require__.r(__webpack_exports__)
    const searchTermInput = document.getElementById('searchTerm')

    searchTermInput?.addEventListener('keyup', showResults)

    async function showResults(e) {
        const searchResults = document.getElementById('searchResultsSection')
        const searchTerm = e.target.value.trim()
        if (!searchTerm) {
            searchResults.innerHTML = ''
            return
        }
        const response = await fetch(`/api/chefs/search?search=${searchTerm}`, {
            headers: {
                'Accept': 'application/json'
            }
        })
        if (response.status === 200) {
            const chefs = await response.json()
            let html = `<p>Found ${chefs.length} results</p>`
            chefs.forEach(chef => {
                html += `<a href="/chef?id=${chef.id}">${chef.firstName + ' ' + chef.lastName}</a><br>`
            })
            searchResults.innerHTML = html
        } else {
            searchResults.innerHTML = '<p>Found no results</p>'
        }
    }

/******/ })()

//# sourceMappingURL=bundle-search-chefs.js.map
