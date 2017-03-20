package sunwin.zhangdong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunwin.zhangdong.dao.UserMapper;
import sunwin.zhangdong.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *  查询所有的用户
     * @param user
     * @return
     */
    public List<User>  getAllUsers(User user){
        return  userMapper.selectAllUsers(user);
    }


    /**
     *  保存domain的更改
     * @param user
     * @return
     */
    public int saveUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
