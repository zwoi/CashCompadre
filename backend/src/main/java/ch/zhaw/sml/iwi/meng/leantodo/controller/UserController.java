package ch.zhaw.sml.iwi.meng.leantodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Role;
import ch.zhaw.sml.iwi.meng.leantodo.entity.RoleRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class UserController {

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

    }
}
