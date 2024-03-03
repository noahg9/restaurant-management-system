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
                <td>${chef.firstName}</td>
                <td>${chef.lastName}</td>
                <td><a href="/chef?id=${chef.id}">Details</a></td>
            </tr>
            `;
        }
        searchResultsSection.style.display = "block";
    } else {
        searchResultsSection.style.display = "none";
    }
})
