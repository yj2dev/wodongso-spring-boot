const dateChangeNumber = document.querySelectorAll('.date-change-number');

dateChangeNumber.forEach((target) => {

    console.log(target.innerText)

    const date = new Date(target.innerText);
    const formatDate = new Intl.DateTimeFormat('ko-KR').format(date);
    target.innerHTML = formatDate;
})
//
//
// const date = new Date(dateChangeNumber.innerText);
//
// const formatDate = new Intl.DateTimeFormat('ko-KR').format(date);
//
// dateChangeNumber.innerText = ;