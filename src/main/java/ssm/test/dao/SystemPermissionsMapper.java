package ssm.test.dao;

import ssm.test.bean.SystemPermissions;

public interface SystemPermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemPermissions record);

    int insertSelective(SystemPermissions record);

    SystemPermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemPermissions record);

    int updateByPrimaryKey(SystemPermissions record);
}