package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.entity.SocietyWithUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Integer> {


    @Override
    Optional<Society> findById(Integer integer);

    boolean findByEnabledContaining(boolean isEnable);

    Page<Society> findByNameContaining(String searchKeyword, Pageable pageable);


    @Query("SELECT s FROM Society s WHERE s.enabled = :isEnable")
    Page<Society> findByEnabledPage(@Param("isEnable") boolean isEnable, Pageable pageable);


    @Query("SELECT new SocietyWithUser(s.number, u.university, u.name, s.name, s.category, s.simpleDesc, s.detailDesc, s.enabled) " +
            "FROM Society s " +
            "INNER join User u " +
            "ON u.id = s.officerId " +
            "WHERE u.university = :university")
    List<SocietyWithUser> findAllByUniversity(@Param("university") String university);


//    @Query("SELECT u.university, u.name, s.name, s.category, s.simpleDesc, s.detailDesc, s.enabled " +
//            "FROM Society s " +
//            "INNER join User u " +
//            "ON u.id = s.officerId " +
//            "WHERE u.university = :university")
//    List<Society> findAllByUniversity(@Param("university") String university);


}

