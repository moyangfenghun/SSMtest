package ssm.test.service.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ssm.test.bean.SystemUser;
import ssm.test.utils.Md5Util;


public class MyShiroRealmServiceImpl extends AuthorizingRealm {

	@Autowired
	SystemUserServiceImpl sysUserService;
	
	/*
	 * 授权,验证权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		user userobj =principals.get;
		
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		System.out.println("授权....");
		roleNames.add("admin");// 添加角色
		permissions.add("admin:list"); // 添加权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);

		info.setStringPermissions(permissions);
		return info;
	} 

	/*
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		System.out.println("登录验证....");
		//原文+盐+散列次数
//		Md5Hash md5Hash=new Md5Hash("12345","ff",2);
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		SystemUser user = sysUserService.getUser(token.getUsername());
		
		if (user!=null) {
			//,ByteSource.Util.bytes("")加盐
			return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
		} else {
			throw new AuthenticationException();
		}
	}

}
