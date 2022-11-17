package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.SocietyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyContentRepository extends JpaRepository<SocietyContent, Integer> {

    List<SocietyContent> findByToCategoryId(Integer number);

//    SocietyContent

}
