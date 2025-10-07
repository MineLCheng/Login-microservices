package com.example.eurekaprovider.controller;


import com.example.eurekaprovider.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "hello,"+user.getName()+","+user.getAge();
    }

        @Value("${server.port}")
        String port;
        @RequestMapping("port")
        public String getPort(){
            return "/login";
        }

}
