package com.rebecana.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ms_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 是否管理员
     */
    private Boolean admin;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Long createDate;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登录时间
     */
    private Long lastLogin;

    /**
     * 手机号
     */
    private String mobilePhoneNumber;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 状态
     */
    private String status;


}
