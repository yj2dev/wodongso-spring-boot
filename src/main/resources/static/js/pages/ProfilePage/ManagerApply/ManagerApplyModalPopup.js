const managerApplyBtn = document.getElementById("manager-apply");
const managerApplyPopup = document.getElementById('manager-apply-popup');
const managerApplyPopupClose = document.getElementById('manager-apply-popup-close');

managerApplyPopupClose.addEventListener('click', (event) => {
    location.reload();
    managerApplyPopupBox();
})

managerApplyBtn.addEventListener('click', (event) => {
    managerApplyPopupBox();
});

const managerApplyPopupBox = () => {
    managerApplyPopup.classList.toggle("open");
}