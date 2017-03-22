package sunwin.zhangdong.dao;

import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.User;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByCondition(User user);   // 这里的返回值类型是可以直接改的哦，都不用改sql！

    User selectByUserName(String userName);

    List<User> selectAllUsers(User user);

    int selectMaxId();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}