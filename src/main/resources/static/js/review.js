
$(function() {
    // 현재 글의 id 값
    const id = $("input[name='id']").val().trim();
    loadComment(id);

        $("#btn_review").click(function() {
            // 입력한 댓글
            const content = $("#input_review").val().trim();

            if(!content) {
                alert("후기를 작성해주세요");
                $("#input_review").focus();
                return;
            }
            // 전달할 parameter 들 준비
            const data = {
                "user_id": logged_id,
                "product_id": id,
                "content": content,
            };

            $.ajax({
                url: "/review/save", // 이 url 에 post 보냄
                type: "POST",
                data: data,
                cache: false,
                success: function(data, status, xhr) {
                    if(status == "success") {
                        if(data.status != "OK") {
                            alert(data.status);
                            return;
                        }
                        loadComment(id); // 댓글 목록 다시 업데이트
                        $("#input_review").val(''); // 입력 input 은 비우기
                    }
                },
            });
        });
});

// 특정 글 (product id) 의 댓글 목록 읽어오기
function loadComment(product_id) {
    $.ajax({
        url: "/review/list?product_id=" + product_id, // 이 url 에서 정보 가져옴
        type: "GET",
        cache: false,
        success: function(data, status, xhr){
            if(status == "success") {
                if (data.status != "OK") {
                    alert(data.status);
                    return;
                }
                buildComment(data)
                addDelete();
                addReply(); // 추가 addReply 버튼
            }
        }
    });
}

function buildComment(result) {
    $("#review_count").text(result.count); // 댓글 총 개수

    const out = [];

    result.data.forEach(review => {
        let id = review.id;
        let content = review.content;
        let reviewdate = review.reviewDate;
        let username = review.user.username;
        let user_id = review.user.id;

        // 삭제버튼 여부
        const delBtn = (logged_id != user_id) ? '' : `
            <a class="btn btn-danger" data-reviewdel-id="${id}">삭제</a>
        `;
        // 어드민응단 버튼
       const adminReplyBtn = (logged_id != 'ADMIN') ? '' : `
           <button id="btn_reply_${id}" class="btn btn-secondary">Reply</button>
           <input id="adminReplyInput_${id}" type="text" style="display:none;" placeholder="여기에 답변을 입력하십시오..." />
       `;
        const row = `
                    <tr>
                        <td>
                            <span>${content}</span>
                        </td>
                        <td><span>${username}</span></td>
                        <td><span><small class="text-secondary">${reviewdate}</small></span></td>
                        <td style="width: 10%">${delBtn}</td>
                        <td style="width: 10%">${adminReplyBtn}</td>
                    </tr>
        `;

        out.push(row);
    });


    $("#review_list").html(out.join("\n"));
}


function addDelete() {
    // 현재 상품글
    const id = $("input[name='id']").val().trim();

    $("[data-reviewdel-id]").click(function() {
        if(!confirm("리뷰를 삭제하시겠습니까?")) return;

        // 삭제할 댓글의 comment id
        const review_id = $(this).attr("data-reviewdel-id");

        $.ajax({
            url: "/review/delete",
            type: "POST",
            cache: false,
            data: JSON.stringify({"id": review_id}),
            contentType: "application/json; charset=utf-8",
            success: function(data, status, xhr) {
                if(status == "success"){
                    if(data.status != "OK") {
                        alert(data.status);
                        return;
                    }

                    // 삭제 후에도 다시 댓글 목록 갱신해야 함
                    loadComment(id);
                }
            },
        })

    });
  }

 function updateAdminReplyReviewID(review_id ,adminReplyText) {
        // AJAX 또는 양식 제출을 사용하여 관리 응답을 서버에 제출하는 논리 구현
        // AJAX 요청에 대해 jQuery 또는 fetch API와 같은 라이브러리를 사용할 수 있습니다
        // fetch API 사용
       fetch('/reply', {
           method: 'POST',
           headers: {
                'Content-Type': 'application/json'
           },
           body: JSON.stringify({ "id": review_id, "reply":adminReplyText }) //검토_id 및 회신 텍스트를 서버로 보냅니다
       })
       .then(response => {
           // 필요한 경우 서버 응답 처리
           if (response.ok) {
              console.log('관리자 응답이 성공적으로 제출되었습니다.');
           } else {
              console.error('관리자 응답이 제출하지 못했습니다.');
           }
       })
       .catch(error => {
           console.error('오류가 발생했습니다:', error);
       });

    }

 function addReply() {
     $('[id^=btn_reply_]').click(function() {
         const review_id = this.id.split('_')[2]; // 버튼 ID에서 review_id를 가져옵니다
         const $adminReplyInput = $('#adminReplyInput_' +review_id); // jQuery 개체 캐시
         $('#adminReplyInput_' + review_id).show(); // 관리자리뷰 버튼 보이기

         //Enter 키를 누를 때 회신을 제출할 입력 필드에 이벤트 수신기 추가
         $('#adminReplyInput_' + review_id).keypress(function(e) {
             if (e.which == 13) {
                 e.preventDefault();    // 기본 동작 방지
                 const adminReplyText = $(this).val();
                 updateAdminReplyReviewID(review_id, adminReplyText);

                 // 제출에 성공한 후 입력 필드를 지우고 다시 숨깁니다
                 // 참고: 'updateAdminReplyReviewID' 함수가 약속을 반환하는 경우 '.then()' 블록 안으로 이동합니다
                  $(this).val('');
                  $(this).hide();
             }
         });
     });
 }
