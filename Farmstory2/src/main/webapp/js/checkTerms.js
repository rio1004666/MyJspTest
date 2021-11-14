/**
 * 
 */
$(document).ready(function(){
	var btnNext = $('.terms > div > a:nth-child(2)');
	btnNext.click(function(){
		var isChecked1 = $('input[name=chk1]').is(':checked');		
		var isChecked2 = $('input[name=chk2]').is(':checked');
		if(!isChecked1 || !isChecked2){
			alert('동의 체크를 하셔야합니다.');
			return false;
		}
		else{
			alert('회원가입 페이지로 넘어갑니다.');
			return true;
		}
	});
})