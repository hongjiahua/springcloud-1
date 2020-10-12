package com.gnnu.controller;

import com.gnnu.service.StudentFeign;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.framework.qual.RequiresQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private StudentFeign studentFeign;

    @RequestMapping("/listall")
    @ResponseBody
    public List getall(HttpSession session) {
        return studentFeign.getallstu();
    }
}
