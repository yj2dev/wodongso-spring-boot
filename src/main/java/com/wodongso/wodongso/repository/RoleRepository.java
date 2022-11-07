package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByIdContaining(Integer id);
}
