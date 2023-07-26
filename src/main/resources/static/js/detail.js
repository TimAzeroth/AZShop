$(function() {
    const increaseButton = document.getElementById('increaseButton');
    const decreaseButton = document.getElementById('decreaseButton');
    const buyButton = document.getElementById('buybutton');
    const cartButton = document.getElementById('cartbutton');
    const error = document.getElementById('error');
    const countInput = document.getElementById('countInput');
    const amount = document.getElementById('amount');

    let count = 1;

    if(count > stock) {
        error.classList.remove("d-none");
    }

    increaseButton.addEventListener('click', () => {
      count++;
      countInput.value = count;
      amount.value = count;
      if(count > stock) {
          error.classList.remove("d-none");
          cartButton.setAttribute('disabled', 'disabled');
          buyButton.setAttribute('disabled', 'disabled');
      }
    });

    decreaseButton.addEventListener('click', () => {
      if(count == 1) {
        return;
      }
      count--;
      countInput.value = count;
      amount.value = count;
      if((count == stock || count < stock)) {
          error.classList.add("d-none");
          cartButton.removeAttribute('disabled');
          buyButton.removeAttribute('disabled');
      }
    });

    $('#cartbutton').click(function(){
        parseInt(amount.value);
        $("form[name='cartAdd']").submit();
    });

    // 삭제 버튼
    $('#delete').click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer) {
            $("form[name='frmDelete']").submit();
        }
    });
});