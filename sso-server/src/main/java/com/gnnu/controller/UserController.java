package com.gnnu.controller;

import com.gnnu.entity.User;
import com.gnnu.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sso")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/haskey/{key}")
    @ResponseBody
    public Boolean haskey(@PathVariable("key") String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String checkUserAndPassword(String username, String password) {
        User user = userService.getUserByName(username);
        String flag = null;
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            flag = username + System.currentTimeMillis();
            redisTemplate.opsForValue().set(flag, username, (long) 5 * 60, TimeUnit.SECONDS);
        }
        return flag;
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request,HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("url") String url) {
        String flag = checkUserAndPassword(username, password);
        if (!StringUtils.isEmpty(flag)) {
            Cookie cookie = new Cookie("accessToken", flag);
            cookie.setPath("/");
            cookie.setMaxAge(5*60);
            try {
                response.addCookie(cookie);
                response.sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }
}
