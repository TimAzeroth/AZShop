$(function(){
    $("('[name =pageRows]')").change(function(){

        var frm= $("['name=frmPageRows']");
        frm.attr("method","post");
        frm.attr("action","fromRows");
        frm.attr("action","pageRows");
        frm.submit();

    });
});