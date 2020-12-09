package top.tangtian.geek_week0802;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tangtian
 * @version 1.0
 * @className DatasourceApplication
 * @description
 * @date 2020/12/1 7:28 AM
 **/
@SpringBootApplication
@MapperScan("top.tangtian.geek_week0801.mapper")
public class DatasourceApplication {
    private static final Logger LOG = LoggerFactory.getLogger(DatasourceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DatasourceApplication.class, args);
    }
}
