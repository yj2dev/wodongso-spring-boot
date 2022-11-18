const profileToggle = document.getElementById('user-profile-toggle');
const notProfileField = document.querySelector('.content-body');

const toggleMenu = document.getElementById("profile-menu");

profileToggle.addEventListener('click', () => {
    showMenu()
})

window.addEventListener('click', (event)=>{
    event.target === notProfileField ? closeMenu() : false
})

function showMenu(){
    toggleMenu.classList.toggle('active');
}

function closeMenu(){
    toggleMenu.classList.remove('active');
}