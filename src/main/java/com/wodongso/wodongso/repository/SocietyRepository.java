package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.Society;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Integer> {

    Page<Society> findByNameContaining(String searchKeyword, Pageable pageable);
}

