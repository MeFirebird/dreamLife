package sunwin.zhangdong.web.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.web.comm.R;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class DevProcess {

    /**
     *
     * @return
     */
    @RequestMapping(value = R.devProcess)
    public ModelAndView devProcess(){
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("vm-backend/de_process/process");
        return mnv;
    }
}
