

select username, email, phone from user
-- where username = ""
;


select 
	u.username,
	u.nickname,
	u.email,
	u.phone,
	a.address_detail,
	a.address,
	a.postcode,
	
from 
	address a, 
	`user` u,
	product p 
where u.id = a.user_id
and u.username = "";



SELECT id, authority_id, username, password, nickname, email, profileimg, u_status, phone, logdate, regdate
FROM azerothdb.`user`;


SELECT id, user_id, address_name, address, address_detail, postcode
FROM azerothdb.address;


SELECT id, p_name, main_cate, sub_cate, p_img, detail, price, stock, p_rank
FROM azerothdb.product;
