const sliderPrev = document.getElementById('slider-prev');
const sliderNext = document.getElementById('slider-next');
const slider = document.querySelector('.society-detail-body-posting-filed');
const images = document.querySelectorAll('.society-detail-body-posting-filed img');

let index = 0;
let width = images[index].clientWidth;

if(index === 0){
    sliderPrev.classList.add("disable");
}

sliderNext.addEventListener('click', () => {
    index++;
    slider.style.transform = `translate(${-index * width}px)`;
    if(index === (images.length-1)){
        sliderNext.classList.add('disable');
    } else{
        sliderPrev.classList.remove("disable");
    }
})

sliderPrev.addEventListener('click', () => {
    index--;
    slider.style.transform = `translate(${-index * width}px)`;
    if(index === 0){
        sliderPrev.classList.add("disable");
    } else{
        sliderNext.classList.remove('disable');
    }
});

