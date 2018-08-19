package org.king.crimson.dao.security;

import org.king.crimson.model.security.Role;
import org.king.crimson.model.security.RoleName;
import pe.albatross.octavia.easydao.EasyDAO;

public interface RoleDAO extends EasyDAO<Role> {

    public Role findByName(RoleName roleName);
    
}
