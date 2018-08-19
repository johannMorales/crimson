package org.king.crimson.security;

import java.util.Collections;
import org.king.crimson.dao.security.RoleDAO;
import org.king.crimson.dao.security.UserDAO;
import org.king.crimson.model.security.Role;
import org.king.crimson.model.security.RoleName;
import org.king.crimson.model.security.User;
import org.king.crimson.zelpers.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;
    
    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public User signup(SignUpRequest request) {

        if(userDAO.findByUsername(request.getUsername()) != null){
            throw new AppException("Username is already taken");
        }
        
        if(userDAO.findByEmail(request.getEmail()) != null){
            throw new AppException("Email is already taken");
        }
        
        User user = new User(request.getName(), request.getUsername(),
                request.getEmail(), request.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleDAO.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        
        userDAO.save(user);

        return user;
    }

}
