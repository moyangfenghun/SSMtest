package ssm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
@Controller
public class ViewResolve {
	@RequestMapping("index")
	public String jsppage(){
		return "index";
	}
}
