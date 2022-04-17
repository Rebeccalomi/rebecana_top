package com.rebecana.blog.vo;

import lombok.Data;

@Data
public class MessageVo {
    private String nickname;

    private String avatar;

    private String msg;

    private Integer time;
}
