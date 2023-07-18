
function showSubCategories(mainCode) {
  console.log(mainCode);
  const mainCategory = document.getElementById("mainCategory_" + mainCode);
  const subCategory = document.getElementById("subCategory_" + mainCode);

  const filteredCategories = categories.filter(category => category.maincode === mainCode);

  subCategory.innerHTML = "";

  filteredCategories.forEach(category => {
    const listDiv = document.createElement("div");
    const listA = document.createElement("a");
    listA.textContent = category.subname;
    listA.href = "#";
    listA.classList.add("dropdown-item");
    listDiv.appendChild(listA);
    subCategory.appendChild(listDiv);
  });
}