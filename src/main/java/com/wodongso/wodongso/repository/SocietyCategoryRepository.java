package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.SocietyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyCategoryRepository extends JpaRepository<SocietyCategory, Integer> {

    List<SocietyCategory> findByFromSocietyNumber(Integer number);

    SocietyCategory findByFromSocietyNumberAndName(Integer number, String name);


}
