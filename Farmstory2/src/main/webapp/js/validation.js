/**
 * 
 */

	// Validation()
	$(document).ready(function(){
		$('.register > form').submit(function(){ // 폼양식을 제출하기 전에 실행하는 이벤트 핸들러 
				if( isUidOk == false ) // 중복사용하는경우 / 숫자로 시작하는 경우 / 한글을 사용하는 경우 =>영어에서 벗어나는 경우 
				{
						alert('아이디가 유효하지 않습니다. 다시 입력하세여.')
						return false; // 다음페이지로 넘어가지 않는다 true는? 당연히 넘어간다 
				}
				if( isPassOk == false  )
				{
					alert('비밀번호가 유효하지 않습니다. 다시 입력하세여.')
					return false; // 다음페이지로 넘어가지 않는다 true는? 당연히 넘어간다 
				}
				if(isNameOk == false)
				{
					alert('이름이 유효하지 않습니다. 다시 입력하세여.')
					return false; // 다음페이지로 넘어가지 않는다 true는? 당연히 넘어간다 
				}
				if( isNickOk == false )
				{
					alert('닉네임이 유효하지 않습니다. 다시 입력하세여.')
					return false; // 다음페이지로 넘어가지 않는다 true는? 당연히 넘어간다 전송취소 
				}
				return true; // 위의 유효성을 모두 거쳐도 맞다면 서버에 전송하자 
				
		});
	});