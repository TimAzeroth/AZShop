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
    $('#address').val(addtext1);
    $('#address_detail').val(adddetail1);
    $('#postcode').val(addpost1);

    var amount = $("#amount").val();

    $('#balance').val(amount + 3000);

    $("#addressbtn2").click(function() {
         $("#addresstable1").hide();
         $("#addresstable2").show();
         $("#addresstable3").hide();

        var addtext2 = $("#postAddressImp2").text();
        var adddetail2 = $("#postAddressDeValue2").text();
        var addpost2 = $("#postCode2").text();
        $('#address').val(addtext2);
        $('#address_detail').val(adddetail2);
        $('#postcode').val(addpost2);
    });

     $("#addressbtn3").click(function() {
         $("#addresstable1").hide();
         $("#addresstable2").hide();
         $("#addresstable3").show();

         var addtext3 = $("#postAddressImp3").text();
         var adddetail3 = $("#postAddressDeValue3").text();
         var addpost3 = $("#postCode2").text();
         $('#address').val(addtext3);
         $('#address_detail').val(adddetail3);
         $('#postcode').val(addpost3);
      });

     $("#addressbtn1").click(function() {
         $("#addresstable1").show();
         $("#addresstable2").hide();
         $("#addresstable3").hide();

        var addtext1 = $("#postAddressImp1").text();
        var adddetail1 = $("#postAddressDeValue1").text();
        var addpost1 = $("#postCode1").text();
        $('#address').val(addtext1);
        $('#address_detail').val(adddetail1);
        $('#postcode').val(addpost1);
     });

});

