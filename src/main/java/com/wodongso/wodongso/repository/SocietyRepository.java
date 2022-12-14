package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.dto.SocietyWithUser;
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


//    Page<Society> findByNameContaining(String searchKeyword, Pageable pageable);

    Page<Society> findByNameContainingAndEnabled(String searchKeyword, Integer isEnable, Pageable pageable);


    @Query("SELECT s FROM Society s WHERE s.enabled = :isEnable")
    Page<Society> findByEnabledPage(@Param("isEnable") Integer isEnable, Pageable pageable);


    @Query("SELECT new SocietyWithUser(s.number, u.university, u.name, s.name, s.category, s.simpleDesc, s.detailDesc, s.enabled) " +
            "FROM Society s " +
            "INNER join User u " +
            "ON u.id = s.officerId " +
            "WHERE u.university = :university")
    List<SocietyWithUser> findAllByUniversity(@Param("university") String university);


}

