package ssm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
@Controller
public class ViewResolve {
	@RequestMapping("jsppage")
	public String jsppage(){
		return "NewFile";
	}
}
