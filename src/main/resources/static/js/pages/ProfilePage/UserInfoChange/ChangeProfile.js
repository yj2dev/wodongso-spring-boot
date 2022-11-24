let profileChangeBtn = document.getElementById('change-profile-btn');
let changeProfile = document.getElementById('change-profile');

profileChangeBtn.addEventListener('change', () => {
    let reader = new FileReader();
    reader.readAsDataURL(profileChangeBtn.files[0]);
    console.log(profileChangeBtn.files[0]);
    reader.onload = () => {
        changeProfile.setAttribute("src", reader.result);
    }
});