package com.rebecana.blog.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleVo {

    private Long id;

    private String name;

    private List<Long> permission;

}