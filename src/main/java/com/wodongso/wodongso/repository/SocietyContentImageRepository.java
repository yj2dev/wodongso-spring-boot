package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.entity.SocietyContentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SocietyContentImageRepository extends JpaRepository<SocietyContentImage, Integer> {


}
