package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}
