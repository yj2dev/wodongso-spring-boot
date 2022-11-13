package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.ManagerWithUser;
import com.wodongso.wodongso.entity.SocietyWithUser;
import com.wodongso.wodongso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByIdContaining(String searchKeyword);


}
