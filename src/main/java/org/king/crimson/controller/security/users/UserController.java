package org.king.crimson.controller.security.users;

import org.king.crimson.model.security.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/as/usuarios")
public class UserController {
    
    @GetMapping("/me")
    public User findMe(){
        User u =new User();
        
        u.setName("hola");
        u.setUsername("superhola");
        u.setPassword("holahola");
        
        return u;
    }
    
}
