package org.king.crimson.dao.security;

import org.king.crimson.model.security.Role;
import org.king.crimson.model.security.RoleName;
import org.king.crimson.zelpers.dao.EzDAO;

public interface RoleDAO extends EzDAO<Role> {

    public Role findByName(RoleName roleName);
    
}
