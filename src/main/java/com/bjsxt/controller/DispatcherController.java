package com.bjsxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器
 */
@Controller
public class DispatcherController {
    /**
     * 只处理get请求方式。跳转到登录页面。
     * 使用自定义的登录页面，必须增加配置信息。
     * @return
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String toLogin(){
        return "index";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/loginError")
    public String loginError(){
        return "loginError";
    }


}
