const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content')
const token = document.querySelector('meta[name="_csrf"]').getAttribute('content')
const languageSwitch = document.getElementById('languageSwitch')

languageSwitch?.addEventListener('click', switchLanguage)

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
