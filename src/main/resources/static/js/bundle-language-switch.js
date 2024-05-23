/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	// The require scope
/******/ 	var __webpack_require__ = {};
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
/*!****************************************!*\
  !*** ./src/main/js/language-switch.js ***!
  \****************************************/
__webpack_require__.r(__webpack_exports__);
const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content')
const token = document.querySelector('meta[name="_csrf"]').getAttribute('content')

function switchLanguage() {
    const xhr = new XMLHttpRequest()
    xhr.open('POST', '/switch-language', true) // Change the URL based on your endpoint
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8')
    xhr.setRequestHeader(header, token) // Include CSRF token in request headers
    xhr.onload = function () {
        if (xhr.status === 200) {
            location.reload() // Reload the page after switching language
        }
    }
    xhr.send()
}

/******/ })()
;
//# sourceMappingURL=bundle-language-switch.js.map