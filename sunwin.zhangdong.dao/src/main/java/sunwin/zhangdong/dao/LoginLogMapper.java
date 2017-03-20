package sunwin.zhangdong.dao;

import sunwin.zhangdong.domain.LoginLog;

public interface LoginLogMapper {

    int deleteByPrimaryKey(Integer loginLogId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer loginLogId);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}