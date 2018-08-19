package org.king.crimson.dao.security;

import org.king.crimson.model.security.User;
import org.king.crimson.zelpers.dao.EzDAO;

public interface UserDAO extends EzDAO<User> {

    User findByUsernameOrEmail(String usernameOrEmail);

    User findByUsername(String username);

    User findByEmail(String email);

}
