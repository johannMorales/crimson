package org.king.crimson.dao.security.hibernate;

import org.king.crimson.dao.security.UserDAO;
import org.king.crimson.model.security.User;
import org.king.crimson.zelpers.dao.EzDAOH;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.Octavia;

@Repository
public class UserDAOH extends EzDAOH<User> implements UserDAO {
    
     public UserDAOH() {
        super();
        setClazz(User.class);
    }

    @Override
    public User findByUsernameOrEmail(String usernameOrEmail) {
             Octavia sql = Octavia.query(User.class)
                .beginBlock()
                .__().filter("username", usernameOrEmail)
                .__().filter("email", usernameOrEmail)
                .endBlock();
        
        return find(sql);
    }

    @Override
    public User findByUsername(String username) {
             Octavia sql = Octavia.query(User.class)
                .filter("username", username);
        
        return find(sql);
    }

    @Override
    public User findByEmail(String email) {
             Octavia sql = Octavia.query(User.class)
                .filter("email", email);
        
        return find(sql);
    }
     
}
