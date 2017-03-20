package sunwin.zhangdong.web.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/14.
 *
 *              页面绑定之用哦！
 */
@Component
public class Velocity {
    @Autowired
    private R r;
    public void  modelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("R",r);
    }

    public void addData(ModelAndView mav, HttpServletRequest request) {
        if(mav != null){
            mav.addObject("R", r);
        }
    }

}
