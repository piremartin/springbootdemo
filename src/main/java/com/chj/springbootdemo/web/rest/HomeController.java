package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.service.pojo.Msg;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chehaojie
 * @date 2019/04/26 15:59
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {

        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "abc";
    }
}
