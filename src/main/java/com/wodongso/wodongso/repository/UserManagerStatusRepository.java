package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.dto.ManagerWithUser;
import com.wodongso.wodongso.entity.UserManagerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManagerStatusRepository extends JpaRepository<UserManagerStatus, Integer> {

    UserManagerStatus findByFromUserId(String id);

    @Query("SELECT new ManagerWithUser (u.id, u.name, u.contact, u.university, u.major, u.classOf, " +
            "ums.proofImageUrl, ums.createdAt, ums.state, ums.rejectReason) " +
            "FROM User u " +
            "INNER JOIN UserManagerStatus ums " +
            "ON u.id = ums.fromUserId")
    List<ManagerWithUser> findAllManagerStatus();

    UserManagerStatus deleteById(String id);
}
