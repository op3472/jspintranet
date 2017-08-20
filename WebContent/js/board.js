function checkValue()
		{
			var form = document.boardform;
		
			if(!form.BOARD_SUBJECT.value){
				alert("제목을 입력하세요.");
				return false;
			}
			
		
			
			if(!form.BOARD_CONTENT.value){
				alert("내용을 입력하세요.");
				return false;
			}
}
