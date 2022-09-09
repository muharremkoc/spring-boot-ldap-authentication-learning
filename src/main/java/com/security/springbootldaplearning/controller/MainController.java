package com.security.springbootldaplearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {


    @GetMapping("/")
    public String success(){
        return "LDAP connection Success";
    }



}
