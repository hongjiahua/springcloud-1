package com.gnnu.feign;

import org.springframework.stereotype.Component;

@Component
public class SsoClientFallBack implements SsoFeign {
    @Override
    public Boolean hasKey(String key) {
        return false;
    }
}
