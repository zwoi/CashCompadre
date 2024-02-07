package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.CategoryController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Expense;

@RestController
public class CategoryEndpoint {

    @Autowired
    private CategoryController categoryController;

    @GetMapping("/api/categories")
    public List<Category> getMyCategories(Principal principal) {
        return categoryController.listAllCategories(principal.getName());
    }

    @GetMapping("/api/categories/{name}")
    public Category getMyCategoryByName(@PathVariable String name, Principal principal) {
        return categoryController.getCategoryByNameAndUser(name, principal.getName());
    }

    @PostMapping("/api/categories")
    public ResponseEntity<Category> createCategory(
            Principal principal,
            @RequestBody Category category) {
        try {
            Category createdCategory = categoryController.createCategory(principal.getName(), category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/api/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory,
            Principal principal) {
        try {
            String username = principal.getName();
            categoryController.updateCategory(categoryId, updatedCategory, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/categories/{id}")
    public ResponseEntity<Void> addExpense(
            Principal principal,
            @PathVariable(name = "id") Long categoryId,
            @RequestBody Expense expense) {

        try {
            categoryController.addExpense(principal.getName(), categoryId, expense);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/api/categories/{id}/expenses/{expenseId}")
    public ResponseEntity<Void> deleteExpense(
            Principal principal,
            @PathVariable(name = "id") Long categoryId,
            @PathVariable(name = "expenseId") Long expenseId) {

        try {
            categoryController.deleteExpense(principal.getName(), categoryId, expenseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/api/categories/{id}/expenses/{expenseId}")
    public ResponseEntity<Void> updateExpense(
            Principal principal,
            @PathVariable(name = "id") Long categoryId,
            @PathVariable(name = "expenseId") Long expenseId,
            @RequestBody Expense updatedExpense) {

        try {
            categoryController.updateExpense(principal.getName(), categoryId, expenseId, updatedExpense);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}