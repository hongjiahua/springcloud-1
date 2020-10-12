package com.gnnu.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFallBack implements StudentFeign {

    @Override
    public List getallstu() {
        System.out.println("请求失败");
        return null;
    }
}
