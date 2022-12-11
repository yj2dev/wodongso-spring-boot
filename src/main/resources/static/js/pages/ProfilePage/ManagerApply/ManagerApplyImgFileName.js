const proofImagePath = document.getElementById("proof-image");

proofImagePath.addEventListener('change', (event) => {
    let uploadFileName = event.target.files[0].name;
    let fileName = document.querySelector(".upload-file-name");

    fileName.innerHTML = uploadFileName;

})