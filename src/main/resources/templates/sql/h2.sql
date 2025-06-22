//创建表
drop table if exists emp;
CREATE TABLE IF NOT EXISTS emp (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    pwd varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

//插入数据
INSERT INTO emp VALUES (NULL,'zhangsan' ,'123456' );
INSERT INTO emp VALUES (NULL,'lisi' ,'123456' );
INSERT INTO emp VALUES (NULL,'wagnwu' ,'123456' );

//查询所有数据
SELECT * FROM emp;

//删除 lisi
DELETE FROM emp WHERE name = 'lisi';
//查询所有数据
SELECT * FROM emp;

//删除表
DROP TABLE IF EXISTS emp;