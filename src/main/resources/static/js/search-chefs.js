const searchTermInput = document.getElementById("searchTerm");
searchTermInput.addEventListener('keyup', showResults);

async function showResults(e) {
    const searchResults = document.getElementById("searchResultsSection");
    const searchTerm = e.target.value.trim();
    if (!searchTerm) {
        searchResults.innerHTML = '';
        return;
    }
    const response = await fetch(`/api/chefs/search?search=${searchTerm}`, {
        headers: {
            "Accept": "application/json"
        }
    });
    if (response.status === 200) {
        const chefs = await response.json();
        let html = `<p>Found ${chefs.length} results</p>`;
        chefs.forEach(chef => {
            html += `<a href="/chef?id=${chef.id}">${chef.firstName + ' ' + chef.lastName}</a><br>`;
        });
        searchResults.innerHTML = html;
    } else {
        searchResults.innerHTML = `<p>Found no results</p>`;
    }
}
