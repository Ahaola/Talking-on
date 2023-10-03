package com.example.chatroom.api;

import com.example.chatroom.model.User;
import com.example.chatroom.model.UserMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserAPI {
    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    //登录方法
    public Object login(String username, String password, HttpServletRequest req){
        User user = userMapper.selectByname(username);
        //如果不匹配
        if(user == null || !user.getPassword().equals(password)){
            System.out.println("用户名或密码错误，登录失败!"+user);
            return new User();
        }
        //如果都匹配，登录成功，创建会话
        HttpSession session = req.getSession(true);
        session.setAttribute("user",user);
        //返回数据之前将password干掉，不希望在返回值里面看到password
        user.setPassword("");
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    //注册方法
    public Object register(String username, String password){
        User user = null;
        try{
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int ret = userMapper.insert(user);
            System.out.println("注册成功了"+ret+"行");
            user.setPassword("");

        }catch (DuplicateKeyException e){
            //如果抛出以上异常，说明注册失败，用户名重复了。
            user = new User();
        }
        return user;
    }
}