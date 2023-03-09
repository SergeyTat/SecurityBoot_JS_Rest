package ru.tatarinov.securityboot.services;

import ru.tatarinov.securityboot.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> allRole();
    public void addRole(Role role);
    public Role findRole(Long id);
    public void removeRole(Long id);
}
