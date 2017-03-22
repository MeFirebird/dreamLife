package sunwin.zhangdong.web.comm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sunwin.zhangdong.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/12.
 */

@Component
public class AppContext {

    private static Logger log = Logger.getLogger(AppContext.class);

    /**
     * 获取当前请求Request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attr == null) {
            log.fatal("Failed to get the request!");
            throw new NullPointerException("Failed to get the request!");
        }
        return attr.getRequest();
    }

    /**
     * 获取当前请求Session对象
     *
     * @return
     */
    public static HttpSession getSession() {

        return getRequest().getSession();
    }


    /**
     * 设置当前登录用户
     *
     * @param loginInfo
     */
    public static void setLoginInfo(User loginInfo) {

        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute("loginInfo", loginInfo);
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getLoginInfo() {

        HttpSession session = getSession();
        if (session != null) {
            // 强制类型转换：HttpSession转换为Users    HttpSession是接口哦！
            return (User) getSession().getAttribute("loginInfo");
        }

        return null;
    }




}
