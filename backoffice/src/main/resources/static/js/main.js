const deleteBtns = document.querySelectorAll(".deleteBtn")
const deleteModal = document.querySelector(".deleteModal")
const closeBtn = document.getElementById("closeBtn")
const confirmMsg = document.getElementById("confirmMsg")



deleteBtns.forEach(deleteBtn => {
  deleteBtn.addEventListener("click", () => {


    const engineId = deleteBtn.dataset.engineid;
    const enginetype = deleteBtn.dataset.enginetype;
    const enginecc = deleteBtn.dataset.enginecc;

    const engineName = enginetype + ' ' + enginecc;

    const categoryId = deleteBtn.dataset.categoryid;
    const categoryName = deleteBtn.dataset.categoryname;

    const motorcycleId = deleteBtn.dataset.motoid;
    const motorcycleName = deleteBtn.dataset.motoname;

    const variantId = deleteBtn.dataset.variantid;
    const variantName = deleteBtn.dataset.variantname;

    const colorVariantId = deleteBtn.dataset.colorvariantid;
    const colorVariantName = deleteBtn.dataset.colorvariantname;

    const deleteForm = document.querySelector(".deleteForm")


    if (engineId) {
      deleteForm.action = `/engines/delete/${engineId}`;
      confirmMsg.textContent = `Are you sure you want to delete ${engineName}?`;
    } else if (categoryId) {
      deleteForm.action = `/categories/delete/${categoryId}`;
      confirmMsg.textContent = `Are you sure you want to delete the category: ${categoryName}?`;
    } else if (motorcycleId) {
      deleteForm.action = `/motos/delete/${motorcycleId}`;
      confirmMsg.textContent = `Are you sure you want to delete ${motorcycleName}?`;
    } else if (variantId) {
      deleteForm.action = `/variants/delete/${variantId}`;
      confirmMsg.textContent = `Are you sure you want to delete ${variantName}?`;
    } else if (colorVariantId) {
      deleteForm.action = `/color-variants/delete/${colorVariantId}`;
      confirmMsg.textContent = `Are you sure you want to delete ${colorVariantName}?`;
    }


    deleteModal.classList.remove("d-none")



  })
})


closeBtn.addEventListener("click", () => {

  deleteModal.classList.add("d-none")

})
