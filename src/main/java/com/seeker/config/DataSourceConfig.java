package com.seeker.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.seeker.enums.DataSourceTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：悬崖上的列车(Jiuling Huan)
 * @date ：Created in 2025/2/27
 * @slogan: 莫听穿林打叶声 何妨吟啸且徐行 竹杖芒鞋轻胜马 一蓑烟雨任平生
 * @email: 514082870@qq.com
 * @desc:
 **/
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * <p/>
     * "@DependsOn({"masterDataSource", "slaveDataSource"})" 解决循环依赖的问题
     *
     * @return 动态数据源
     */
    @Primary
    @Bean("dynamicDataSource")
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceTypeEnum.MASTER.getType(), masterDataSource());
        targetDataSources.put(DataSourceTypeEnum.SLAVE.getType(), slaveDataSource());
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * 定义事务管理策略
     * @return 事务源
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
