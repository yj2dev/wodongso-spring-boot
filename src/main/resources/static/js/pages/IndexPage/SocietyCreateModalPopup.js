const societyToggle = document.getElementById("society-create-btn");
const popup = document.getElementById('popup-box');

function popupClose(event) {
    event.preventDefault();
    popup.classList.remove('open')
}

societyToggle.addEventListener('click', (event) => {
    popupBox();
});

const popupBox = () => {
    popup.classList.add("open");
}