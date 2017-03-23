package sunwin.zhangdong.web.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.web.comm.R;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class MemoEController {

    /**
     *  访问备忘录列表页
     * @return
     */
    @RequestMapping(value = R.memoList)
    public ModelAndView memoList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/vm-backend/memo/meme.list");
        return  mav;
    }

}
