let profileUploadBtn = document.getElementById('upload-profile-btn');
let choiceProfile = document.getElementById('choice-profile');

profileUploadBtn.addEventListener('change', () => {
    let reader = new FileReader();
    reader.readAsDataURL(profileUploadBtn.files[0]);
    console.log(profileUploadBtn.files[0]);
    reader.onload = () => {
        choiceProfile.setAttribute("src", reader.result);
    }
});