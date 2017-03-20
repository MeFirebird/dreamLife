package sunwin.zhangdong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunwin.zhangdong.dao.UserMapper;
import sunwin.zhangdong.domain.User;

/**  注册逻辑
 * Created by Administrator on 2016/12/13.
 */
@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.用户名是否已经存在？   需要比对判断（这里来一个ajax比较好哦：jquery代码段） （if输入项多）实时更好
     * @param userName
     * @return
     */
    public boolean hasMatchUser(String  userName){
        if(userMapper.selectByUserName(userName) == null){
            return false;    // 没有找到
        }else{
            return  true;   // 找到
        }
    }



    // 2.输入内容的规范性检查（指定格式）       前端也可以做的哦！（正则、js来做）

           //  用户名格式：手机号？ 邮箱？ 组合方式？     密码：多少位 数字和字母组合啊

    // 3.密码两次输入的一致性检查              这个前端做好一点哦！

    // 4.密码加密                            后端做（前端也是可以得，时机问题：submit之前做）
    //   控制器做了哦
    // 5.将用户信息添加到数据表中

    /**
     *
     * @param user
     * @throws Exception
     */
    public void register(User user) throws Exception{
        userMapper.insert(user);
    }


    /**
     *  获取表中的最大id
     * @return
     */
    public int getMaxId(){
        return  userMapper.selectMaxId();
    }


}
