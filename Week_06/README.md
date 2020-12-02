# 必做作业

https://github.com/tangtian8/JAVA-000/blob/main/Week_06/examination_mall.sql



# 选做

用存储过程增加数据到内存表，从内存表插入数据到订单表

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

## 尝试对mysql不同引擎测试100万订单数据对增删改查性能

|                | InnoDB | MEMORY | MyISAM |
| -------------- | ------ | ------ | ------ |
| 查询没有用索引 | 1670ms | 1031ms | 1022ms |
| 查询用索引     | 662ms  | 651ms  | 627ms  |
|                |        |        |        |

