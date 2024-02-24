const deleteButtons = document.querySelectorAll("button.btn-danger");

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteChef)
}

async function handleDeleteChef(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const chefId = rowId.substring(rowId.indexOf('_') + 1);
    const response = fetch(`/api/chefs/${chefId}`, {
        method: "DELETE"
    });
    if (response === 204) {
        const row = document.getElementById(chefId);
        row.parentNode.removeChild(row);
    }
}
