package com.rebecana.blog.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

    private Long id;
    private String title;
    private String summary;
    private Integer commentCounts;
    private Integer viewCounts;
    private Integer weight;

    private String createDate;
    private String author;
    private Long bodyId;
    private Integer categoryId;
    private List<TagVo> tags;
}
