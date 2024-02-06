package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryRepository;

@Component
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories(String loginName) {
        return categoryRepository.findByName(loginName);
    }

}
