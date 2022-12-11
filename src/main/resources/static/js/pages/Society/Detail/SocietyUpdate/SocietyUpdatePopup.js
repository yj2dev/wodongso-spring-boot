const societyToggle = document.getElementById("society-update-btn");
const societyUpdatePopup = document.getElementById('society-update-popup');
const societyUpdatePopupClose = document.getElementById('society-update-popup-close');

societyUpdatePopupClose.addEventListener('click', (event) => {
    location.reload();
    SocietyUpdatePopupBox();
})

societyToggle.addEventListener('click', (event) => {
    SocietyUpdatePopupBox();
});

const SocietyUpdatePopupBox = () => {
    societyUpdatePopup.classList.add("open");
}