package top.tangtian.geek_week0802.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author tangtian
 * @version 1.0
 * @className SlaveMybatisConfig
 * @description
 * @date 2020/11/30 8:04 PM
 **/
@Configuration
@MapperScan(basePackages = "top.tangtian.geek_week0702.mapper.slave", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveMybatisConfig {
    private static final Logger LOG = LoggerFactory.getLogger(SlaveMybatisConfig.class);
    @Bean("slaveSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("slave") DataSource dataSource) throws Exception {
        //设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mapper的xml文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/slave/*.xml"));
        //对应数据库的entity位置
        String typleAliasesPackage = "top.tangtian.geek_week0702.entity.slave";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typleAliasesPackage);
        return mybatisSqlSessionFactoryBean.getObject();
    }
}
