package org.king.crimson.model.security;

import java.io.Serializable;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }

}
