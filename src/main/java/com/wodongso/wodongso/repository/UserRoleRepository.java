package com.wodongso.wodongso.repository;


import com.wodongso.wodongso.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole findByRoleIdContaining(String userId);
}
