$(function(){
    // 글 [삭제] 버튼
    $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("p[name='delete']").remove();
        }
    });

    // 현재 글의 id 값
    const id = $("input[name='id']").val().trim();

    // 현재 글의 댓글을 불러온다.
    loadReview_list(id);


    $("#btn_review").click(function(){
        // 입력한 댓글
        const review = $("#input_review").val().trim();

        // 검증
        if(!content){
            alert("리뷰를 입력 해주세요");
            $("#input_review").focus();
            return;
        }

        // 전달할 parameter 들 준비
        const data = {
            "user_id": id,
            "product_id": product_id,
            "content": content,
        };

        $.ajax({
            url: "/review",
            type: "POST",
            data: data,
            cache: false,
            success: function(data, status, xhr){
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }
                    loadComment(id);   // 댓글 목록 다시 업데이트
                    $("#input_review").val('');   // 입력 input 은 비우기
                }
            },
        });


    });


});

// 특정 글 (user_id) 의 댓글 목록 읽어오기
function loadComment(user_id){
    $.ajax({
        url: "/review/list?id=" + user_id,
        type: "GET",
        cache: false,
        success: function(data, status, xhr){
            if(status == "success"){
                //alert(xhr.responseText);   // response 결과 확인용.

                // data 매개변수 : JSON 으로 response 되면 JS object 로 변환되어 받아온다
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                buildComment(data);    // 댓글 화면 렌더링

                // ★댓글목록을 불러오고 난뒤에 삭제에 대한 이벤트 리스너를 등록해야 한다
                addDelete();
            }
        }

    });
}

function buildReview(result){
    $("#review_cnt").text(result.content);   // 댓글 총 개수

    const out = [];

    result.data.forEach(review => {
        let id = review.id;
        let content = review.content;
        let regdate = review.regdate;

        let user_id = review.user.id;
        let product_id =  review.product_id;

        // 삭제버튼 여부
        review delBtn = (user_id !== product_id) ? '' : `
            <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip"
                data-reviewdel-id="${id}" title="삭제"></i>
        `;

        const row = `
              <tr>
                <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
                <td>
                  <span>${content}</span>
                   ${delBtn}
                </td>
                <td><span><small class="text-secondary">${regdate}</small></span></td>
              </tr>
                `;

         out.push(row);
    });


    $("#review_list").html(out.join("\n"));
}

function addDelete(){
    // 현재글
    const id = $("input[name='id']").val().trim();

    $("[data-cmtdel-id]").click(function(){
        if(!confirm("댓글을 삭제하시겠습니까?")) return;

        // 삭제할 댓글의 comment id
        const review_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url: "/review/delete",
            type: "POST",
            cache: false,
            data: {"id":review_id },
            success: function(data, status, xhr){
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }


                // 삭제후에도 다시 댓글목록 갱신해야 함
                loadreview(id);
            },
        });

    });
}

function container(){
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;

}











