// 선택된 대분류에 따라 소분류 옵션 보이기
function updateSubCategories() {
  const mainCategorySelect = document.getElementById("main_cate");
  const subCategorySelect = document.getElementById("sub_cate");

  subCategorySelect.innerHTML = '<option value="">선택하세요</option>';

  const selectedMainCategory = mainCategorySelect.value;

  const filteredCategories = categories.filter(category => category.maincode === selectedMainCategory);

  filteredCategories.forEach(category => {
    const option = document.createElement("option");
    option.value = category.subcode;
    option.textContent = category.subname;
    subCategorySelect.appendChild(option);
  });
}