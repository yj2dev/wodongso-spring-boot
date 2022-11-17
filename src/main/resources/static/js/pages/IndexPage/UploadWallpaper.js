let wallpaperUploadBtn = document.getElementById('upload-wallpaper-btn');
let choiceWallpaper = document.getElementById('choice-wallpaper');

wallpaperUploadBtn.addEventListener('change', () => {
    let reader = new FileReader();
    reader.readAsDataURL(wallpaperUploadBtn.files[0]);
    console.log(wallpaperUploadBtn.files[0]);
    reader.onload = () => {
        choiceWallpaper.setAttribute("src", reader.result);
    }
});