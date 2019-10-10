package ssm.test.service.serviceImpl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.test.bean.SystemUser;
import ssm.test.common.bean.MessageBean;
import ssm.test.dao.SystemRoleMapper;
import ssm.test.dao.SystemUserMapper;

@Service
public class SystemUserServiceImpl {
	@Autowired
	SystemUserMapper systemUserMapper;
	
	/**
	 * 添加一个新的系统角色
	 * @param user
	 * @return
	 */
	public MessageBean addSystemUser(SystemUser user) {
		MessageBean messageBean=new MessageBean();
		Md5Hash md5Hash=new Md5Hash(user.getPassword(),null,1);
		user.setPassword(md5Hash.toString());
		int i = systemUserMapper.insertSelective(user);
		messageBean.setMessage("成功数量:"+i);
		messageBean.setStatus(0);
		messageBean.setData(user);
		return messageBean;
	}
	
	/**
	 * 通过用户名查询用户的所有信息，包括密码等
	 * @param name
	 * @return
	 */
	public SystemUser getUser(String name){
		
		return systemUserMapper.selectByuserName(name);
	}
	
	
}
