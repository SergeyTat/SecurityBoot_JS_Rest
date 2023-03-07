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

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }


    @Override
    public String getAuthority() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
