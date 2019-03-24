package com.pichub.hello.web;

import com.pichub.hello.bo.Picture;
import com.pichub.hello.bo.User;
import com.pichub.hello.service.PictureService;
import com.pichub.hello.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    PictureService pictureService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/e")
    public String register(){
        return "register";
    }

    @RequestMapping("/loginIndex")
    public String loginIndex(ModelMap user, HttpServletRequest request, HttpServletResponse response){
        user.put("p",request.getSession().getAttribute("userName"));

        return "loginIndex";
    }

    @RequestMapping(value = "/picturelist",method = RequestMethod.POST)
    @ResponseBody
    public List<Picture> picturelist(HttpServletRequest request){
        System.out.println(request.getParameter("1"));
        System.out.println("23453445");
        //List<Picture> picList = pictureService.getHotPicture();
        //System.out.println(pictureService.getHotPicture().get(0));
        return pictureService.getHotPicture();
    }
}


