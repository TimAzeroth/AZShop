$(function)(){
    // 탈퇴하기 버튼
    $("#button2").click(function(){
        let answer = confirm("탈퇴하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });





}