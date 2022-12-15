const currentPassword = document.getElementById('current-password');
const updatePassword = document.getElementById("update-password");
const updatePasswordCheck = document.getElementById("update-password-check");

const updatePasswordBtn = document.getElementById('update-password-btn');

let setIsCurrentPassword = false;
let setIsUpdatePassword = false;
let setIsUpdateConfirmPassword = false;

updatePasswordBtn.addEventListener('click', (event) => {

    onCurrentPasswordHandler();
    onUpdatePasswordHandler();
    onUpdateConfirmPasswordHandler();

    if(!(setIsCurrentPassword && setIsUpdatePassword && setIsUpdateConfirmPassword)){
        event.preventDefault();
    }
})

const setUpdateError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.password-error');

    errorDisplay.innerText = message;

    inputControl.classList.add('error');
    inputControl.classList.remove('success');
}

const setUpdateSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.password-error');

    errorDisplay.innerText = '';

    inputControl.classList.add('success');
    inputControl.classList.remove('error');
}

function onCurrentPasswordHandler(){
    const currentPasswordValue = currentPassword.value.trim();

    if(currentPasswordValue === ''){
        setUpdateError(currentPassword, '현재 비밀번호를 입력해주세요.');
        setIsCurrentPassword = false;
    } else{
        setUpdateSuccess(currentPassword);
        setIsCurrentPassword = true;
    }
}

function onUpdatePasswordHandler(){
    const updatePasswordValue = updatePassword.value.trim();
    const updatePasswordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

    if(updatePasswordValue === ''){
        setUpdateError(updatePassword, '새 비밀번호를 입력해주세요.');
        setIsUpdatePassword = false;
    }else if(!updatePasswordRegex.test(updatePasswordValue)){
        setUpdateError(updatePassword, '8~25자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
        setIsUpdatePassword = false;
    } else{
        setUpdateSuccess(updatePassword);
        setIsUpdatePassword = true;
    }
}

function onUpdateConfirmPasswordHandler(){
    const updatePasswordCheckValue = updatePasswordCheck.value.trim();

    if(updatePasswordCheckValue === ''){
        setUpdateError(updatePasswordCheck, '새 비밀번호 확인란을 입력해주세요');
        setIsUpdateConfirmPassword = false;
    }else if(updatePasswordCheckValue !== updatePassword.value){
        setUpdateError(updatePasswordCheck, '비밀번호가 틀립니다');
        setIsUpdateConfirmPassword = false;
    } else{
        setUpdateSuccess(updatePasswordCheck);
        setIsUpdateConfirmPassword = true;
    }
}