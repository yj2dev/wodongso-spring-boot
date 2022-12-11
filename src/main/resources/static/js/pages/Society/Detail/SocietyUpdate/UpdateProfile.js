let profileUpdateBtn = document.getElementById('update-profile-btn');
let updateChoiceProfile = document.getElementById('update-choice-profile');

profileUpdateBtn.addEventListener('change', () => {
    let reader = new FileReader();
    reader.readAsDataURL(profileUpdateBtn.files[0]);
    reader.onload = () => {
        updateChoiceProfile.setAttribute("src", reader.result);
    }
});