$(function(){
    $("#backBtn").click(function() {
      history.back();
    });

    // 집 명칭 버튼 클릭 시 주소가 바뀌는 작업
    $("#addresstable2").hide();
    $("#addresstable3").hide();

    var addtext1 = $("#postAddressImp1").text();
    var adddetail1 = $("#postAddressDeValue1").text();
    var addpost1 = $("#postCode1").text();
    $('#addressValue').val(addtext1);
    $('#addressDeValue').val(adddetail1);
    $('#addPostcode').val(addpost1);


    $("#addressbtn2").click(function() {
         $("#addresstable1").hide();
         $("#addresstable2").show();
         $("#addresstable3").hide();

        var addtext2 = $("#postAddressImp2").text();
        var adddetail2 = $("#postAddressDeValue2").text();
        var addpost2 = $("#postCode2").text();
        $('#addressValue').val(addtext2);
        $('#addressDeValue').val(adddetail2);
        $('#addPostcode').val(addpost2);
    });

     $("#addressbtn3").click(function() {
         $("#addresstable1").hide();
         $("#addresstable2").hide();
         $("#addresstable3").show();

         var addtext3 = $("#postAddressImp3").text();
         var adddetail3 = $("#postAddressDeValue3").text();
         var addpost3 = $("#postCode2").text();
         $('#addressValue').val(addtext3);
         $('#addressDeValue').val(adddetail3);
         $('#addPostcode').val(addpost3);
      });

     $("#addressbtn1").click(function() {
         $("#addresstable1").show();
         $("#addresstable2").hide();
         $("#addresstable3").hide();

        var addtext1 = $("#postAddressImp1").text();
        var adddetail1 = $("#postAddressDeValue1").text();
        var addpost1 = $("#postCode1").text();
        $('#addressValue').val(addtext1);
        $('#addressDeValue').val(adddetail1);
        $('#addPostcode').val(addpost1);
     });

});