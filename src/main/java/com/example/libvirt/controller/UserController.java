package com.example.libvirt.controller;

import com.example.libvirt.pojo.Host;
import com.example.libvirt.pojo.LibvirtConnect;
import com.example.libvirt.service.LibvirtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    /**
     * 跳转登录页面
     */
    @RequestMapping(value = {"/"})
    public String toLogin() {
        return "login";
    }

    /**
     * 登录
     */
    @RequestMapping(value = {"/login"})
    public String login(@RequestParam(value = "username", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password,
                        Model model,
                        HttpSession session) {
        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("loginUser", userName);//UserName存入Session
            return "redirect:index";
        }
        model.addAttribute("msg", "userName or password error!");
        return "login";
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            session.removeAttribute("loginUser");          //移除Session 转到登陆界面
        }
        return "redirect:/";
    }

}
