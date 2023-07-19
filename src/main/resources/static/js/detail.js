$(function() {
    const increaseButton = document.getElementById('increaseButton');
    const decreaseButton = document.getElementById('decreaseButton');
    const error = document.getElementById('error');
    const countInput = document.getElementById('countInput');
    let count = 1;

    if(count > stock) {
        error.classList.remove("d-none");
    }

    increaseButton.addEventListener('click', () => {
      count++;
      countInput.value = count;
      if(count > stock) {
          error.classList.remove("d-none");
      }
    });

    decreaseButton.addEventListener('click', () => {
      if(count == 1) {
        return;
      }
      count--;
      countInput.value = count;
      if((count == stock || count < stock)) {
          error.classList.add("d-none");
      }
    });

    // 삭제 버튼
    $('#delete').click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer) {
            $("form[name='frmDelete']").submit();
        }
    });
});