const listHeader = ["society-member-state", "society-create-state", "manager-member-state", "society-create-list", "manager-member-list"];
const listItem = ["societyMemberState", "societyCreateState", "managerMemberState", "societyCreateList", "managerMemberList"];

let clickListHeader = null;
let selectListItem = null;

function getListBtn(listHeaderId, listItemId){
    clickListHeader = listHeaderId;
    selectListItem = listItemId;
    showListItem();
}

function showListItem(){
    let i, listHeaderId, listHeaderIdTag, listItemId, listItemBox;

    for(i=0;i<listHeader.length;i++){
        listHeaderId = listHeader[i];
        listHeaderIdTag = document.getElementById(listHeaderId);

        listItemId = listItem[i];
        listItemBox = document.getElementById(listItemId);

        if(clickListHeader == listHeaderId && selectListItem == listItemId){
            listHeaderIdTag.classList.add('active');
            listItemBox.classList.add('show');
        } else{
            listHeaderIdTag.classList.remove('active');
            listItemBox.classList.remove('show');
        }

    }
}