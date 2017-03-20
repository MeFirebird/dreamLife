package sunwin.zhangdong.web.controller.backend;

import jodd.util.BCrypt;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.comm.AjaxResult;
import sunwin.zhangdong.comm.YoisVelocityTools;
import sunwin.zhangdong.domain.User;
import sunwin.zhangdong.service.UserService;
import sunwin.zhangdong.web.comm.AppContext;
import sunwin.zhangdong.web.comm.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
@Controller
public class UserController {

    @Autowired
    private R r;

    @Autowired
    private YoisVelocityTools tools;

    @Autowired
    private UserService userService;


    /**
     * 没有写get方法哦，看看行不行 ,不行哦！
     *
     * @return 查询需要的数据哦！   user表      条件和分页查询哦！
     */
    @RequestMapping(value = R.UserList)
    public ModelAndView userList(User user) {
        ModelAndView mad = new ModelAndView();
        mad.setViewName("vm-backend/user/usersList");
        List<User> users = userService.getAllUsers(user);
        mad.addObject("users", users);
        mad.addObject("tools", tools);
        return mad;
    }


    /**
     * 修改用户信息页面        搞清楚session：登录成功的时候保存user domain到session中。
     * 当然了，退出的时候也要从session中销毁哦！
     *
     * @return
     */
    @RequestMapping(value = R.userEdit)
    public ModelAndView userEdit() {
        ModelAndView mad = new ModelAndView();
        mad.setViewName("vm-backend/user/user.edit");   // 返回逻辑视图哦！
        // 查询出当前用户的信息哦！
        return mad;
    }

    /**
     * 保存用户信息页面   走ajax吧！  弹窗的方式用户体验会好一点
     * 修改：先查之前的domain哦！ 根据当前的用户名！   然后才是改用户名哦！
     */
    @RequestMapping(value = R.ajaxEditName)
    @ResponseBody
    public JSON saveUserName(String userName) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;                    // 作用域的问题，不能放到try catch中哦
        try {
            // 查询出当前登录的用户
            User user = AppContext.getLoginInfo();
            if (user != null) {
                user.setUserName(userName);
                userService.saveUser(user);
                ajaxResult.setStatus(true);
            } else {
                ajaxResult.setStatus(false);
            }
            json = JSONObject.fromObject(ajaxResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 根据密码判断是不是当前用户的密码     页面要给出提示哦
     *
     * @param password
     */
//    @RequestMapping(value = R.confirmPwd)
//    @ResponseBody
//    public JSON confirmPwd(String password) {
//        AjaxResult ajaxResult = new AjaxResult();
//        JSON json = null;
//        // 获取当前用户的密码
//        User user = AppContext.getLoginInfo();
//        if (user != null) {
//            String pwd = user.getPassword();
//            if(!pwd.equals(password)){
//                ajaxResult.setStatus(false);
//            }else{
//                ajaxResult.setStatus(true);
//            }
//        }else{
//            ajaxResult.setStatus(false);
//        }
//        json = JSONObject.fromObject(ajaxResult);
//        return  json;
//    }


    /** 哎呀，你想错了，在提交的时候验证一下就够了！
     * 修改密码：先查出来当前的domain，然后将页面获取的string设置为当前domain的密码哦！
     */
    @RequestMapping(value = R.ajaxEditPwd)
    @ResponseBody
    public JSON changePassword(String oldPwd, String newPwd) {
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;
        // 验证旧密码是否正确  旧密码从页面获取的没有加密哦
        User user = AppContext.getLoginInfo();
        String handleOldPwd = BCrypt.hashpw(oldPwd, BCrypt.gensalt());
        // 这里验证密码的时候需要解密哦！    原始密码和session中的密码比对
        if(user != null && user.getPassword().equals(handleOldPwd)){
            // 新密码加密
            String HandleNewPwd = BCrypt.hashpw(newPwd, BCrypt.gensalt());
            user.setPassword(HandleNewPwd);
            userService.saveUser(user);
            ajaxResult.setStatus(true);
        }else{
            ajaxResult.setStatus(false);
        }
        json = JSONObject.fromObject(ajaxResult);
        return  json;
    }
    //  不管成功与否，只要java方法执行成功了就进入到ajax的success里面哦！


}
