package com.rebecana.blog.service;

import com.rebecana.blog.vo.EsArticle;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESArticleRepository extends ElasticsearchRepository<EsArticle, Long> {
    List<EsArticle> findByTitleOrContent(String title, String content);


    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "summary"),
    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits<EsArticle> find(String keyword);

}
