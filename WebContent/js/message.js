function checkValue()
{
			var form = document.messageform;
		
		
			if(!form.messageAddressee.value){
				alert("받는이를 입력하세요.");
				return false;
			}
			if(!form.messageSubject.value){
				alert("쪽지 제목을 입력하세요.");
				return false;
			}
			if(!form.messageContent.value){
				alert("쪽지 내용을 입력하세요");
				return false;
			}
}

function reloadclose(){
	opener.parent.location.reload();
	 window.close();
}