package sunwin.zhangdong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.web.comm.R;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class MemoController {

    @RequestMapping(value = R.Memo)
    public ModelAndView memo(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("memo");
        return  mav;
    }
}
