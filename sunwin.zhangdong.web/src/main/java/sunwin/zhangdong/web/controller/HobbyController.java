package sunwin.zhangdong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.domain.Module;
import sunwin.zhangdong.service.common.ModuleService;
import sunwin.zhangdong.web.comm.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
public class HobbyController {

    @Autowired
    private ModuleService moduleService;


    /**
     *
     * @return
     */
    @RequestMapping(value = R.Hobby)
    public ModelAndView hobby(){
       ModelAndView mav = new ModelAndView();
        List<Module>  modules =  moduleService.getAllModules();
        mav.addObject("modules",modules);
        mav.setViewName("hobby");
        return mav;
    }





}
