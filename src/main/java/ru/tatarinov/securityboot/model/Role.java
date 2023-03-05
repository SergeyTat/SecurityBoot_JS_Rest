package ru.tatarinov.securityboot.model;



import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table
public class Role implements GrantedAuthority {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return role;
    }
}
