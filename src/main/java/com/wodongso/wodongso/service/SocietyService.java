package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocietyService {

    @Autowired
    private SocietyRepository societyRepository;

    public List<Society> societyList(){
        return societyRepository.findAll();
    }


}
