const cancelOnBtn = document.querySelectorAll('.cancel-on-btn');

const acceptBox = document.querySelectorAll('.accept-box');
const cancelBox = document.querySelectorAll('.recruit-user-profile-info-apply-cancel');

const cancelOffBtn = document.querySelectorAll('.cancel-off-btn');
const rejectReason = document.querySelectorAll('.reject-reason');
const rejectReasonSend = document.querySelectorAll('.reject-reason-send');


cancelOnBtn.forEach((target, index) => {
    target.addEventListener('click', () => {

        target.classList.toggle('active');

        for(let i=0;i<acceptBox.length;i++){
            if(index === i){
                acceptBox[i].classList.toggle('active');
                cancelBox[i].classList.toggle('active');
            }
        }
    });
});

cancelOffBtn.forEach((target, index) => {
    target.addEventListener('click', (event) => {
        event.preventDefault();

        for(let i=0;i<acceptBox.length;i++){
            if(index === i){
                cancelOnBtn[i].classList.toggle('active');
                acceptBox[i].classList.toggle('active');
                cancelBox[i].classList.toggle('active');
            }
        }

    })
})

rejectReasonSend.forEach((target, index) => {
    target.addEventListener('click', (event) => {

        for(let i=0;i<acceptBox.length;i++){
            if(index === i){
                if(rejectReason[i].value == ''){
                    event.preventDefault();
                    alert('거절사유를 적어주세요!');
                }
            }
        }


    })
})