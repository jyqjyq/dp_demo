package com.dapeng.demo.aop;

import com.dapeng.demo.annotation.DataSource;
import com.dapeng.demo.config.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut("@within(com.dapeng.demo.annotation.DataSource) || @annotation(com.dapeng.demo.annotation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        log.info("选择数据源---"+ dataSource.value().getValue());
//        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
        DataSourceType.setDataBaseType(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceType.clearDataBaseType();
    }
}
