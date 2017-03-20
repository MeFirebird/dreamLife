package sunwin.zhangdong.web.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sunwin.zhangdong.web.comm.R;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
public class DreamEController {

    @Autowired
    private  R r;

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = R.DreamEdit)
    public String dreamEdit(ModelMap map){

        return "/vm-backend/dream-edit";
    }
}
