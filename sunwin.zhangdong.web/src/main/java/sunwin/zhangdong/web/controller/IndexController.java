package sunwin.zhangdong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sunwin.zhangdong.comm.PropertiesUtil;
import sunwin.zhangdong.domain.Dream;
import sunwin.zhangdong.service.DreamService;
import sunwin.zhangdong.web.comm.AppContext;
import sunwin.zhangdong.web.comm.R;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
@Controller
public class IndexController {

    @Autowired
    private DreamService dreamService;

    @Autowired
    private R r;

    /**
     *
     * @param request
     * @param modelMap
     * @throws IOException
     */
    @RequestMapping(value = R.Index, method = RequestMethod.GET, produces = {"text/html;charset=UTF-8"})
    public void Index(HttpServletRequest request, ModelMap modelMap) throws IOException {
        Dream dream = dreamService.select(1);  //这里的service处的方法要有返回值哦！
        modelMap.addAttribute("dream", dream);
//        modelMap.addAttribute("R",r);    // 不然首页的登录链接将无法解析
//        request.setAttribute("R",r);
        // 解析url
        AppContext.getSession().setAttribute("R", r);
        // 读取路径
        AppContext.getSession().setAttribute("imgPath", PropertiesUtil.getProperty("videoPath"));
//      return "dream-heaven";   //  这里返回的视图文件必须是vm文件夹里的文件哦！

    }
}
