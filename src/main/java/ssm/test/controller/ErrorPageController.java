package ssm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorPageController {
	
	@RequestMapping("404")
	public String error404(){
		return "error/404";
	}
	@RequestMapping("403")
	public String error403(){
		return "error/403";
	}
	
}
