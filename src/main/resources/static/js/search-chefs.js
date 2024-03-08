const searchTermInput = document.getElementById("searchTerm");
searchTermInput.addEventListener('keyup', showResults);

async function showResults(e) {
    const searchResults = document.getElementById("searchResultsSection");
    const searchTerm = e.target.value.trim();
    if (!searchTerm) {
        searchResults.innerHTML = '';
        return;
    }
    const response = await fetch(`/api/chefs?search=${searchTerm}`, {
        headers: {
            "Accept": "application/json"
        }
    });
    if (response.status === 200) {
        const chefs = await response.json();
        let html = `<p>Found ${chefs.length} results</p>`;
        html += '<ul>';
        chefs.forEach(chef => {
            html += `<li><a href="/chef?id=${chef.id}">${chef.firstName + ' ' + chef.lastName}</a></li>`;
        });
        html += '</ul>';
        searchResults.innerHTML = html;
    } else {
        searchResults.innerHTML = `<p>Found no results</p>`;
    }
}
