package com.rebecana.blog.dao.pojo;


import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author zdy
 * @since 2022-03-30
 */

@Data
public class ArticleBody{

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;

}
