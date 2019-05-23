package ssm.test.controller;

import java.security.acl.Permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssm.test.bean.SystemUser;
import ssm.test.common.bean.MessageBean;
import ssm.test.service.serviceImpl.SystemUserServiceImpl;

@RestController
@RequestMapping("/systemUserData")
public class SystemUserDataController {
	
	@Autowired
	SystemUserServiceImpl systemUserService;
  
	@RequestMapping("addUser")
    public MessageBean addSystemUser(SystemUser user){
		System.out.println(user.getContent());
		return systemUserService.addSystemUser(user);
    }
	@RequestMapping("getSystemUser")
    public SystemUser getSystemUser(SystemUser user){
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("admin")){
			System.out.println("该用户是admin...");
		}
		if(subject.isPermitted("admin:list")){
			System.out.println("该用户拥有admin:list...");
		}
		return systemUserService.getUser(user.getName());
    }
}