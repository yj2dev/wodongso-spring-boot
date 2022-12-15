const writingTitle = document.getElementById('writing-title');
const writingContent = document.getElementById('writing-content');
const checkFiles = document.getElementById('num-of-files');

const writingBtn = document.getElementById('writing-btn');

let setWritingTitle = false;
let setWritingContent = false;
let setCheckFiles = false;

const setWritingError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.writing-error');

    errorDisplay.innerText = message;

    inputControl.classList.add('error');
    inputControl.classList.remove('success');
}

const setWritingSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.writing-error');

    errorDisplay.innerText = '';

    inputControl.classList.add('success');
    inputControl.classList.remove('error');
}

writingBtn.addEventListener('click', (event) => {

    onCheckWritingTitle();
    onCheckWritingContent();
    onCheckFile();

    if(!(setWritingTitle && setWritingContent && setCheckFiles)){
        event.preventDefault();
    }

})

function onCheckWritingTitle(){
    const writingTitleValue = writingTitle.value.trim();

    if(writingTitleValue === ''){
        setWritingError(writingTitle, '제목을 작성해주세요.');
        setWritingTitle = false;
    }else if(writingTitleValue < 3){
        setWritingError(writingTitle, '제목은 3글자 이상부터 가능합니다.');
        setWritingContent = false;
    }  else{
        setWritingSuccess(writingTitle);
        setWritingTitle = true;
    }
}

function onCheckFile(){
    const writingContentValue = writingContent.value.trim();

    if(writingContentValue === ''){
        setWritingError(writingContent, '본문을 작성해주세요.');
        setWritingContent = false;
    }else if(writingContentValue < 10){
        setWritingError(writingContent, '본문은 10글자 이상부터 가능합니다.');
        setWritingContent = false;
    } else{
        setWritingSuccess(writingContent);
        setWritingContent = true;
    }
}

function onCheckWritingContent(){
    const checkFileValue = checkFiles.textContent;

    if(checkFileValue === '선택된 파일이 없습니다'){
        checkFiles.classList.add("error");
        checkFiles.classList.remove("success");
        setCheckFiles = false;
    } else{
        checkFiles.classList.add("success");
        checkFiles.classList.remove("error");
        setCheckFiles= true;
    }

}