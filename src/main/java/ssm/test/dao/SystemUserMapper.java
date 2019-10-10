package ssm.test.dao;

import java.util.List;
import java.util.Map;

import ssm.test.bean.SystemUser;

public interface SystemUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SystemUser record);

	int insertSelective(SystemUser record);

	SystemUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SystemUser record);

	int updateByPrimaryKey(SystemUser record);

	/**
	 * 通过用户名查询系统用户信息，包括密码
	 * 
	 * @param name
	 * @return
	 */
	SystemUser selectByuserName(String name);

	/**
	 * 获取系统用户,分页
	 * @return
	 */
	List<SystemUser> selectAll(Map<String,Object> map);
}