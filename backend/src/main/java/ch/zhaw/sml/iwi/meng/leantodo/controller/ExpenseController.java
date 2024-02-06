package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Expense;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ExpenseRepository;

@Component
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> listAllToDos(String loginName) {
        return expenseRepository.findByAmount(loginName);
    }
}
