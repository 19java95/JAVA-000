package top.tangtian.geek_week0702.config;

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
 * @className MasterMybatisConfig
 * @description
 * @date 2020/11/30 7:25 PM
 **/
@Configuration
@MapperScan(basePackages = "top.tangtian.geek_week0702.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterMybatisConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MasterMybatisConfig.class);
    /**
     * 注意，此处需要使用MybatisSqlSessionFactoryBean，不是SqlSessionFactoryBean，
     * 否则，使用mybatis-plus的内置函数时就会报invalid bound statement (not found)异常
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master") DataSource dataSource) throws Exception {
        //设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mapper的xml文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/master/*.xml"));
        //对应数据库的entity位置
        String typleAliasesPackage = "top.tangtian.geek_week0702.entity.master";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typleAliasesPackage);
        return mybatisSqlSessionFactoryBean.getObject();
    }
}
