const societyUpdateContent = document.querySelectorAll('.popup-body-society_item');

const societyUpdateContentBtn = document.getElementById('society-update-content-btn');

societyUpdateContentBtn.addEventListener('click', (event) => {

    societyUpdateContent.forEach((target) => {
        if(target.value === ''){
            event.preventDefault();

            target.classList.add('error');
            target.setAttribute('placeholder', '입력란이 비워져 있습니다.');
        } else {
            target.classList.remove('error');
        }
    })


})