function checkValue()
		{
			var form = document.theForm;
		
		
			if(!form.schedule_subject.value){
				alert("제목을 입력하세요.");
				return false;
			}
			if(!form.schedule_content.value){
				alert("내용을 입력하세요.");
				return false;
			}
	}