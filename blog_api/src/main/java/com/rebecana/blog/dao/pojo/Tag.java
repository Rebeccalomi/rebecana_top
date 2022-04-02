package com.rebecana.blog.dao.pojo;


import java.io.Serializable;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
@Data
public class Tag implements Serializable {

    private Long id;

    private String avatar;

    private String tagName;


}
