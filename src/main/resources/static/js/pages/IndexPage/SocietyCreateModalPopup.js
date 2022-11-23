const societyToggle = document.getElementById("society-create-btn");
const popup = document.getElementById('popup-box');
const popupClose = document.getElementById('popupClose');

popupClose.addEventListener('click', (event) => {
    event.preventDefault();
    popup.classList.remove('open');
})

societyToggle.addEventListener('click', (event) => {
    popupBox();
});

const popupBox = () => {
    popup.classList.add("open");
}