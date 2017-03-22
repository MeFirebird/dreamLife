package sunwin.zhangdong.dao;

import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.LoginLog;

@Repository
public interface LoginLogMapper {

    int deleteByPrimaryKey(Integer loginLogId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer loginLogId);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}