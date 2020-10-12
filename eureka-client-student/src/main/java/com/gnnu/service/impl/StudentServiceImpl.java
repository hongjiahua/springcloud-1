package com.gnnu.service.impl;

import com.gnnu.dao.StudentDao;
import com.gnnu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List getAlL() {
        return studentDao.findAll();
    }
}
