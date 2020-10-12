package com.gnnu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "eureka-client-student", fallback = StudentFallBack.class)
public interface StudentFeign {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List getallstu();
}
