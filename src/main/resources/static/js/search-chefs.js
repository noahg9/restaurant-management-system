const searchButton = document.getElementById("searchButton");
const searchResultsSection = document.getElementById("searchResultsSection");
const searchTermInput = document.getElementById("searchTerm");
const tableBody = document.getElementsByTagName("tbody")[0];

searchButton.addEventListener("click", async () => {
    const response = await fetch(`/api/chefs?search=${searchTermInput.value}`);
    if (response.status === 200) {
        const chefs = await response.json();
        tableBody.innerHTML = '';
        for (const chef of chefs) {
            tableBody.innerHTML += `
            <tr>
                <td><a href="/chef?id=${chef.id}">${chef.firstName + ' ' + chef.lastName}</a></td>
            </tr>
            `;
        }
        searchResultsSection.style.display = "block";
    } else {
        searchResultsSection.style.display = "none";
    }
})
