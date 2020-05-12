$(function(){
	$(document).on('click', '#loginbtn', function(e){
		e.preventDefault();
		var elements = document.getElementsByTagName("input"); 
		for (var i = 0; i < elements.length; i++) { 
			if(elements[i].value == "") { 
				alert(elements[i].type + "을 입력해주세요 !!!");
				elements[i].focus(); return false; 
			} 
		}

		
		$.ajax({
			url : './userSignin',
			type : 'post',
			data : $('#loginForm').serialize(),
			success: function(data) {
				if (data == 1 ) { // 1 : 회원 정보 존재 (메인으로 이동)
					alert("로그인을 환영합니다^^  메인페이지로 이동합니다.");
					window.location.href='/';
					
				} else { // 0 : 회원 정보 미 존재 (/loginForm 다시 보여주기)
					alert("ID또는 PW가 일치 하지 않습니다. 다시 입력해 주세요");
					window.location.href='/login';
				}
			},
			error: function() {
				alert("실패");
			}
		});
	}); 	
});