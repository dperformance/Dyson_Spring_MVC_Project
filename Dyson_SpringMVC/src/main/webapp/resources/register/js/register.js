$(function(){

		$('#submit').on('click', function(){
			$.ajax({
				url : '/insertMember',
				type : 'post',
				data : $('#regiForm').serialize(),
				success: function() {
					alert("Registration in completed!! \n"
							+ "Welcome to Dyson SpringMVC web !! \n"
							+ "Now you can sign in :)");
					location.href='/login';
				},
				error : function() {
					alert("Exception Error!!!!!!!!!");
				}
			});
		});
	
		// 닉네임 중복 검사
		$("#mem_name").blur(function() {
			$.ajax({
				url : '/getMemberNick',
				type : 'post',
				data : $("#regiForm").serialize(),
				success : function(data) {
					console.log("data : " + data);	
					
					if(data == 1) {
						// 1 : 닉네임 중복되는 문구
						$("#getMemberNick").text("Nickname has already been taken :p");
						$("#getMemberNick").css("color", "red");
						$("#submit").attr("disabled", true);
						
					} else {
						// 0 : 사용가능
						$("#getMemberNick").text("It's available!!");
						$("#getMemberNick").css("color", "blue");
						$("#submit").attr("disabled", false);
						
						// Nickname 입력란의 빈 공백일경우
						if($('#mem_name').val() == ""){
							$('#getMemberNick').text('Please fill in the blank :(');
							$('#getMemberNick').css('color', 'red');
							$("#submit").attr("disabled", true);				
						}
					}
				}, 
				error : function() {
					console.log("실패");
				}
			});
		});

		
	// 아이디(이메일) 중복 검사
		$("#mem_email").blur(function() {
			$.ajax({
				url : '/getMemberEmail',
				type : 'post',
				data : $("#regiForm").serialize(),
				success : function(data) {
					console.log("data : " + data);	
					
					if(data == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#getMemberEmail").text("Email has already been taken :p");
						$("#getMemberEmail").css("color", "red");
						$("#submit").attr("disabled", true);
						
					} else {
						
						var idRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
						
						if(idRegExp.test($('#mem_email').val())){
							// 0 : 사용가능
							$("#getMemberEmail").text("It's available!!");
							$("#getMemberEmail").css("color", "blue");
							$("#submit").attr("disabled", false);
							
							// Email 입력란의 빈 공백일경우
						} else if($('#mem_email').val() == ""){
							$('#getMemberEmail').text('Please fill in the blank :(');
							$('#getMemberEmail').css('color', 'red');
							$("#submit").attr("disabled", true);				
							
							//유효성검사에 실패했을 경우
						} else {
							$('#getMemberEmail').text('Please write your Email correctly :(');
							$('#getMemberEmail').css('color', 'red');
							$("#submit").attr("disabled", true);				
						}
					}
				}, 
				error : function() {
					console.log("실패");
				}
			});
		});
							
		
		$("#mem_pw").blur(function() {
			var pw = $("#mem_pw").val();
			if(pw.length < 6 || pw.length > 20){
				$('#password_check').text('Please enter the password at least 6 characters(maximum:20)');
				$('#password_check').css('color', 'red');
				$("#submit").attr("disabled", true);
			} else {
				$("#password_check").text("It's available!!");
				$("#password_check").css("color", "blue");
				$("#submit").attr("disabled", false);
			}
		});
		
});