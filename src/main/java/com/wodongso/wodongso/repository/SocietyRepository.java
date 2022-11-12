package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Integer> {
    Page<Society> findByNameContaining(String searchKeyword, Pageable pageable);

    //    Page<Society> findByEnabledContaining(boolean isEnable, Pageable pageable);
    @Query("SELECT s FROM Society s WHERE s.enabled = :isEnable")
    Page<Society> findByEnabledPage(@Param("isEnable") boolean isEnable, Pageable pageable);
}

