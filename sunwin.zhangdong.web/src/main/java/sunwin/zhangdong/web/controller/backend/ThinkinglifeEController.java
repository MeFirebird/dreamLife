package sunwin.zhangdong.web.controller.backend;

import jodd.datetime.JDateTime;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.comm.AjaxResult;
import sunwin.zhangdong.comm.PageUtil;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.comm.YoisVelocityTools;
import sunwin.zhangdong.domain.ThinkingLife;
import sunwin.zhangdong.service.ThinkingLifeService;
import sunwin.zhangdong.service.common.CommonService;
import sunwin.zhangdong.web.comm.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
@Controller
public class ThinkinglifeEController {

    @Autowired
    private R r;

    @Autowired
    @Qualifier("thinkingLifeService")
    private ThinkingLifeService thinkingLifeService;

    @Autowired
    private YoisVelocityTools tools;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private CommonService commonService;


    /**
     * 查: 无条件查询页面   分页查询
     */
    @RequestMapping(value = R.FindTL)
    public String findTL(ModelMap map,
                         @RequestParam(required = false, defaultValue = "1") int current,
                         ThinkingLife thinkingLife) throws Exception {

        YogmsPage yogmsPage = new YogmsPage();
        yogmsPage.setCurrent(current);
        yogmsPage.setPageSize(10);

        List<ThinkingLife> thinkingLifes = thinkingLifeService.findTL(thinkingLife, yogmsPage, null, null);

        map.addAttribute("tools", tools);
        map.addAttribute("domains", thinkingLifes);
        map.addAttribute("yogmsPage", yogmsPage);
        map.addAttribute("pages", pageUtil.getPageData(yogmsPage, R.FindGL, null));//分页按钮控制
        return "/vm-backend/thinking-life/thinkinglife.find";    // 这里必须return  R里面随便定义
    }

    /**
     * 查：条件查询       分页查询
     * JSON:  作为返回数据哦！   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    @RequestMapping(value= R.ajax_FindTL,method = RequestMethod.POST)
    @ResponseBody
    public JSON ajax_FindTl(ThinkingLife thinkingLife,YogmsPage yogmsPage) throws Exception{
        AjaxResult result = new AjaxResult();
        JSON json = null;
        try {
            // 查询符合条件的结果集
            result.setResultObj("resultList",thinkingLifeService.findTL(thinkingLife,yogmsPage,null,null));
            //
            result.setResultObj("yogmsPage",commonService.resultYogmsPage(yogmsPage,thinkingLifeService.getCount()));
            result.setStatus(true);
            System.out.println(result);
        }catch (Exception e){
            result.setStatus(false);
            // 这里要抛出异常，那么方法声明的地方就必须要抛出
            throw new Exception("获取生活随想录失败!");
        }
        json= JSONObject.fromObject(result);
        return json;

    }



    /**
     * 增：增加页面
     */
    @RequestMapping(value = R.AddTL)
    public String createTL(ModelMap modelMap) {
//        modelMap.addAttribute("R",r);
        return "/vm-backend/thinking-life/thinkinglife.crea";
    }



    /**
     * 增：增加记录     数据库已配通，接下来就是发起请求了
     */
    @RequestMapping(value = R.ajax_saveTL)
    @ResponseBody
    public JSON ajax_CreateTL(ThinkingLife thinkingLife) throws Exception {
        AjaxResult result = new AjaxResult();
        JSON json = null;
        try {
            thinkingLife.setCreateTime(new JDateTime().convertToDate());
//            System.out.println(thinkingLife.getTest());
            thinkingLifeService.addTL(thinkingLife);
        } catch (Exception e) {
            e.printStackTrace();
        }
        json = JSONObject.fromObject(result);
        return json;
    }


    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = R.editTL)
    public ModelAndView toEditPage(Integer id) {
        ModelAndView modlelAndView = new ModelAndView();
        try {
            modlelAndView.addObject("thinkingLife", thinkingLifeService.selectById(id));
        } catch (Exception e) {
            modlelAndView.addObject("message","获取单个菜单信息失败");
        }
        modlelAndView.setViewName("/vm-backend/thinking-life/thinkinglife.edit");
        return modlelAndView;
    }



    /**
     *  编辑并保存记录
     */
    @RequestMapping(value = R.ajax_editTL)
    @ResponseBody
    public JSON   ajax_edit(ThinkingLife thinkingLife) throws Exception{
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;
        try{
            thinkingLife.setCreateTime(new JDateTime().convertToDate());
            thinkingLifeService.updateGL(thinkingLife);
            ajaxResult.setStatus(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        json = JSONObject.fromObject(ajaxResult);
        return json;
    }



    /**
     * 删
     */
    @RequestMapping(value = R.ajax_deleteTL)
    @ResponseBody
    public JSON ajaxDelete(Integer id) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;
        try {
            thinkingLifeService.deleteGL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        json = JSONObject.fromObject(ajaxResult);
        System.out.println(json);

        return json;
    }
}
