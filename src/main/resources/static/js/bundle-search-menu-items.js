/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/scss/search.scss":
/*!***********************************!*\
  !*** ./src/main/scss/search.scss ***!
  \***********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
// extracted by mini-css-extract-plugin


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
/*!******************************************!*\
  !*** ./src/main/js/search-menu-items.js ***!
  \******************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _scss_search_scss__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../scss/search.scss */ "./src/main/scss/search.scss");


const searchTermInput = document.getElementById('searchTerm')

searchTermInput?.addEventListener('keyup', showResults)

async function showResults(e) {
    const searchResults = document.getElementById('searchResultsSection')
    const searchTerm = e.target.value.trim()
    if (!searchTerm) {
        searchResults.innerHTML = ''
        return
    }
    const response = await fetch(`/api/menu-items/search?search=${searchTerm}`, {
        headers: {
            'Accept': 'application/json'
        }
    })
    if (response.status === 200) {
        const menuItems = await response.json()
        let html = `<p>Found ${menuItems.length} results</p>`
        menuItems.forEach(menuItem => {
            html += `<a href="/menu-item?id=${menuItem.id}">${menuItem.name}</a><br>`
        })
        searchResults.innerHTML = html
    } else {
        searchResults.innerHTML = '<p>Found no results</p>'
    }
}

})();

/******/ })()
;
//# sourceMappingURL=bundle-search-menu-items.js.map