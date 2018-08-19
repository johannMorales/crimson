package org.king.crimson.dao.security;

import org.king.crimson.model.security.User;
import pe.albatross.octavia.easydao.EasyDAO;

public interface UserDAO extends EasyDAO<User>{

    public User findByUsernameOrEmail(String usernameOrEmail);
    
}
