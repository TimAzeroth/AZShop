$(function(){
      function validateEmail() {
        const emailInput = document.getElementById('email');
        const msg = document.getElementById('msg');
        const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
        const Button = document.querySelector(".btn-success");

        if (!emailPattern.test(emailInput.value)) {
          msg.innerText = "이메일 형식이 맞지 않습니다. 올바른 형식의 이메일을 적어주세요.";
          msg.style.color = "red";
          return false;
        } else {
          msg.innerText = "";
          return true;
        }
      }

      // Attach the validateEmail function to the email input's onchange event
      document.getElementById('email').addEventListener('change', validateEmail);
})