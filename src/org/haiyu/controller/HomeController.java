package org.haiyu.controller;

import org.haiyu.annotation.Autowired;
import org.haiyu.annotation.Controller;
import org.haiyu.annotation.RequestBoby;
import org.haiyu.annotation.RequestMapping;
import org.haiyu.bean.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lyq on 2018/3/29.
 */
@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping(path = "/home",method = "get")
    public ModelAndView home(){
        String ss=homeService.getHome();
        return  new ModelAndView("home.jsp").addData("message",ss);
    }

    @RequestBoby
    @RequestMapping(path = "/ajax",method = "get")
    public String ajaxhome(){
        String ss=homeService.getHome();
        return  ss;
    }

    @RequestBoby
    @RequestMapping(path = "/json",method = "get")
    public Student json(){
        Student student = new Student();
        student.setId(1);
        student.setName("Jack");
        return  student;
    }

    @RequestMapping(path = "/login",method = "post")
    public String login(HttpServletRequest request){

        String ss=request.getParameter("name");
        System.out.println(ss);
        return  ss;
    }
}
