package com.dapeng.demo.config;

import com.dapeng.demo.enums.DataSourceEnum;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("test1DataSource") DataSource test1DataSource,
                                        @Qualifier("test2DataSource") DataSource test2DataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceEnum.DB1.getValue(), test1DataSource);
        targetDataSource.put(DataSourceEnum.DB2.getValue(), test2DataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(test1DataSource);
        return dataSource;
    }

    //    @Bean(name = "SqlSessionFactory")
//    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dynamicDataSource);
////        bean.setMapperLocations(
////                new PathMatchingResourcePatternResolver().getResources("classpath:/mapping/*.xml"));
//        return bean.getObject();
//    }
    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        try {

            // 实例SessionFactory
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 配置数据源
            sqlSessionFactoryBean.setDataSource(DataSource(getDateSource1(),getDateSource2()));


            // 加载MyBatis配置文件
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:/mapper/*Mapper.xml"));
            // 配置mybatis的config文件(我目前用不上)
            // sqlSessionFactoryBean.setConfigLocation("mybatis-config.xml");
        } catch (Exception e) {
            System.out.println("创建SqlSession连接工厂错误：{}");
            System.out.println(e);
        }

        return sqlSessionFactoryBean;
    }
}
