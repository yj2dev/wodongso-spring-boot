package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.dto.SocietyCreateWithUser;
import com.wodongso.wodongso.dto.SocietyRecruitWithUser;
import com.wodongso.wodongso.dto.SocietyRecruitWithUserOfficer;
import com.wodongso.wodongso.entity.SocietyRecruitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyRecruitStatusRepository extends JpaRepository<SocietyRecruitStatus, Integer> {


    SocietyRecruitStatus findByToSocietyNumberAndFromUserIdContaining(int number, String id);

    @Query("SELECT new SocietyRecruitWithUser(u.id, s.number, s.name, srs.state, srs.rejectReason, srs.createdAt) " +
            "FROM User u " +
            "INNER JOIN SocietyRecruitStatus srs " +
            "ON u.id = srs.fromUserId " +
            "INNER JOIN Society s " +
            "ON s.number = srs.toSocietyNumber " +
            "WHERE u.id = :userId")
    List<SocietyRecruitWithUser> findByUserIdJoinSocietyRecruit(@Param("userId") String userId);


    @Query("SELECT new SocietyRecruitWithUserOfficer(srs.id, srs.toSocietyNumber, u.id, u.name, u.university, u.profileUrl, u.major, u.contact, srs.state, srs.rejectReason, srs.createdAt) " +
            "FROM SocietyRecruitStatus srs " +
            "INNER JOIN User u " +
            "ON srs.fromUserId = u.id " +
            "WHERE srs.toSocietyNumber = :number")
    List<SocietyRecruitWithUserOfficer> findBySocietyRecruitJoinUser(@Param("number") Integer number);


}
