package org.king.crimson.dao.security.hibernate;

import org.king.crimson.dao.security.UserDAO;
import org.king.crimson.model.security.User;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.Octavia;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class UserDAOH extends AbstractEasyDAO<User> implements UserDAO {
    
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
     
}
