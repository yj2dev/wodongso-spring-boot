const SubApplyListPopup = document.getElementById('sub-apply-list-popup');
const SubApplyListPopupClose = document.getElementById('sub-apply-list-popup-close');

SubApplyListPopupClose.addEventListener('click', (event) => {
    location.reload();
    SubApplyListPopupBox();
})

function SupApplyList(){
    SubApplyListPopupBox();
}

const SubApplyListPopupBox = () => {
    SubApplyListPopup.classList.toggle("open");
}