package com.yucheng.estm.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis及分页插件配置
 *
 * @Author liukw 20191019
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        //添加配置，也可以指定文件路径
        Properties props = new Properties();
        props.setProperty("helperDialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("params", "count=countSql");

        pageHelper.setProperties(props);
        return pageHelper;
    }
}
