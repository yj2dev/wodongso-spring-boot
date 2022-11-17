const slidePage = document.getElementById("slidePage");
const firstNextBtn = document.getElementById("firstNextBtn");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");

const class_of = document.getElementById("class_of");
const name = document.getElementById("name");
const nickname = document.getElementById("nickname");
const password = document.getElementById("password");
const passwordCheck = document.getElementById("passwordCheck");
const phoneNumber = document.getElementById("phoneNumber");

let setIsClassNumber = false;
let setIsName = false;
let setIsNickName = false;
let setIsPassword = false;
let setIsConfirmPassword = false;
let setIsPhoneNumber = false;

firstNextBtn.addEventListener("click", (event) => {
	event.preventDefault();
	
	onClassNumberHandler();
	
	if(setIsClassNumber){
		slidePage.style.marginLeft = "-100%";	
	}
	
})	


const setError = (element, message) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector('.error');
	
	errorDisplay.innerText = message;
	
	inputControl.classList.add('error');
	inputControl.classList.remove('success');
}

const setSuccess = (element) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector('.error');
	
	errorDisplay.innerText = '';
	
	inputControl.classList.add('success');
	inputControl.classList.remove('error');
}


prevBtn.addEventListener("click", (event) => {
	event.preventDefault()
	slidePage.style.marginLeft = "0%";
})

nextBtn.addEventListener('click', (event) => {
	
	onNameHandler();
	onNickNameHandler();
	onPasswordHandler();
	onConfirmPassword();
	onPhoneNumberHandler();
	
	if(!(setIsClassNumber && setIsName && setIsNickName && setIsPassword && setIsConfirmPassword && setIsPhoneNumber)){
		event.preventDefault();
	}
	

})

function onClassNumberHandler(){
	const number = class_of.value.trim();
	const numberRegex = /^[0-9]+$/; 
	
	if(number === ''){
		setError(class_of, '학번을 입력해주세요.');
		setIsClassNumber = false;
	} else if(!numberRegex.test(number)){
		setError(class_of, '숫자만 입력 가능합니다.');
		setIsClassNumber = false;
	} else if(number.length < 8 || number.length > 10){
		setError(class_of, '학번을 제대로 입력해주세요.');
		setIsClassNumber = false;
	} else if(/(\w)\1\1/.test(number)){
		setError(class_of, '학번을 제대로 입력해주세요(동일한 학번따위는 없어 이자시가).');
		setIsClassNumber = false;
	} else {
		setSuccess(class_of);
		setIsClassNumber = true;
	}
	
}

function onNameHandler(){
	const nameValue = name.value.trim();
	
	if(nameValue === ''){
		setError(name, '이름을 입력해주세요.');
		setIsName = false;
	} else if(nameValue.length < 2 || nameValue.length > 10){
		setError(name, '2글자 이상 10글자 미만으로 입력해주세요.');
		setIsName = false;
	} else{
		setSuccess(name);
		setIsName = true;
	}
	
}

function onNickNameHandler(){
	const nickNameValue = nickname.value.trim();
	
	if(nickNameValue === ''){
		setError(nickname, '닉네임을 입력해주세요.');
		setIsNickName = false;
	}else if(nickNameValue.length < 2 || nickNameValue.length > 15){
		setError(nickname, '2글자 이상 15글자 미만으로 입력해주세요.');
		setIsNickName = false;
	} else{
		setSuccess(nickname);
		setIsNickName = true;
	}
	
}
function onPasswordHandler(){
	const passwordValue = password.value.trim();
	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	
	if(passwordValue === ''){
		setError(password, '비밀번호를 입력해주세요.');
		setIsPassword = false;
	}else if(!passwordRegex.test(passwordValue)){
		setError(password, '8~25자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
		setIsPassword = false;
	} else{
		setSuccess(password);
		setIsPassword = true;
	}
	
}
function onConfirmPassword(){
	const passwordCheckValue = passwordCheck.value.trim();
	
	if(passwordCheckValue === ''){
		setError(passwordCheck, '비밀번호 확인란을 입력해주세요');
		setIsConfirmPassword = false;
	}else if(passwordCheckValue !== password.value){
		setError(passwordCheck, '비밀번호가 틀립니다');
		setIsConfirmPassword = false;
	} else{
		setSuccess(passwordCheck);
		setIsConfirmPassword = true;
	}
}
function onPhoneNumberHandler(){
	const phoneNumberValue = phoneNumber.value.trim();
	const phoneRegex = /^[0-9]{8,13}$/;
	
	if(phoneNumberValue === ''){
		setError(phoneNumber, '전화번호를 입력해주세요.');
		setIsPhoneNumber = false;
	} else if(!phoneRegex.test(phoneNumberValue)){
		setError(phoneNumber, '숫자만 입력 가능합니다.');
		setIsPhoneNumber = false;
	} else {
		setSuccess(phoneNumber)
		setIsPhoneNumber = true;
	}
}
