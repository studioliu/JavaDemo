CREATE DATABASE SMSdb;
USE SMSdb;
CREATE TABLE goods(
    gid   varchar(255),
    cid   varchar(255),
    name  varchar(255),
    price decimal(10, 2),
    unit  varchar(255)
);
INSERT INTO goods VALUES ('10001001', '9001', '苹果', 2.68, '斤');
INSERT INTO goods VALUES ('10001002', '9001', '橘子', 2.98, '斤');
INSERT INTO goods VALUES ('10001003', '9002', '可乐', 3.00, '瓶');
INSERT INTO goods VALUES ('10001004', '9001', '香蕉', 3.20, '斤');