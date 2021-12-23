package org.example.crm.settings.controller;

import org.example.crm.settings.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService service;
}
