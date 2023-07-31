$(function() {
    // 상품관리 페이지에서 노출순서 변경
    $('.p_rank').change(function(){

        var postData = {
            "Product_id" : this.nextSibling.value,
            "p_rank" : this.value
        }
        $.ajax({
                    type: 'POST',
                    url : '/admin/changPrank',
                    data: postData,
                    success: function(data, status, xhr) {

                        location.reload();
                        return;
                    },
                    error: function() {
                        alert("순서변경에 실패하였습니다.");
                        return;
                    }
                });
    });
    // 답변목록에서 답변입력가능
    $('.answerBtn').click(function(){
//    <input class="hiddenId" type="hidden" th:value="${anserList.id}"/>
//    <input class="hiddenUsername" type="hidden" th:value="${anserList.username}"/>
//    <input class="anserReply" type="text" placeholder="답변입력"/>
//    <button class="answerBtn">답변입력</button>


        let anserReply = this.previousSibling.previousSibling;
        let hiddenUsername = this.previousSibling.previousSibling.previousSibling.previousSibling;
        let hiddenId = this.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling;


        var postData = {
            "id" : hiddenId.value,
            "reply" : anserReply.value,
            "username" :  hiddenUsername.value
        }

//        alert("id : "+ hiddenId.value);
//        alert("reply : "+ anserReply.value);
//        alert("username : "+ hiddenUsername.value);



        $.ajax({
                    type: 'POST',
                    url : '/admin/insertAnswer',
                    data: postData,
                    success: function(data, status, xhr) {
//                        alert(":A");
//                        recallReview(data);
                        location.reload();
                        return;
                    },
                    error: function() {
                        alert("답변 등록에 실패 하였습니다");
                        return;
                    }
                });

    });
    $('.btn-primary').click(function(){
        location.reload();
    });
});
//function recallReview (answerList) {
//    const out = [];
//    console.log(answerList)
//    answerList.forEach(anserList =>
//    let row = `
//        <td class="col-2">
//            <div th:unless="${anserList.p_img}" class="image thumbnail">
//                <a th:href="@{'/product/detail/' + ${anserList.product_id}}">
//                    <img th:src="@{/image/default.jpg}" class="img img-responsive full-width img-thumbnail">
//                </a>
//            </div>
//            <div th:if="${anserList.p_img}" class="image thumbnail">
//                <a th:href="@{'/product/detail/' + ${anserList.product_id}}">
//                    <img th:src="@{'/upload/' + ${anserList.p_img}}" class="img img-responsive full-width img-thumbnail">
//                </a>
//            </div>
//        </td>
//        <td class="col-2" th:text="${anserList.p_name}"></td>
//        <td class="col-4" th:text="${anserList.content}"></td>
//        <td class="col-4" th:if="${anserList.reply}==''">
//            <input id="hiddenId" type="hidden" th:value="${anserList.id}"/>
//            <input id="hiddenUsername" type="hidden" th:value="${anserList.username}"/>
//            <input id="anserReply" type="text" placeholder="답변입력"/><button id="answerBtn">답변입력</button>
//        </td>
//        <td class="col-4" th:if="${anserList.reply}!=''" th:text="${anserList.reply}"></td>
//        <td class="col-2" th:text="${anserList.reviewdate}"></td>
//        <td class="col-2" th:text="${anserList.replydate}"></td>`
//
//        out.push(row);
//    );
//
//    $('#answer_tbody').html(out.join());
//}
//
//
