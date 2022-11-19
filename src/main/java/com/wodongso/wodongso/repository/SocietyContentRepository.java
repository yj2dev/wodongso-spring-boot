package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.dto.CategoryDetail;
import com.wodongso.wodongso.entity.SocietyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyContentRepository extends JpaRepository<SocietyContent, Integer> {

    List<SocietyContent> findByToCategoryId(Integer number);

    @Query("SELECT new CategoryDetail (sc.id, sc.title, sc.content, sci.path, u.id, u.nickname, u.name, u.profileUrl, sc.createdAt) " +
            "FROM SocietyContent sc " +
            "INNER JOIN SocietyContentImage sci " +
            "ON sc.id = sci.fromSocietyContentId " +
            "INNER JOIN User u " +
            "ON u.id = sc.writerId " +
            "WHERE sc.id = :scid")
    List<CategoryDetail> findByIdJoinUserAndImage(@Param("scid") Integer scid);

}
