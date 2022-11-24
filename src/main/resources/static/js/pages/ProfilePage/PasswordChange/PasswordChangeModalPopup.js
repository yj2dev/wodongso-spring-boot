const passwordChangeBtn = document.getElementById("password-change");
const passwordChangePopup = document.getElementById('password-change-popup');
const passwordChangePopupClose = document.getElementById('password-change-popup-close');

passwordChangePopupClose.addEventListener('click', (event) => {
    location.reload();
    passwordChangePopupBox();
})

passwordChangeBtn.addEventListener('click', (event) => {
    passwordChangePopupBox();
});

const passwordChangePopupBox = () => {
    passwordChangePopup.classList.toggle("open");
}