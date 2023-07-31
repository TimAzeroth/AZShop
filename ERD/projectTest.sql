

select username, email, phone from user
-- where username = ""
;


-- select 
-- 	u.nickname,
-- 	a.address_detail,
-- 	a.address,
-- 	u.phone
-- from 
-- 	address a, 
-- 	username u
-- where u.id = a.user_id
-- and u.username = ""




select
	u.username,
	u.nickname,
	u.email,
	u.phone,
	a.address_detail,
	a.address,
	a.postcode,
	p.id
from
	address a,
	`user` u,
	product p ;



SELECT id, authority_id, username, password,
nickname, email, profileimg, u_status, phone,
logdate, regdate
FROM azerothdb.`user`;


SELECT id, user_id, address_name, address, address_detail, postcode
FROM azerothdb.address;


SELECT id, p_name, main_cate, sub_cate, p_img, detail, price, stock, p_rank
FROM azerothdb.product;


SELECT id, u_username, p_id, amount, address, address_detail, postcode, deliveryreq, tracknum
FROM azerothdb.sales;

SELECT a.id, a.name
FROM authority a, USER u
WHERE a.id = u.authority_id AND u.id = 5;

SELECT * FROM address 
WHERE user_id = 12;

INSERT INTO Address (user_id, address_name, address, address_detail, postcode)
VALUES  (12, '우리집', '서울시 강동구 아리수로 97길 20', '강일리버파크5단지 505동 806호', '50920') ;

SELECT * FROM Cart;

        SELECT c.product_id, c.amount, p.p_img, p.p_name, p.price
        FROM Cart c
        LEFT JOIN Product p
        ON c.product_id = p.id
        WHERE c.user_id = 6;
       
       
       
INSERT INTO Sales (u_username, p_id, amount, address, address_detail, postcode, deliveryreq, tracknum)
VALUES ('fnrrlfnrrl3', 9, 2, '강동구 아리수로 97길 20', '505동 806호', '05209', '문앞에놔주세요', '123456789'),
	 ('fnrrlfnrrl3', 3, 1, '강동구 아리수로 97길 20', '505동 806호', '05209', '문앞에놔주세요', '123845789');
	
	
SELECT
    u_username,
    p_id,
    amount,
    address,
    address_detail,
    postcode,
    deliveryreq,
    regdate
FROM Sales
WHERE u_username = 'fnrrlfnrrl3';

SELECT c.id, c.product_id, c.amount, p.p_img, p.p_name, p.price, p.stock
        FROM Cart c
        LEFT JOIN Product p
        ON c.product_id = p.id
        WHERE c.user_id = 'fnrrlfnrrl3';
       
       UPDATE `user`  SET authority_id = 2
       WHERE username = 'fnrrlfnrrl3';
        
SELECT s.id,
	   s.u_username,
	   s.p_id,
	   s.amount,
	   s.address,
	   s.address_detail,
	   s.postcode,
	   s.deliveryreq,
	   s.regdate,
	   p.p_img,
	   p.p_name,
	   p.price
FROM Sales s
LEFT JOIN product p
ON s.p_id = p.id
WHERE s.u_username = 'fnrrlfnrrl3'
ORDER BY s.regdate DESC;
       
SELECT count('id') FROM sales WHERE u_username='fnrrlfnrrl3';

SELECT s.id,
        s.u_username,
        s.p_id,
        s.amount,
        s.address,
        s.address_detail,
        s.postcode,
        s.deliveryreq,
        s.regdate,
        p.p_img,
        p.p_name,
        p.price
        FROM Sales s
        LEFT JOIN product p
        ON s.p_id = p.id
        WHERE s.u_username = 'fnrrlfnrrl3'
        ORDER BY s.regdate DESC
        Limit 0,5;
       
       UPDATE USER SET u_status = 'SLEEP' WHERE username = 'master';

DELETE FROM azerothdb.`user`
WHERE username='gusdl0718';



