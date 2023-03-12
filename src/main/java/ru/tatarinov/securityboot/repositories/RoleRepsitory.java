package ru.tatarinov.securityboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tatarinov.securityboot.model.Role;


public interface RoleRepsitory extends JpaRepository<Role,Long> {
}
