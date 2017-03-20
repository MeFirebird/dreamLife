package sunwin.zhangdong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sunwin.zhangdong.service.DreamService;
import sunwin.zhangdong.web.comm.R;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
public class CareerController {

    @Autowired
    private DreamService dreamService;

    /**
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = R.Career)
    public String Career() throws IOException{
        return "career";
    }
}
