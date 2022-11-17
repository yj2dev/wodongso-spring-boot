/* 전역변수 지정
   regionInfo = 지역명 선택
   schoolInfo = 지역에 있는 모든 대학교를 담을 수 있는 배열
   magorInfo = 대학교에 있는 모든 학과를 담을 수 있는 배열
*/
		
let regionInfo = "";
let schoolInfo = [];
let majorInfo = [];
		
function SchoolSerch(value){
	/* 대학교 정보가 담긴 데이터 파일 불러오기 */
	fetch('/json/University.json').then(res => {return res.json()}).then(jsonData => {

		/* 대학교 정보가 객체로 이루어진 jsonData를 info 변수에 값 할당 */
		const info = jsonData;

		/* 지역변수 regionInfo에 선택한 지역명 값 할당 */
		regionInfo = value;
				
		let i=0;
				
		/* 
			schoolInfo 배열안에 값이 있으면 지역변수이기 때문에
			다른 지역을 선택할때 마다 값이 합쳐져서 나오기 때문에
			함수를 시작하고 초기화를 한번 해준다.
		*/
		schoolInfo = [];

		schoolInfo.push("-");

		/* 
			반복문과 조건문을 통해 선택한 지역의 대학교를 모두
			schoolInfo 배열안에 값을 추가시켜 준다
		*/
		while(i < info.length){
			if(info[i].지역 == regionInfo){
				schoolInfo.push(info[i].학교명);
			};
			i++;
		}
				
		/* 
			가져온 데이터 파일에 데이터 정보가 학교 => 학과 이렇게 저장이 되있어
			같은 지역에 중복된 대학교가 최소 1000개 이상 있기 때문에
			중복된 데이터 값들을 지우고 대학교 정보가 1개씩만 있게 배열을 생성
		*/
		const schoolInfoCopy = new Set(schoolInfo);
		schoolInfo = [...schoolInfoCopy];
				
		/* 
			SchoolSerch 함수가 끝나면 .html select 태그에 옵션 값들이
			생기는데 배열 값을 지우지 않고 옵션 값들을 계속 추가하면
			서울, 부산 대학교 정보들이 다 같이 저장이되어 옵션에 추가가 됨
			그렇기 때문에 옵션을 생성하기 전에 옵션 값들이 있으면 모든 정보를
			없애고 지역에 맞는 옵션 값들을 다시 생성
		*/
		while(school.options.length > 0){
			school.options.remove(0);
		}
				
		/* 
			반복문을 통해 schoolInfo에 있는 지역별 대학교 정보를
			.html select 태그에 옵션을 추가함
		*/				
		Array.from(schoolInfo).forEach(function(el){
			let option = new Option(el, el);
			school.appendChild(option);
		})
	})
}
		
function majorSerch(value){
	/* 대학교 정보가 담긴 데이터 파일 불러오기 */
	fetch('/json/University.json').then(res => {return res.json()}).then(jsonData => {
		/* 대학교 정보가 객체로 이루어진 jsonData를 info 변수에 값 할당 */
		const info = jsonData;

		let i=0;
				
		/* 
			margorInfo 배열안에 값이 있으면 지역변수이기 때문에
			다른 지역을 선택할때 마다 값이 합쳐져서 나오기 때문에
			함수를 시작하고 초기화를 한번 해준다.
		*/
		majorInfo = [];
				
		/* 
			반복문과 조건문을 통해 선택한 지역별 대학교에 있는 모든 학과
			schoolInfo 배열안에 값을 추가시켜 준다.
		*/

		majorInfo.push("-");

		while(i < info.length){
			if(info[i].지역 == regionInfo && info[i].학교명 == value){
				majorInfo.push(info[i].학과);
			};
			i++;
		}
				
		/* 
			가져온 데이터 파일에 데이터 정보가 학교 => 학과 이렇게 저장이 되있어
			같은 지역에 중복된 대학교가 최소 1000개 이상 있기 때문에
			중복된 데이터 값들을 지우고 대학교 정보가 1개씩만 있게 배열을 생성
		*/
		const majorInfoCopy = new Set(majorInfo);
		majorInfo = [...majorInfoCopy];
			
		/* 
			majorSerch 함수가 끝나면 .html select 태그에 옵션 값들이
			생기는데 배열 값을 지우지 않고 옵션 값들을 계속 추가하면
			서울, 부산 대학교 정보들이 다 같이 저장이되어 옵션에 추가가 됨
			그렇기 때문에 옵션을 생성하기 전에 옵션 값들이 있으면 모든 정보를
			없애고 지역에 맞는 옵션 값들을 다시 생성
		*/				
		while(major.options.length > 0){
			major.options.remove(0);
		}
				
		/* 
			반복문을 통해 majorInfo에 있는 지역별 대학교 정보를
			.html select 태그에 옵션을 추가함
		*/	
		Array.from(majorInfo).forEach(function(el){
			let option = new Option(el, el);
			major.appendChild(option);
		})
	})
}
		
