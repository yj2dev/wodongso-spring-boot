const managerRejectPopup = document.getElementById('manager-reject-popup');
const managerRejectPopupClose = document.getElementById('manager-reject-close');
const managerRejectTextCheck = document.getElementById('manager-reject-text-check');

managerRejectTextCheck.addEventListener('click', (event) => {
    const managerRejectText = document.getElementById('manager-reject-text');

    if(managerRejectText.value === ''){
        event.preventDefault();

        managerRejectText.setAttribute('placeholder', "사유를 적어주세요!!!");

    }
})

managerRejectPopupClose.addEventListener('click', (event) => {
    location.reload();
    managerRejectPopupBox();
})

function ManagerRejectBtn(name, id){
    console.log(name, id);
    managerRejectPopupBox(name, id);
}

const managerRejectPopupBox = (name, id) => {
    managerRejectPopup.classList.toggle("open");

    const listName = document.getElementById('manager-reject-name');
    listName.innerText = name + "님 환영합니다.";

    const managerRejectPopupForm = document.getElementById('manager-reject-popup-form');

    let actionURI = `/user/manager-reject/${id}`;

    managerRejectPopupForm.setAttribute('action', actionURI);

}