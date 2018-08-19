package org.king.crimson.security;

import java.util.Collections;
import org.hibernate.SessionFactory;
import org.king.crimson.dao.security.RoleDAO;
import org.king.crimson.dao.security.UserDAO;
import org.king.crimson.model.security.Role;
import org.king.crimson.model.security.RoleName;
import org.king.crimson.model.security.User;
import org.king.crimson.zelpers.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public User register(SignUpRequest request) {

        if (userDAO.findByUsernameOrEmail(request.getEmail()) != null) {
            throw new AppException("Username is already taken");
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
