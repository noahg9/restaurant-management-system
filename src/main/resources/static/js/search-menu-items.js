const searchTermInput = document.getElementById("searchTerm");
searchTermInput.addEventListener('keyup', showResults);

async function showResults(e) {
    const searchResults = document.getElementById("searchResultsSection");
    const searchTerm = e.target.value.trim();
    if (!searchTerm) {
        searchResults.innerHTML = '';
        return;
    }
    const response = await fetch(`/api/menu-items?search=${searchTerm}`, {
        headers: {
            "Accept": "application/json"
        }
    });
    if (response.status === 200) {
        const menuItems = await response.json();
        let html = `<p>Found ${menuItems.length} results</p>`;
        html += '<ul>';
        menuItems.forEach(menuItem => {
            html += `<li><a href="/menu-item?id=${menuItem.id}">${menuItem.name}</a></li>`;
        });
        html += '</ul>';
        searchResults.innerHTML = html;
    } else {
        searchResults.innerHTML = `<p>Found no results</p>`;
    }
}