/* .html 파일에 region id를 가진 태그를 가지고 온 뒤, 이벤트리스너로 옵션 값들이 바뀌는걸 인지 */
let regionSelect = document.getElementById('region').addEventListener('change', function(event){
	SchoolSerch(event.target.value);
});

/* .html 파일에 school, major id를 가진 태그를 가지고 온다 */
let school = document.getElementById('school');
let major = document.getElementById('major');
		
/*  school id를 가진 태그를 이벤트리스너로 옵션 값들이 바뀌는걸 인지 */
school.addEventListener('change', (event) => {
	majorSerch(event.target.value);
})
		
		/* 
		let testhaha = "";
		let arr = [];
		let majorArr = [];
		
		let select = document.getElementById('location').addEventListener('change', function(event){
			test(event.target.value);
		});
		
		let school = document.getElementById('school');
		let major = document.getElementById('major');
		
		let schoolcopy = document.getElementById('school').addEventListener('change', function(event){
			model_two(event.target.value);
		});
		
		function test(value){
			
			fetch('resources/json/University.json').then(res => {
				
				if(res.status != 200){
					alert("API 불러오기 실패");
				} 
				else {
					return res.json();
				} 
				
			}).then(jsonData => {
				
				const data = jsonData;

				testhaha = value;
				var i=0;
				
				arr = [];
				
				while(i<data.length){
					if(data[i].지역 == value){
						arr.push(data[i].학교명);
					};
					i++;
				}
				
				const copyArr = new Set(arr);
				arr = [...copyArr];
				
				while(school.options.length > 0){
					school.options.remove(0);
				}
				
				Array.from(arr).forEach(function(el){
					let option = new Option(el, el);
					
					school.appendChild(option);
				})
				
			})
		}
		
		function model_two(value){
			fetch('resources/json/University.json').then(res => {
				
				if(res.status != 200){
					alert("API 불러오기 실패");
				} 
				else {
					return res.json();
				} 
				
			}).then(jsonData => {
				
				const data = jsonData;
				
				var i=0;
				
				majorArr = [];
				
				while(i<data.length){
					if(data[i].지역 == testhaha && data[i].학교명 == value){
						majorArr.push(data[i].학과);
					};
					i++;
				}
				
				const copyArr = new Set(majorArr);
				majorArr = [...copyArr];
				
				console.log(majorArr);
				
				while(major.options.length > 0){
					major.options.remove(0);
				}
				
				Array.from(majorArr).forEach(function(el){
					let option = new Option(el, el);
					
					major.appendChild(option);
				})
				
			})
		}
		
		let clickEvent = document.getElementById("btn").addEventListener('click', () => {
			console.log(testhaha);
		}); */
		
		/* 
		function getSelectValue(value){
			var letas = value;
			console.log(letas);
			ajax(letas)
		}
		
		function ajax(location){
			fetch('resources/json/University.json').then(res => {
				
				if(res.status != 200){
					alert("API 불러오기 실패");
				} 
				else {
					return res.json();
				} 
				
			}).then(jsonData => {
				
				const data = jsonData;
				
				var i=0;
				
				
				var university = "호남대학교";
				var sd = "미래자동차과";
				
				while(i<data.length){
					if(data[i].지역 == location){
						console.log(data[i].학교명);
					};
					i++;
				}
				
				if(this.letas != "") {
					while(i<data.length){
						if(data[i].지역 == this.letas && data[i].학교명 == university){
							console.log(data[i].학과);
						}
						i++
					}
				}	
				
				while(i<data.length){
					if(data[i].학교명 == university && data[i].지역 == location){
						console.log(data[i].학과);
					};
					i++;
				} 
				
				
			})
		} */