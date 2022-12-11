const contentProfileStateList = document.querySelector('.content-profile-state-list');
const managerList = document.querySelector('.manager-list');

let isPressedDown = false;
let cursorXSpace;

contentProfileStateList.style.cursor = 'grab';

contentProfileStateList.addEventListener('mousedown', (event) => {
    isPressedDown = true;
    cursorXSpace = event.offsetX - managerList.offsetLeft;
    managerList.style.pointerEvents = 'none';
    contentProfileStateList.style.cursor = 'grabbing';
})

contentProfileStateList.addEventListener('mouseup', () => {
    contentProfileStateList.style.cursor = 'grab';
})

window.addEventListener('mouseup', () => {
    isPressedDown = false;
})

contentProfileStateList.addEventListener('mousemove', (event) => {
    if(!isPressedDown) return;
    event.preventDefault();
    managerList.style.left = `${event.offsetX - cursorXSpace}px`;
    boundList();
})

function boundList() {
    const container_rect = contentProfileStateList.getBoundingClientRect();
    const managerList_rect = managerList.getBoundingClientRect();

    if(parseInt(managerList.style.left) > 0){
        managerList.style.left = 0;
    } else if(managerList_rect.right < container_rect.right){
        managerList.style.left = `-${managerList_rect.width - container_rect.width}px`
    }

}