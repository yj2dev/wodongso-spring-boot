const writingPopup = document.getElementById('writing-popup');
const writingPopupClose = document.getElementById('writing-popup-close');

const a = true

writingPopupClose.addEventListener('click', (event) => {
    location.reload();
    WritingPopup();
})

function postWritingBtn(value){
    if(value != null) {
        WritingPopup();
    } else{
        alert("로그인 후 신청해주세요!");
    }
}

const WritingPopup = () => {
    writingPopup.classList.toggle("open");
}