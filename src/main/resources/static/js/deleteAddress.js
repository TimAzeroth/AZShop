
function deleteAddressContainer(btn) {
    const addressContainer = btn.parentElement;
    addressContainer.remove();

    addressCount--;
    num--;

    const addAddressBtn = document.getElementById("addAddressBtn");
    if (addressCount < 3) {
        addAddressBtn.style.display = "block";
    }
}