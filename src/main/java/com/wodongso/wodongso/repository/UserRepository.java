package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByIdContaining(String searchKeyword);
//
//    @Query(value = "SELECT u.id, r.name " +
//            "FROM User u " +
//            "INNER JOIN UserRole ur " +
//            "ON u.id = ur.userId " +
//            "INNER JOIN Role r " +
//            "ON r.id = ur.roleId " +
//            "WHERE u.id = :userId")
//    List<UserRoleDTO> findByUserId(@Param("userId") String userId);


}
