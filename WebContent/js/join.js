	function checkValue()
		{
			var form = document.joinform;
			var reg_pwd = /^.*(?=.{8,15})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

		
			if(!form.MEMBER_ID.value){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if(form.idDuplication.value != "idCheck"){
				alert("아이디 중복체크를 해주세요.");
				return false;
			}
			
			if(!form.MEMBER_PW.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
			if(form.MEMBER_PW.value != form.repassword.value ){
				alert("비밀번호를 동일하게 입력하세요.");
				return false;
			}	
			if(!reg_pwd.test(form.MEMBER_PW.value)){
				alert("비밀번호는 8~15자리 영문숫자 조합만가능합니다.");
				return false;
				
				
			}
			if(!form.MEMBER_NAME.value){
				alert("이름을 입력하세요.");
				return false;
			}
			
			if(isNaN(form.MEMBER_AGE.value)){
				alert("나이는 숫자만 입력가능합니다.");
				return false;
			}
			
			if(form.MEMBER_GENDER.value == "00"){
				alert("성별을 선택하세요.");
				return false;
			}
			
			if(!form.MEMBER_EMAIL.value){
				alert("이메일을 입력하세요.");
				return false;
			}
}

function openIdChk(){
	window.name = "parentForm";
	window.open("IdCheckForm.jsp","chkForm","top=200 ,left=500 width=500, height=300, resizable=no, scrollbars=no");
}

function inputIdChk(){
	document.joinform.idDuplication.value="idUncheck";
}
