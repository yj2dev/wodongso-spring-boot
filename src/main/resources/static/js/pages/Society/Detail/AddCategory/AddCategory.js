const categoryValueCheckBtn = document.getElementById('category-value-check-btn');

categoryValueCheckBtn.addEventListener('click', (event) => {
    const categoryValue = document.getElementById('category-value');

    if(categoryValue.value === ''){
        event.preventDefault();

        categoryValue.classList.add("error");
        categoryValue.setAttribute('placeholder', "카테고리 내용을 적어주세요")

    } else {
        console.log(false)
    }

})