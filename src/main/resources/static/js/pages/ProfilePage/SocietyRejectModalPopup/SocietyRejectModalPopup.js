const rejectPopup = document.getElementById('reject-popup');
const rejectPopupClose = document.getElementById('reject-popup-close');

const societyRejectTextCheck = document.getElementById('society-reject-text-check');

societyRejectTextCheck.addEventListener('click', (event) => {
    const societyRejectText = document.getElementById('society-reject-text');

    if(societyRejectText.value === ''){
        event.preventDefault();

        societyRejectText.setAttribute('placeholder', "사유를 적어주세요!!!");

    }

})

rejectPopupClose.addEventListener('click', (event) => {
    location.reload();
    rejectPopupBox();
})

function RejectBtn(s_name, s_num){
    console.log(s_name, s_num);
    rejectPopupBox(s_name, s_num);
}

const rejectPopupBox = (s_name, s_num) => {
    rejectPopup.classList.toggle("open");

    const listName = document.getElementById('reject-name');
    listName.innerText = s_name;

    const RejectPopupForm = document.getElementById('reject-popup-form');
    let actionURI = `/society/reject/${s_num}`;

    RejectPopupForm.setAttribute('action', actionURI);
}
