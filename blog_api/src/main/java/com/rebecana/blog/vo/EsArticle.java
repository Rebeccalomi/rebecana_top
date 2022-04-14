package com.rebecana.blog.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Document(indexName = "article",createIndex = true)
public class EsArticle {
    @Id
    @Field(type = FieldType.Text)
    @JsonSerialize(using = ToStringSerializer.class)  //雪花算法会丢失精度
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text,analyzer = "ik_smart")
    private String content;

    public EsArticle(String id,String title,String content) {
        this.id =id;
        this.title=title;
        this.content=content;
    }
}
