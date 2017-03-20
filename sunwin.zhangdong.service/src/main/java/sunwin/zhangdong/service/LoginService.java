package sunwin.zhangdong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunwin.zhangdong.dao.LoginLogMapper;
import sunwin.zhangdong.dao.UserMapper;
import sunwin.zhangdong.domain.LoginLog;
import sunwin.zhangdong.domain.User;

/**
 * Created by Administrator on 2016/12/12.
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 是否有匹配的用户   若有，返回true
     */
    public boolean hasMatchUser(User user){
        // 用户名和密码等于页面传来的哦！  domain
//        return userMapper.selectByCondition(user);
        User result = userMapper.selectByCondition(user);
        if (result != null){
            System.out.println(result.getUserName());
            return true;     // 找到用户了  有匹配
        }else{
            return false;
        }
    }

    /**
     * 根据用户名查找用户
     * @param
     * @return
     */
    public User findUserByUsername(String userName){
        return userMapper.selectByUserName(userName);
    }

    /**
     * 登录成功，记录用户登录信息：用户id，ip，登录时间
     * @param user
     */
    public void loginSuccess(User user){
        System.out.println(user);       // 打印用户信息，domain要有toString()方法哦
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDatetime(user.getLastVisit());
        loginLogMapper.insert(loginLog);
    }
}
