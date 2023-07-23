$(function(){
    const addAddressBtn = document.getElementById("addAddressBtn");
    const addressFields = document.getElementById("addressContainer");

        addAddressBtn.addEventListener("click", () => {

            if(addressCount < 3){

                const newAddressFields = createAddressFields();

                addressContainer.appendChild(newAddressFields);

                addressCount ++;
                num ++;
            }

            if(addressCount >= 3){
                addAddressBtn.style.display= "none";
            }
        });

        function createAddressFields(){
            const addressFields = document.createElement('div');
            addressFields.classList.add('box');
            addressFields.innerHTML = `
            <br>
            <input type="text" class="sample6" id="sample6_postcode" placeholder="우편번호" name="addresses[${num}].postcode" required>
            <input type="button" class="sample6" id="sample6_btn" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" class="sample6" id="sample6_address" placeholder="주소" name="addresses[${num}].address" required><br>
            <input type="text" class="sample6" id="sample6_detailAddress" placeholder="상세주소" name="addresses[${num}].address_detail" required>
            <input type="text" class="sample6" id="sample6_extraAddress" placeholder="주소명" name="addresses[${num}].address_name"> <br>
            <input type="button" class="sample6" id="delbtn" onclick="deleteAddressContainer(this)" value="삭제"> <br>
            `;
            return addressFields;
        }


})
