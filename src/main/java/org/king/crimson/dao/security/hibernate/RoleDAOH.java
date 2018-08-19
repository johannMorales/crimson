package org.king.crimson.dao.security.hibernate;

import org.king.crimson.dao.security.RoleDAO;
import org.king.crimson.model.security.Role;
import org.king.crimson.model.security.RoleName;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.Octavia;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class RoleDAOH extends AbstractEasyDAO<Role> implements RoleDAO {

    public RoleDAOH() {
        super();
        setClazz(Role.class);
    }

    @Override
    public Role findByName(RoleName roleName) {
        Octavia sql = Octavia.query(Role.class)
                .filter("name", roleName);
        
        return find(sql);
    }

}
