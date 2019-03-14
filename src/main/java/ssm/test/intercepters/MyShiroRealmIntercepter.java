package ssm.test.intercepters;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import ssm.test.utils.Md5Util;

public class MyShiroRealmIntercepter extends AuthorizingRealm {
	//这里因为没有调用后台，直接默认只有一个用户("luoguohui"，"123456")
    private static final String USER_NAME = "luoguohui";  
    private static final String PASSWORD = "123456";

    /* 
     * 授权
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("administrator");//添加角色
        permissions.add("newPage.jhtml");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		 UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        if(token.getUsername().equals(USER_NAME)){
	            return new SimpleAuthenticationInfo(USER_NAME, Md5Util.MD5(PASSWORD), getName());  
	        }else{
	            throw new AuthenticationException();  
	        }
	}

}
