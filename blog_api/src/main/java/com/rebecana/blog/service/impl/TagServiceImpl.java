package com.rebecana.blog.service.impl;

import com.rebecana.blog.dao.pojo.Tag;
import com.rebecana.blog.dao.mapper.TagMapper;
import com.rebecana.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }
    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus 无法进行多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit){
        /**
         * 标签所拥有对文章数量最多
         * 查询 根据最热tag_id进行分组计数。从大到小排列，取前limit个
         */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        //需求tagid和tagname tag对象
        List<Tag> taglist=tagMapper.findTagsByTagIds(tagIds);
        return Result.success(taglist);
    }

}
