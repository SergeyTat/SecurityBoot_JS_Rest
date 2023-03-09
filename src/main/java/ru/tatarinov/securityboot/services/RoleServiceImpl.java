package ru.tatarinov.securityboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.repositories.RoleRepsitory;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepsitory roleRepsitory;

    @Autowired

    public RoleServiceImpl(RoleRepsitory roleRepsitory) {
        this.roleRepsitory = roleRepsitory;
    }

    @Override
    public List<Role> allRole() {
        return roleRepsitory.findAll();
    }
    @Override
    public void addRole(Role role) {
        roleRepsitory.save(role);
    }
    @Override
    @Transactional(readOnly = true)
    public Role findRole(Long id) {
        return roleRepsitory.findById(id).get();
    }
    @Override
    @Transactional
    public void removeRole(Long id) {
        roleRepsitory.deleteById(id);
    }

}
