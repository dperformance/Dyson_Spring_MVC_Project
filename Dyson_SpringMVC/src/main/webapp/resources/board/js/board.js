$(function(){


	$('#btn-save').on('click', function(){
		var elements = document.getElementsByClassName("inputGroup"); 
		for (var i = 0; i < elements.length; i++) { 
			if(elements[i].value == "") { 
				if(elements[i].name != 'no') {
					alert(elements[i].name + "을 입력해주세요 !!!");
					elements[i].focus(); return false; 
				}
			} 
		}
		
		$.ajax({
			url: './insert',
			type: 'post',
			data: $('#boardForm').serialize(),
			success: function(data) {
				alert('글이 등록되었습니다. \n'
						+'게시판으로 이동합니다.');
				location.href='/board';
			},
			error: function() {
				alert('게시글 등록 실패!!!');
			}
		});
	});
	
	var b_seq = $('#b_seq').val(); //공통으로 사용하는 글로벌 변수
	
	$('#btn-delete').on('click', function(){
		var answer = confirm('게시글을 정말 삭제 하시겠습니까?????');
		if (answer) { //게시글 삭제 요청 처리
			$.ajax({
				url: '/board/delete/'+b_seq,
				type: 'DELETE',
				success: function() {
					alert('게시글이 정상적으로 삭제 처리 되었습니다. \n'
							+'게시판 목록으로 돌아갑니다.');
					location.href='/board';
				},
				error: function() {
					alert('게시글 삭제 실패!!!');
				}
			});
		}
	});
	
	$('#btn-update').on('click', function(){
		var answer = confirm('게시글을 정말 수정 하시겠습니까???');
		if(answer) {
			$.ajax({
				url: './update',
				type: 'POST',
				data: $('#boardForm').serialize(),
				success: function() {
					alert('게시글이 정상적으로 수정 되었습니다. \n'
							+'게시판 목록으로 돌아갑니다.');
					location.href='/board/content/'+b_seq;
				},
				error: function(){
					alert('게시글 수정이 실패하였습니다.');
				}
			});
		}
	});
	
	$('#btn-comm').on('click', function(){ //댓글 등록 
		$.ajax({
			url:'/board/insertComm',
			type: 'post',
			data: $('#commForm').serialize(),
			success: function() {
				alert('댓글 등록이 성공하였습니다.');
				location.href='/board/content/'+b_seq;
			},
			error: function() {
				alert('댓글 등록이 실패하였습니다.');
			}
		});
	});
});
	

	function commDel(c_seq) { //댓글 삭제
		var b_seq = $('#b_seq').val();
		var answer = confirm('댓글을 정말 삭제 하시겠습니까???');
		if(answer) {
			$.ajax({
				url:'/board/commDelete/'+c_seq,
				type: 'delete',
				success: function() {
					alert('댓글 삭제를 성공하였습니다.');
					location.href='/board/content/'+b_seq;
				},
				error: function() {
					alert('댓글 삭제를 실패하였습니다.');
				}
			});
		}
	}
	
	function reComm(c_seq) { //대댓글(답글) 등록
		var b_seq = $('#b_seq').val();
		$.ajax({
			url:'/board/insertReComm',
			type: 'post',
			data: $('#reCommForm'+c_seq).serialize(),
			success: function() {
				alert('답글 등록을 성공하였습니다.');
				location.href='/board/content/'+b_seq;
			},
			error: function() {
				alert('답글 등록을 실패하였습니다.');
			}
		});
	}
	


function listValue() {
	var selValue = document.getElementById('listValue').value,
	    currentPage = document.getElementById('currentPage').value;
	location.href='/board?currentPage='+currentPage+'&countPerPage='+selValue;
}

function searchListValue() {
	var selValue = document.getElementById('searchListValue').value,
	    currentPage = document.getElementById('currentPage').value,
	    conSelect = document.getElementById('conSelect').value,
	    searchData = document.getElementById('searchData').value;
	location.href='/boardSearch?currentPage='+currentPage+'&countPerPage='+selValue+'&conSelect='+conSelect+'&searchData='+searchData;
}

function replyView(c_seq) {
	$("#"+c_seq).toggle("fast");
	console.log("c_seq : " + c_seq);
}

