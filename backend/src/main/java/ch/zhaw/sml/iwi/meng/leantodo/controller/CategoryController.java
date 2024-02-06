package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class CategoryController {

    @Autowired
    private UserRepository userRepository;

    public List<Category> listAllCategories(String loginName) {
        return userRepository.findById(loginName).get().getCategories();
    }

}
