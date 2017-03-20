package sunwin.zhangdong.web.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.domain.User;
import sunwin.zhangdong.service.RegisterService;
import sunwin.zhangdong.web.comm.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/13.
 */
@Controller
public class RegisterController {

    @Autowired
    private R r;

    @Autowired
    private RegisterService registerService;


    /** 注册页
     * @param modelMap
     * @return
     */
    @RequestMapping(value = R.register, method = RequestMethod.GET)
    public String register(ModelMap modelMap){

        return "vm-backend/user/register";
    }


    /**  保证页面中的name属性和控制器方法的形参一致哦！ 这样参数就会按照名称匹配的方式匹配进来！
     *
     * @param request
     * @param user
     * @param repassword
     * @return
     */
    @RequestMapping(value = R.register,method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user, String repassword, HttpServletResponse response) throws  Exception{
        ModelAndView mav = new ModelAndView();
        // 1.验证码与服务器生成的放到session中的匹配？ 这是后端第一件要做的！

        // 2.用户名是否已经注册，用户名是唯一的哦！（先看是否注册过，然后再加密保存到数据库中好一点吧）
        if(! registerService.hasMatchUser(user.getUserName())){

            user.setUserId(registerService.getMaxId() + 1);    // 设置id   先查出当前user表的最大id，此基础上加1

            // 用户名从页面直接传进来就绑定到domain上了，所以不用设置了！

            String newPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()); // 3.密码加密加盐保存到数据库中
            user.setPassword(newPwd);

            user.setLastVisit(new java.util.Date());   // java的数据类型和数据库的数据类型自动转换？

            user.setLastIp(request.getRemoteAddr());    // 获取用户的ip

            user.setUserRole("管理员");

            user.setCreateTime(new Date());   // 设置用户domain的创建时间

            registerService.register(user);            // 将用户信息添加到数据库中

            mav.addObject("message","注册成功，请登录！");   // 注册成功后的反馈哦！
            mav.setViewName("vm-backend/user/login");
        }else{
            mav.addObject("message","用户名已注册");
            mav.setViewName("vm-backend/user/register");
        }
        // 这个return是 if-else之外的哦！  即使不写else     不用下面这种方式是因为界面挑战的太快
//        mav.setViewName("vm-backend/user/login");      // 注册成功后返回到指定页面哦！
        return  mav;
    }


}
