package org.tinygroup.websample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("testjsp")
	public String testjsp(Model model) {
		return "test";
	}

}
