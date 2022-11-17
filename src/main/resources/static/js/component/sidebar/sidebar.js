const sidebar = document.getElementById("sidebar");
const toggleBtn = document.getElementById("toggle");

toggleBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});