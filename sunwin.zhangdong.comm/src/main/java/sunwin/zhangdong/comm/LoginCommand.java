package sunwin.zhangdong.comm;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/12.
 */
@Component
public class LoginCommand {

    private  String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
