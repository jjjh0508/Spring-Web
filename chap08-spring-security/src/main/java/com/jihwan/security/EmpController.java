package com.jihwan.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmpController {

    @GetMapping("list")
    public void listPage(){}

    @GetMapping("/admin")
    public void adminPage(){}
}
