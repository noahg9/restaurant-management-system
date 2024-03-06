function switchLanguage() {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/switch-language', true); // Change the URL based on your endpoint
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onload = function () {
        if (xhr.status === 200) {
            location.reload(); // Reload the page after switching language
        }
    };
    xhr.send();
}
