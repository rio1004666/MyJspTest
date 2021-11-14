
$(document).ready(function() { 
	
	var btnNext = $('.terms > div > a:nth-child(2)') // 태그선택자를 객체로 만듬 
	btnNext.click(function(){ // 버튼 클릭이 이벤트!!!
		// 상태변수 체크 됫냐 안됫냐를 나타내는 변수 
		var isChecked1 = $('input[name=chk1]').is(':checked'); // input선택자 그중에서도 키가 ck1 이냐 
		var isChecked2 = $('input[name=chk2]').is(':checked'); // 반환값이 true false 상태 
		
		if(!isChecked1 || !isChecked2){ // 체크가 되었는지 하나라도 안되면 
			alert('동의 체크를 하셔야 합니다.');
			return false;
		}else {
			alert('회원가입 페이지로 넘어 갑니다.');
			return true; // 링크이동이 취소가 된다 
		}
	});
	
});