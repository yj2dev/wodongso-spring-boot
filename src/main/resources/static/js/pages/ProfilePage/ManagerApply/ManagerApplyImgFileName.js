const proofImagePath = document.getElementById("proof-image");
const applyButton = document.getElementById("manager-apply-btn");

let imgText = '';

proofImagePath.addEventListener('change', (event) => {
    let uploadFileName = event.target.files[0].name;
    let fileName = document.querySelector(".upload-file-name");

    fileName.innerHTML = uploadFileName;
    imgText = uploadFileName;

})

applyButton.addEventListener('click', (event) => {
    if(imgText === ''){
        event.preventDefault();

        const error = document.querySelector('.proof-image-error');
        error.innerHTML = '증빙서류를 제출해주세요.';
    }
})
