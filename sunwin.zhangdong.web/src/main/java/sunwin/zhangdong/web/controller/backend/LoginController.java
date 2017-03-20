package sunwin.zhangdong.web.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.domain.User;
import sunwin.zhangdong.service.LoginService;
import sunwin.zhangdong.service.UserService;
import sunwin.zhangdong.web.comm.AppContext;
import sunwin.zhangdong.web.comm.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
@Controller
public class LoginController {

    @Autowired
    private R r;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    /**
     *   登录界面
     * @param map
     * @return
     */
    @RequestMapping(value = R.Login)
    public String login(ModelMap map) {

        return "vm-backend/user/login";     // 返回登陆界面

    }

    /**
     * 登录验证：   请求消息入参
     *
     * @param request
     * @param user                密码比对应该用：checkpw   hashpw每次执行的结果都不一样
     * @return
     */
    @RequestMapping(value = R.LoginCheck)
    public ModelAndView loginCheck(HttpServletRequest request, User user) {

        User target = loginService.findUserByUsername(user.getUserName());   // 查找用户by用户名
        // 如果没匹配的   在指定逻辑视图页返回错误原因的数据模型
        if (target == null) {
            return new ModelAndView("vm-backend/user/login", "error", "用户名不存在");
            // 如果有匹配的  执行相应的逻辑
        } else {
            if(BCrypt.checkpw(user.getPassword(),target.getPassword())){
                target.setLastIp(request.getRemoteAddr());
                target.setLastVisit(new Date());
                userService.saveUser(user);
                loginService.loginSuccess(target);
                request.getSession().setAttribute("loginInfo", target);  // 添加字段到session
                return new ModelAndView("vm-backend/guidelines/guidelines.find");   // 页面重定向
            }else{
                return new ModelAndView("vm-backend/user/login", "error", "密码错误");
            }

        }
    }


    /**
     *  用户退出：销毁session，页面重定向
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = R.logout)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            User user = AppContext.getLoginInfo();

            // 销毁session
            request.getSession().invalidate();
            response.sendRedirect(R.Index);    // 页面重定向

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
