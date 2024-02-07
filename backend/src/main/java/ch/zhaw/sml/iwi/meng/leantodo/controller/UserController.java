package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.ClassName;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Role;
import ch.zhaw.sml.iwi.meng.leantodo.entity.RoleRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

import java.util.logging.Logger;

@Component
public class UserController {
private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    

    public void addNewUser(String username, String password) {
        User user = new User();
        user.setLoginName(username);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));

        user.getCategories().add(new Category("Essen", 600));
        user.getCategories().add(new Category("Freizeit", 300));
        user.getCategories().add(new Category("Wohnen", 100));
        user.getCategories().add(new Category("Sonstiges", 600));

        Role r = new Role();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);
        user.getRoles().add(r);
        userRepository.save(user);

        user.setBalance(0);

    }

    public void updateBalance(String loginName, Integer updatedBalance) {
        LOGGER.log(Level.INFO,
                "User " + loginName + " is updating their balance to" + updatedBalance);
        User user = userRepository.findById(loginName).get();
        
        // Update the balance with updatedBalance
        user.setBalance(updatedBalance);
        // Save the category with the updated expense
        userRepository.save(user);
    }

    public Integer getBalance(String loginName) {
        return userRepository.findById(loginName).get().getBalance();
    }

  

}
