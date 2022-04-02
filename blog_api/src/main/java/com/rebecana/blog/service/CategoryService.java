package com.rebecana.blog.service;

import com.rebecana.blog.vo.CategoryVo;
import com.rebecana.blog.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long id);

    Result findAll();

    Result findAllDetail();

    Result categoriesDetailById(Long id);
}
