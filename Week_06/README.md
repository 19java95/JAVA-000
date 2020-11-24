# 选做

### 给订单表增加100万条数据 

1.创建存储过程

```sql
CREATE DEFINER=`root`@`%` PROCEDURE `add_vote_record_memory`(IN n INT)
BEGIN
	DECLARE i  INT DEFAULT 1;
    DECLARE rand_num INT DEFAULT 0;
    DECLARE statusnum TINYINT DEFAULT 1;
    DECLARE payment decimal(20,2) DEFAULT 1.00;
    DECLARE postage INT DEFAULT 1;
    DECLARE statu TINYINT DEFAULT 1;
    WHILE i < n DO
        SET rand_num = FLOOR(0 + RAND()*3);
        SET statusnum = FLOOR(1 + RAND()*2);
        set payment =  FLOOR(0 + RAND()*999)/100;
        set postage =  FLOOR(0 + RAND()*999);
		SET statu = FLOOR(0 + RAND()*6);
        INSERT INTO `order_memory` VALUES (i, i, rand_num, NULL, payment,statusnum,postage,statu,now(),now(),now(),now(),now(),now());
        SET i = i + 1;
    END WHILE;
END
```

