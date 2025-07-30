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

    const motorcycleId = deleteBtn.dataset.motorcycles;

    const deleteForm = document.querySelector(".deleteForm")


    if (engineId) {
      deleteForm.action = `/engines/delete/${engineId}`;
      confirmMsg.textContent = `Are you sure you want to delete ${engineName}?`;
    } else if (categoryId) {
      deleteForm.action = `/categories/delete/${categoryId}`;
      confirmMsg.textContent = `Are you sure you want to delete this category?`;
    } else if (motorcycleId) {
      deleteForm.action = `/motos/delete/${motorcycleId}`;
      confirmMsg.textContent = `Are you sure you want to delete this motorcycle?`;
    }


    deleteModal.classList.remove("d-none")



  })
})


closeBtn.addEventListener("click", () => {

  deleteModal.classList.add("d-none")

})
