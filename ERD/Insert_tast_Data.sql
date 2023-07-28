DELETE FROM Address;
ALTER TABLE Address AUTO_INCREMENT = 1;
DELETE FROM Cart;
ALTER TABLE Cart AUTO_INCREMENT = 1;
DELETE FROM Review;
ALTER TABLE Review AUTO_INCREMENT = 1;
DELETE FROM User;
ALTER TABLE User AUTO_INCREMENT = 1;
DELETE FROM Authority;
ALTER TABLE Authority AUTO_INCREMENT = 1;
DELETE FROM Card;
ALTER TABLE Card AUTO_INCREMENT = 1;
DELETE FROM Category;
ALTER TABLE Category AUTO_INCREMENT = 1;
DELETE FROM Product;
ALTER TABLE Product AUTO_INCREMENT = 1;
DELETE FROM Sales;
ALTER TABLE Sales AUTO_INCREMENT = 1;


-- 권한테이블 
INSERT INTO Authority(name) VALUES
('ROLE_MEMBER'),
('ROLE_ADMIN')
;


       



-- 카테고리 리스트
INSERT INTO Category(maincode,subcode,mainname,subname) VALUES
(1, 1, '패션의류', '여성의류'),
(1, 2, '패션의류', '남성의류'),
(1, 3, '패션의류', '남여공용'),
(1, 4, '패션의류', '신발'),
(2, 1, '식품', '과일'),
(2, 2, '식품', '채소'),
(2, 3, '식품', '쌀/잡곡'),
(2, 4, '식품', '냉동/간편'),
(3, 1, '주방용품', '냄비/프라이팬'),
(3, 2, '주방용품', '그릇/접시'),
(3, 3, '주방용품', '수저/젖가락'),
(4, 1, '유아용품', '기저귀'),
(4, 2, '유아용품', '분유'),
(4, 3, '유아용품', '매트/안전용품'),
(4, 4, '유아용품', '유모차'),
(5, 1, '가전/디지털', '냉장고'),
(5, 2, '가전/디지털', '세탁기'),
(5, 3, '가전/디지털', '청소기'),
(5, 4, '가전/디지털', '에어컨'),
(5, 5, '가전/디지털', 'TV/컴퓨터'),
(6, 1, '반려동물용품', '강아지사료'),
(6, 2, '반려동물용품', '강아지간식'),
(6, 3, '반려동물용품', '고양이사료'),
(6, 4, '반려동물용품', '고양이간식'),
(6, 5, '반려동물용품', '장난감')
;

-- 상품목록. ADMIN에서 메인화면에 띄울 상품 픽	
INSERT INTO Product (p_name, main_cate, sub_cate, p_img, detail, price, stock, p_rank) VALUES
('여성의류1', 1,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 5 ),
('여성의류2', 1,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 40000, 5, 3 ),
('여성의류3', 1,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 30000, 0, 2 ),
('남성의류1', 1,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 1 ),
('남성의류2', 1,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 4 ),
('남성의류3', 1,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 6 ),
('과일1', 2,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 5000, 50, 7 ),
('채소1', 2,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 1000, 50, 8 ),
('쌀/잡곡1', 2,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 9 ),
('냉동/간편1', 2,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50, 10 )
;

