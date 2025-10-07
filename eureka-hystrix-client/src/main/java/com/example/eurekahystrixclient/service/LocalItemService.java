package com.example.eurekahystrixclient.service;

import com.example.eurekahystrixclient.config.HystrixConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-provider",
        configuration = HystrixConfig.class,
        fallback = LocalItemServiceImpl.class)
@Service
public interface LocalItemService {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hi(@RequestParam(value = "id")String id);
}

