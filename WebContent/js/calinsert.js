function checkValue()
		{
			var form = document.theForm;
		
		
			if(!form.date_Y.value){
				alert("년도를 입력하세요.");
				return false;
			}
			if(!form.date_M.value){
				alert("월를 입력하세요.");
				return false;
			}
			if(!form.date_D.value){
				alert("일를 입력하세요.");
				return false;
			}
			if(!form.schedule_subject.value){
				alert("제목을 입력하세요.");
				return false;
			}
			if(!form.schedule_content.value){
				alert("내용을 입력하세요.");
				return false;
			}
	}