-- 상품목록. ADMIN에서 메인화면에 띄우는 선택하지 않은 상품
INSERT INTO Product (p_name, main_cate, sub_cate, p_img, detail, price, stock) VALUES
('패션의류더미1', 1,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('패션의류더미2', 1,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('패션의류더미3', 1,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('패션의류더미4', 1,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('식품더미1', 2,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('식품더미2', 2,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('식품더미3', 2,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('식품더미4', 2,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('주방용품더미1', 3,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('주방용품더미2', 3,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('주방용품더미3', 3,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('유아용품더미1', 4,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('유아용품더미2', 4,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('유아용품더미3', 4,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('유아용품더미4', 4,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('가전/디지털더미1', 5,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('가전/디지털더미2', 5,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('가전/디지털더미3', 5,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('가전/디지털더미4', 5,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('가전/디지털더미5', 5,5,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('반려동물용품더미1', 6,1,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('반려동물용품더미2', 6,2,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('반려동물용품더미3', 6,3,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('반려동물용품더미4', 6,4,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50),
('반려동물용품더미5', 6,5,'face01.png', '상품상세설명입니다~에디터이용하여 수정', 50000, 50)
;

-- 사용자 등록
insert into User(authority_id, username, password, nickname, email,	profileimg, u_status, phone ) VALUES
(1, 'user2', '1234', '사용자2', 'user2@gmail.com', 'face01.png', 'USE', '01023451234'),
(1, 'user3', '1234', '사용자3', 'user3@gmail.com', 'face01.png', 'USE', '01034561234'),
(1, 'user4', '1234', '사용자4', 'user4@gmail.com', 'face01.png', 'USE', '01045671234'),
(2, 'admin1', '1234', '사용자4', 'admin1@gmail.com', 'face01.png', 'USE', '01057891234')
;

-- 4명의 사용자 주소 목록 
INSERT INTO Address(user_id, address_name, address,	address_detail,	postcode) VALUES
(1, '유저1집', '서울시 종로구 테헤란로 56길', '234-5번지', '12345'),
(1, '유저1회사', '서울시 종로구 테헤란로 57길', '234-6번지', '23456'),
(1, '유저1부모님댁', '서울시 종로구 테헤란로 58길', '234-7번지', '34567'),
(2, '유저2집', '서울시 종로구 테헤란로 56길', '234-5번지', '12345'),
(2, '유저2회사', '서울시 종로구 테헤란로 57길', '234-6번지', '23456'),
(2, '유저2부모님댁', '서울시 종로구 테헤란로 58길', '234-7번지', '34567'),
(3, '유저3집', '서울시 종로구 테헤란로 56길', '234-5번지', '12345'),
(3, '유저3회사', '서울시 종로구 테헤란로 57길', '234-6번지', '23456'),
(3, '유저3부모님댁', '서울시 종로구 테헤란로 58길', '234-7번지', '34567'),
(4, '유저4집', '서울시 종로구 테헤란로 56길', '234-5번지', '12345'),
(4, '유저4회사', '서울시 종로구 테헤란로 57길', '234-6번지', '23456'),
(4, '유저4부모님댁', '서울시 종로구 테헤란로 58길', '234-7번지', '34567')
;


-- 리뷰 데이터
insert into Review (user_id , product_id,  content, reply) values
(1, 1, '유저1의 댓글내용입니다1', ''),
(1, 2, '유저1의 댓글내용입니다2', '답변내용입니다2'),
(1, 3, '유저1의 댓글내용입니다3', '답변내용입니다3'),
(1, 4, '유저1의 댓글내용입니다4', '답변내용입니다4'),
(2, 1, '유저2의 댓글내용입니다1', '답변내용입니다1'),
(2, 2, '유저2의 댓글내용입니다2', ''),
(2, 3, '유저2의 댓글내용입니다3', '답변내용입니다3'),
(2, 4, '유저2의 댓글내용입니다4', '답변내용입니다4'),
(3, 1, '유저3의 댓글내용입니다1', '답변내용입니다1'),
(3, 2, '유저3의 댓글내용입니다2', '답변내용입니다2'),
(3, 3, '유저3의 댓글내용입니다3', ''),
(3, 4, '유저3의 댓글내용입니다4', '답변내용입니다4')
;

-- 판매등록. 송장번호 입력완료
insert into Sales(u_username, p_id, amount,address,address_detail,postcode,deliveryreq,tracknum) values
('user1', 12, 5,'서울시 종로구 테헤란로 56길', '234-5번지', '12345', '빠른배달부탁드립니다', '123456789101112'),
('user1', 13, 5,'서울시 종로구 테헤란로 56길', '234-5번지', '12345', '빠른배달부탁드립니다', '123456789101112'),
('user3', 6, 1,'서울시 종로구 테헤란로 58길', '234-7번지', '34567', '부모님선물용입니다. 파손주의해주세요.', '123456789101112')
;
-- 판매등록. 송장번호 미입력
insert into Sales(u_username, p_id, amount,address,address_detail,postcode,deliveryreq) values
('user2', 12, 5,'서울시 종로구 테헤란로 57길', '234-6번지', '23456', '사무용품입니다'),
('user4', 6, 1,'서울시 종로구 테헤란로 56길', '234-5번지', '12345', '몰래배달해주세요.')
;


-- 임시 카드정보 입력
INSERT INTO Card (c_num, c_year, c_month, c_cvc, balance) VALUES
('1111222233334444', '25','07', '123', 590000),
('2222333344445555', '26','08', '123', 450000),
('1234123412341234', '27','09', '123', 3000),
('1212121212121212', '28','10', '123', 0)
;