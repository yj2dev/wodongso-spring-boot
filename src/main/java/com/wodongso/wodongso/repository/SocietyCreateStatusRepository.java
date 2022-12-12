package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.dto.MySocietyCreate;
import com.wodongso.wodongso.dto.MySocietyList;
import com.wodongso.wodongso.dto.SocietyCreateWithUser;
import com.wodongso.wodongso.entity.SocietyCreateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyCreateStatusRepository extends JpaRepository<SocietyCreateStatus, Integer> {


    @Query("SELECT new SocietyCreateWithUser(u.id, s.number, s.name, s.simpleDesc, scs.state, scs.rejectReason, scs.createdAt) " +
            "FROM User u " +
            "INNER JOIN SocietyCreateStatus scs " +
            "ON u.id = scs.fromUserId " +
            "INNER JOIN Society s " +
            "ON s.number = scs.toSocietyNumber " +
            "WHERE u.id = :userId")
    List<SocietyCreateWithUser> findByUserIdJoinSocietyCreate(@Param("userId") String userId);


    @Query("SELECT new MySocietyList (u.id, u.name, s.name, s.profileUrl, s.number) " +
            "FROM User u " +
            "INNER JOIN SocietyCreateStatus scs " +
            "ON scs.fromUserId = u.id " +
            "INNER JOIN Society s " +
            "ON scs.toSocietyNumber = s.number and scs.state = 1 " +
            "WHERE u.id = :userId")
    List<MySocietyList> findMySocietyCreate(@Param("userId") String userId);

    SocietyCreateStatus findByToSocietyNumberAndFromUserId(Integer number, String userId);

}
