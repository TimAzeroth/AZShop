INSERT INTO Authority (name)
VALUES ('User'),('Admin');

INSERT INTO review (user_id, product_id, content, reviewdate, reply, replydate) VALUES 
(1, 1, 'Great product', NOW(), 'Impressive!', NOW());

SELECT * FROM review r  WHERE id;
SELECT * FROM product p  WHERE id;
SELECT * FROM `user` u WHERE id;
SELECT * FROM authority a  WHERE id;

