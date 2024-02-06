package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.UserController;
import ch.zhaw.sml.iwi.meng.leantodo.dto.NewUserDTO;

@RestController
@CrossOrigin
public class UserEndpoint {

    @Autowired
    private UserController userController;

    @RequestMapping(path = "/api/me", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public String me(Principal principal) {

        return "{\"user\": \"" + principal.getName() + "\"} ";
    }

    @RequestMapping(path = "/auth/user", method = RequestMethod.POST, produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> addNewUser(@RequestBody NewUserDTO newUser, Principal principal) {

        userController.addNewUser(newUser.getLoginName(), newUser.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}