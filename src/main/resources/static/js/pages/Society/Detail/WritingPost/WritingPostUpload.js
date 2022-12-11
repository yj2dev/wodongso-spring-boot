let fileInput = document.getElementById('multiple-file');
let imageContainer = document.getElementById('images');
let numOfFile = document.getElementById('num-of-files');

function preview() {
    imageContainer.innerHTML = "";

    if(fileInput.files.length < 6){
        numOfFile.textContent = `${fileInput.files.length}개의 파일 선택됨`;

        if(fileInput.files.length >= 3){
            imageContainer.classList.add('excess');
        } else{
            imageContainer.classList.remove('excess');
        }

        for(i of fileInput.files){
            let reader = new FileReader();
            let figure = document.createElement('figure');
            let figCap = document.createElement('figcaption');

            figCap.innerText = i.name;
            figure.appendChild(figCap);
            reader.onload = () => {
                let img = document.createElement('img');
                img.setAttribute("src", reader.result);
                figure.insertBefore(img, figCap);
            }

            imageContainer.appendChild(figure);
            reader.readAsDataURL(i);

        }

        numOfFile.classList.remove('error');
    } else{
        numOfFile.textContent = `5개 이상의 사진은 담을 수 없습니다.`;
        numOfFile.classList.add('error');
    }

}