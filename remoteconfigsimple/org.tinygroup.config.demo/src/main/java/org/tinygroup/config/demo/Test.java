package org.tinygroup.config.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value={"/springmvc"})
public class Test {

	@Value("${wholeWidthwholeWidth}")
	String pro1;
	
	@Value("${name1}")
	String pro2;
	
	@RequestMapping(value= {"/getUsersPage.shtm"})
    public ModelAndView getUserPage(ModelAndView mv){
        List<String> list = new ArrayList<String>();
        list.add("Hello Tiny!");
        list.add("Hello,悠然！");
        mv.addObject("list",list);
        mv.setViewName("/test.jsp");
        return mv;
    }
	
}
