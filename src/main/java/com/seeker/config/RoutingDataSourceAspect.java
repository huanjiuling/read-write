package com.seeker.config;

import com.seeker.enums.DataSourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ：悬崖上的列车(Jiuling Huan)
 * @date ：Created in 2025/2/27
 * @slogan: 莫听穿林打叶声 何妨吟啸且徐行 竹杖芒鞋轻胜马 一蓑烟雨任平生
 * @email: 514082870@qq.com
 * @desc:
 **/
@Component
@Aspect
@Slf4j
public class RoutingDataSourceAspect {

    /**
     * master主库写库AOP切入点
     */
    @Pointcut("execution(* com.seeker.mapper..*.insert*(..)) "
            + "|| execution(* com.seeker.mapper..*.save*(..))"
            + "|| execution(* com.seeker.mapper..*.update*(..))"
            + "|| execution(* com.seeker.mapper..*.delete*(..))"
            + "|| execution(* com.seeker.mapper..*.reomove*(..))")
    private void masterDataSourcePointcut() {
        log.info("切换到master主数据源");
    }

    /**
     * slave数据库读库服务AOP切入点
     */
    @Pointcut("execution(* com.seeker.mapper.ImMessageMapper.select*(..))")
    private void slaveDataSourcePointcut() {
        log.info("切换到slave从数据源");
    }

    /**
     * 切换master数据源
     */
    @Before("masterDataSourcePointcut()")
    public void master() {
        DbContextHandler.setDbType(DataSourceTypeEnum.MASTER);
    }

    /**
     * 返回后清除master数据源
     */
    @AfterReturning("masterDataSourcePointcut()")
    public void masterClear() {
        DbContextHandler.clearDbType();
    }

    /**
     * 异常时清除master数据源
     */
    @AfterThrowing("masterDataSourcePointcut()")
    public void masterExceptionClear() {

        DbContextHandler.clearDbType();
    }

    /**
     * 切换slave数据源
     */
    @Before("slaveDataSourcePointcut()")
    public void slave() {

        DbContextHandler.setDbType(DataSourceTypeEnum.SLAVE);
    }

    /**
     * 返回后清除slave数据源
     */
    @AfterReturning("slaveDataSourcePointcut()")
    public void slaveClear() {

        DbContextHandler.clearDbType();
    }

    /**
     * 异常时清除slave数据源
     */
    @AfterThrowing("slaveDataSourcePointcut()")
    public void slaveExceptionClear() {

        DbContextHandler.clearDbType();
    }



}
