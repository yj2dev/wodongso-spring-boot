let wallpaperUpdateBtn = document.getElementById('update-wallpaper-btn');
let updateChoiceWallpaper = document.getElementById('update-choice-wallpaper');

wallpaperUpdateBtn.addEventListener('change', () => {
    let reader = new FileReader();
    reader.readAsDataURL(wallpaperUpdateBtn.files[0]);
    console.log(wallpaperUpdateBtn.files[0]);
    reader.onload = () => {
        updateChoiceWallpaper.setAttribute("src", reader.result);
    }
});