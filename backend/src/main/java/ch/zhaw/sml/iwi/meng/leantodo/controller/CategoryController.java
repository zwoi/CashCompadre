package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Expense;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class CategoryController {
    private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories(String loginName) {
        return userRepository.findById(loginName).get().getCategories();
    }

    public void addExpense(String loginName, Long categoryId, Expense expense) {
        LOGGER.log(Level.INFO, "User " + loginName + " is adding expesnes to category " + categoryId + ": " + expense);
        User user = userRepository.findById(loginName).get();
        // Check if category is owned by user
        Category category = null;
        for (Category c : user.getCategories()) {
            if (c.getId() == categoryId) {
                category = c;
            }
        }
        if (category == null) {
            LOGGER.log(Level.WARNING, "User " + loginName + " tried to add expenses to another user's category");
            throw new IllegalArgumentException("This category does not belong to the user.");
        } else {
            expense.setId(null);
            expense.setDone(LocalDate.now());
            category.getExpenses().add(expense);
            categoryRepository.save(category);
        }
    }

    public void deleteExpense(String loginName, Long categoryId, Long expenseId) {
        LOGGER.log(Level.INFO,
                "User " + loginName + " is deleting expense " + expenseId + " from category " + categoryId);
        User user = userRepository.findById(loginName).get();
        // Check if category is owned by user
        Category category = null;
        for (Category c : user.getCategories()) {
            if (c.getId() == categoryId) {
                category = c;
            }
        }
        // Check if expense exists in the category
        Expense expense = category.getExpenses().stream()
                .filter(e -> e.getId().equals(expenseId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expense not found in the category."));
        // Remove the expense and save the category
        category.getExpenses().remove(expense);
        categoryRepository.save(category);
    }

}
