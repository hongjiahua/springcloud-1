package com.gnnu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sso-server", fallback = SsoClientFallBack.class)
public interface SsoFeign {
    @RequestMapping(value = "/sso/haskey/{key}", method = RequestMethod.GET)
    Boolean hasKey(@PathVariable("key") String key);
}
