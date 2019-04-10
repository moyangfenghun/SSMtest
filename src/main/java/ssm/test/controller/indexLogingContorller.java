package ssm.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ssm.test.common.bean.LayuiMessageBean;

@Controller
public class indexLogingContorller {
	
	@RequestMapping("/index")
	public String indexView(HttpServletRequest request,Map<String,Object> map){
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			map.put("userName",session.getAttribute("user"));
		}
		return "index";
	}
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	
	public LayuiMessageBean indexTologing(){
		return null;
	}
}
