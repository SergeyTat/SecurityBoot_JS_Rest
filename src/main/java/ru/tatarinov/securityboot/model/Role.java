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
    private String name;

    public Role(String role) {
        this.name = role;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.name = role;
    }

    public Role() {

    }


    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + name + '\'' +
                '}';
    }




}
