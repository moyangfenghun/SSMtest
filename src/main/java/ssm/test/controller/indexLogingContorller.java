package ssm.test.controller;

import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86189
 *
 */
@Controller
public class indexLogingContorller {

	/**
	 * 
	 * 登录接口
	 * 
	 * @param name
	 * @param password
	 * @param rememberMe
	 * @param map
	 * @return
	 */
	@RequestMapping("/login/authentization")
	public String loginAuthentization(String name, String password, boolean rememberMe, Map<String, Object> map) {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("rememberMe:" + rememberMe);
		UsernamePasswordToken token = new UsernamePasswordToken(name, password, rememberMe);
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				map.put("userName", name);
			}
			token.getCredentials();
			token.getPrincipal();
		} catch (DisabledAccountException e) {
			String msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
			System.out.println(msg);
		} catch (UnauthorizedException e) {
			String msg = "您没有得到相应的授权！" + e.getMessage();
			System.out.println(msg);
		} catch (AuthenticationException e) {
			// e.printStackTrace();
			String msg = "账号或者密码错误！" + e.getMessage();
			System.out.println(msg);
		}
		return "login";
	}

	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 首页页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	/**
	 * 登出接口
	 */
	@RequestMapping(value = "/login/loginOut")
	public String LoginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		System.out.println("登出.....");
		return "redirect:/index";
	}
}
