package com.wodongso.wodongso.repository;

import com.wodongso.wodongso.dto.SocietyWithUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SocietyRepositoryTest {


    @Autowired
    SocietyRepository societyRepository;


    @Test
    void findAllByUniversity() {
        List<SocietyWithUser> list = societyRepository.findAllByUniversity("호남대학교");
        System.out.println("list >> " + list);
        list.forEach(l -> {
            System.out.println(l.getUniversity());
            System.out.println(l.getCategory());
            System.out.println(l.getNumber());
            System.out.println(l.getUserName());
            System.out.println(l.getSocietyName());
        });
    }
}