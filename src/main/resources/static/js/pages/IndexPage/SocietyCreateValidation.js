const createSociety = document.querySelectorAll('.create-society');

const createSocietyBtn = document.getElementById('create-society-btn');

createSocietyBtn.addEventListener('click', (event) => {

    createSociety.forEach((target) => {
        if(target.value === ''){
            event.preventDefault();
            target.setAttribute('placeholder', '입력란이 비워져 있습니다.');
            target.classList.add('error');
        } else{
            target.classList.remove('error');
        }
    })

})