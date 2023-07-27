



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

        const row = `
                    <tr>
                        <td>
                            <span>${content}</span>
                        </td>
                        <td><span>${username}</span></td>
                        <td><span><small class="text-secondary">${reviewdate}</small></span></td>
                        <td style="width: 10%">${delBtn}</td>
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
            data: {"id": review_id},
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