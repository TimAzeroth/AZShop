function test() {
      var p1 = document.getElementById('password').value;
      var p2 = document.getElementById('re_password').value;
          if( p1 != '' && p2 != '') {
            if(p1 == p2) {
                document.getElementById('msg').innerHTML='비밀번호가 일치합니다'
                document.getElementById('msg').style.color='blue';
                document.getElementById('btn').disabled=false;
            } else {
                document.getElementById('msg').innerHTML='비밀번호가 일치하지 않습니다'
                document.getElementById('msg').style.color='red';
                document.getElementById('btn').disabled=true;
            }
          }

    }