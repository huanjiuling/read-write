package com.seeker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * im_message
 * @author
 */
@Data
public class ImMessage implements Serializable {
    private Integer id;

    /**
     * 消息
     */
    private String message;

    /**
     * 1已读 2未读
     */
    private String status;

    /**
     * 发送者
     */
    private Integer senderId;

    /**
     * 接收者
     */
    private Integer receiver;

    private String isDel;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
