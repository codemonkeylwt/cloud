package com.cloud.user.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author lwt
 * @date 2018/8/8 13:30
 */
@Configuration
@PropertySource(value = "classpath:application-data.yml")
public class DataSourceConf {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruid(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setMaxActive(2);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }
}
