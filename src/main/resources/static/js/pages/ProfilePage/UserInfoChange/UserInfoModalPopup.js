const userInfoBtn = document.getElementById("user-info-change");
const userInfoPopup = document.getElementById('user-info-popup');
const userInfoPopupClose = document.getElementById('user-info-popup-close');

userInfoPopupClose.addEventListener('click', (event) => {
    location.reload();
    userInfoPopupBox();
})

userInfoBtn.addEventListener('click', (event) => {
    userInfoPopupBox();
});


const userInfoPopupBox = () => {
    userInfoPopup.classList.toggle("open");
}