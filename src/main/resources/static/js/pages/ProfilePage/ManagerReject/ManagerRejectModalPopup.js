const managerRejectBtn = document.querySelectorAll(".manager-reject-btn");
const managerRejectPopup = document.getElementById('manager-reject-popup');
const managerRejectPopupClose = document.getElementById('manager-reject-close');

managerRejectPopupClose.addEventListener('click', (event) => {
    location.reload();
    managerRejectPopupBox();
})

managerRejectBtn.forEach(target => {
    target.addEventListener('click', () => {
        managerRejectPopupBox();
    });
});

const managerRejectPopupBox = () => {
    managerRejectPopup.classList.toggle("open");
}