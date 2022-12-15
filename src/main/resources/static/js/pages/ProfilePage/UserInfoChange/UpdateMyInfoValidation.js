const userUpdateInfo = document.querySelectorAll('.update-my-info');

const updateMyInfoUpdateBtn = document.getElementById('update-my-info-btn');

updateMyInfoUpdateBtn.addEventListener('click', (event) => {

    userUpdateInfo.forEach((target) => {
        if(target.value === ''){
            event.preventDefault();
            target.setAttribute('placeholder', '입력란이 비워져 있습니다.');
            target.classList.add('error');
        } else{
            target.classList.remove('error');
        }
    })

})