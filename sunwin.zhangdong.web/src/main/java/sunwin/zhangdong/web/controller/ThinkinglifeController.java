package sunwin.zhangdong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sunwin.zhangdong.web.comm.R;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
public class ThinkinglifeController {

    /**
     *
     * @return
     */
    @RequestMapping(value = R.ThinkingLife)
    public String thinkingLife(){
        return "thinking-life";
    }
}
