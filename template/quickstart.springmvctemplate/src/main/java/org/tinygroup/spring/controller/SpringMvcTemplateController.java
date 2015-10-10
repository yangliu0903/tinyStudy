package org.tinygroup.spring.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class SpringMvcTemplateController { 
   
    @RequestMapping(value="/index")
    public String index(Model model){ 
    	model.addAttribute("name","Tiny User");
        model.addAttribute("date",new Date());
        return "index.page";
    }
}

