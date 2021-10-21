package com.diary.service;

import com.diary.entity.Test;
import com.diary.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public String getTestVal(){

        List<Test> list = testRepository.findAll();
        return list.get(0).getVal();
    }
}
