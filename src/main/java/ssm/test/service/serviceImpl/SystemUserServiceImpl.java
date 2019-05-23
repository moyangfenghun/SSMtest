package ssm.test.service.serviceImpl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.test.bean.SystemUser;
import ssm.test.common.bean.MessageBean;
import ssm.test.dao.SystemUserMapper;

@Service
public class SystemUserServiceImpl {
	@Autowired
	SystemUserMapper systemUserMapper;
	
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
	
	public SystemUser getUser(String name){
		
		return systemUserMapper.selectByuserName(name);
	}
}
