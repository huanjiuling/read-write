package com.seeker.enums;

/**
 * @author ：悬崖上的列车(Jiuling Huan)
 * @date ：Created in 2025/2/27
 * @slogan: 莫听穿林打叶声 何妨吟啸且徐行 竹杖芒鞋轻胜马 一蓑烟雨任平生
 * @email: 514082870@qq.com
 * @desc:
 **/
public enum DataSourceTypeEnum {

    /**
     * mysqlMaster主库
     */
    MASTER("master"),

    /**
     * slave从库
     */
    SLAVE("slave");

    /**
     * 数据库类型
     */
    private String type;

    /**
     * 构造函数
     */
    DataSourceTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 数据库类型
     *
     * @return type
     */
    public String getType() {
        return type;
    }

}
