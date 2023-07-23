$(function(){
    $(document).ready(function() {
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
        });


        // 탈퇴하기 버튼
                $("#detbtn").click(function(){
                    const answer = confirm("삭제하시겠습니까?");
                    if(answer){
                        $("form[name='frmDelete']").submit();
                    }
                });
})
