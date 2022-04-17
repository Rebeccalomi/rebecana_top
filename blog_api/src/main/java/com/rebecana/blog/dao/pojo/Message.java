package com.rebecana.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zdy
 * @since 2022-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ms_message")
public class Message implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String avatar;

    private String msg;

    private Integer time;


}
