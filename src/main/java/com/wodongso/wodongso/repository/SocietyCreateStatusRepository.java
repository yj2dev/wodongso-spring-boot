package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.SocietyCreateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyCreateStatusRepository extends JpaRepository<SocietyCreateStatus, Integer> {


}
