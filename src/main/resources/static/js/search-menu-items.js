const searchButton = document.getElementById("searchButton");
const searchResultsSection = document.getElementById("searchResultsSection");
const searchTermInput = document.getElementById("searchTerm");
const tableBody = document.getElementsByTagName("tbody")[0];

searchButton.addEventListener("click", async () => {
    const response = await fetch(`/api/menuitems?search=${searchTermInput.value}`);
    if (response.status === 200) {
        const menuItems = await response.json();
        for (const menuItem of menuItems) {
            tableBody.innerHTML += `
            <tr>
                <td>${menuItem.name}</td>
                <td>${menuItem.price}</td>
                <td>${menuItem.course}</td>
                <td>${menuItem.vegetarian}</td>
                <td>${menuItem.spiceLvl}</td>
                <td><a href="/menu/menu-item?id=${menuItem.id}">Details</a></td>
            </tr>
            `
        }
        searchResultsSection.style.display = "block";
    } else {
        searchResultsSection.style.display = "none";
    }
})
