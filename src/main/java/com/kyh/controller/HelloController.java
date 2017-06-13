package com.kyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class HelloController {
    @Autowired
    private Environment env;

    @Value("${producer.hello}")
    private String hello;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        String myIP = "";
        try{
            myIP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "hello "+name+"，this is first messge from " + myIP + ":" + env.getProperty("server.port")
                + " and server config: [" + hello + "]";
    }
}