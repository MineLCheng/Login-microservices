package com.example.eurekahystrixclient.controller;


import com.example.eurekahystrixclient.service.LocalItemService;
import com.example.eurekahystrixclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class FilmeController {
    @Autowired
    RibbonService ribbonService;
    @RequestMapping("/hi")
    public String hi(){
        return ribbonService.ribbon();
    }
    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type")String type,
                           @PathVariable("path")String path) {
        return "detail/"+type+"/"+path;
    }
    @GetMapping("/userLogin")
    public String toLoginPage() {
        return ribbonService.ribbon();
    }
    @GetMapping("/getuserBySession")
    @ResponseBody
    public void getUser(HttpSession session) {
        Enumeration<String> names = session.getAttributeNames();
        while (names.hasMoreElements()){
            String element = names.nextElement();
            SecurityContextImpl attribute =
                    (SecurityContextImpl) session.getAttribute(element);
            Authentication authentication = attribute.getAuthentication();
            UserDetails principal = (UserDetails)authentication.getPrincipal();
        }
    }

    @Autowired
    LocalItemService localItemService;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi(String id) {
        return localItemService.hi(id);
    }


}

