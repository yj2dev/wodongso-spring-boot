const rejectBtn = document.querySelectorAll(".reject-btn");
const rejectPopup = document.getElementById('reject-popup');
const rejectPopupClose = document.getElementById('reject-popup-close');

rejectPopupClose.addEventListener('click', (event) => {
    location.reload();
    rejectPopupBox();
})

rejectBtn.forEach((target) => {
    target.addEventListener('click', () => {
        rejectPopupBox();
    })
})

const rejectPopupBox = () => {
    rejectPopup.classList.toggle("open");
}