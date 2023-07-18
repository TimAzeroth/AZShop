<script>
    document.addEventListener("DOMContentLoaded", function(){
        function resizeImage(event) {
            const maxWidth = 200; // 변경할 너비
            const maxHeight = 200; // 변경할 높이

            const file = event.target.files[0];
            const img = document.createElement('img');
            const reader = new FileReader();

            reader.onload = function(e) {
                img.onload = function() {
                    const canvas = document.createElement('canvas');
                    const ctx = canvas.getContext('2d');

                    let width = img.width;
                    let height = img.height;

                    if (width > height) {
                        if (width > maxWidth) {
                            height *= maxWidth / width;
                            width = maxWidth;
                        }
                    } else {
                        if (height > maxHeight) {
                            width *= maxHeight / height;
                            height = maxHeight;
                        }
                    }

                    canvas.width = width;
                    canvas.height = height;
                    ctx.drawImage(img, 0, 0, width, height);

                    const resizedImage = canvas.toDataURL('image/jpeg');

                    let preview = document.querySelector("#profileimg_preview");
                    preview.setAttribute("src", resizedImage);
                };

                img.src = e.target.result;
            };

            reader.readAsDataURL(file);
        }

        const profileimgInput = document.querySelector("#profileimg");
        if(profileimgInput) {
            profileimgInput.addEventListener("change", resizeImage);
        }
    });
</script>