$(function(){

      // 주소 정보가 3개인지 확인하는 함수
      function checkAddressCount() {
        var addressCount = $("#tbody tr").length;
        return addressCount;
      }

      // 주소추가 버튼을 숨기거나 보이게 하는 함수
      function toggleAddButton() {
        if (checkAddressCount() >= 3) {
          $("#footbtn .btn-success").css("display", "none");
        } else {
          $("#footbtn .btn-success").css("display", "block");
        }
      }

      // 페이지 로딩 시 주소추가 버튼 상태 설정
      toggleAddButton();

      // 주소 정보가 변경될 때마다 주소추가 버튼 상태 업데이트
      $("#tbody").on("DOMSubtreeModified", function() {
        toggleAddButton();
      });



      // 탈퇴하기 버튼
      $(".delbtn").click(function() {
          var answer = confirm("삭제하시겠습니까?")
          if(answer){
            var addressId = $(this).data("address-id"); // 클릭한 버튼의 주소 ID 값 가져오기
                      $("#deleteAddressId").val(addressId); // form의 숨겨진 input 요소의 값을 변경하여 문자열로 설정
                      $("#deleteForm").submit(); // form 제출
          }
      });
});
