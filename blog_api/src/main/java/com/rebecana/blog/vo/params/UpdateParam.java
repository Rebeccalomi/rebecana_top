package com.rebecana.blog.vo.params;

import lombok.Data;

@Data
public class UpdateParam {
    private String account;

    private String password;

    private String passwordnew;

    private String nickname;

    private String avatar;
}
