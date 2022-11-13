package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.SocietyRecruitStatus;
import com.wodongso.wodongso.entity.SocietyRecruitStatusPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRecruitStatusRepository extends JpaRepository<SocietyRecruitStatus, SocietyRecruitStatusPK> {
}
