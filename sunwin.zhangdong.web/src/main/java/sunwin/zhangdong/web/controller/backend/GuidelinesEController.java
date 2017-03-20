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
import sunwin.zhangdong.domain.Guidelines;
import sunwin.zhangdong.service.GuidelinesService;
import sunwin.zhangdong.web.comm.R;
import sunwin.zhangdong.web.comm.Velocity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
//@RequestMapping(value = "vm-backend/guidelines")
public class GuidelinesEController {
    @Autowired
    private R r;

    @Autowired
    private Velocity velocity;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private YoisVelocityTools tools;

    @Autowired
    @Qualifier("guidelinesService")
    private GuidelinesService guidelinesService;

    /**
     * 查找全部：一次性都查出来哦
     * tpix    当前页码           数据库分页
     * @return
     * @throws Exception
     */
    @RequestMapping(value = R.FindGL)
    public String findGL(ModelMap map,
                         @RequestParam(required = false, defaultValue = "1") int current,
                         Guidelines guidelines ) throws Exception{
        YogmsPage yogmsPage = new YogmsPage();
        yogmsPage.setCurrent(current);
        yogmsPage.setPageSize(8);
        List<Guidelines> guidelinesList = guidelinesService.findGL(guidelines, yogmsPage,null, null);
        //  上面这个方法执行过程中，根据pagesize进行了分页哦， 总页数知道了哦！
        map.addAttribute("tools",tools);
        map.addAttribute("domains", guidelinesList);
        map.addAttribute("yogmsPage",yogmsPage);
        map.addAttribute("pages",pageUtil.getPageData(yogmsPage,R.FindGL,null));//分页按钮控制
        return "/vm-backend/guidelines/guidelines.find";    // 这里必须return  R里面随便定义
    }

    /**
     * 条件查找： 1.按标题查     2.按创建时间查     3.按时间段查      4.分页查
     */





    /**
     * 跳转到创建指导方针页面
     * @param modelMap
     * @return
     */
    @RequestMapping(value = R.AddGL)
    public String addGL(ModelMap modelMap) {
//        modelMap.addAttribute("R", r);
        return "/vm-backend/guidelines/guidelines.crea";
    }

    /**
     * 创建指导方针：ajax异步请求，通过ajax传递请求参数到控制器形参，控制器完成domain的创建
     *
     * 错误原因：请求参数没有传进来（请求报文中的参数——》domain）
     *
     * @return
     */
    @RequestMapping(value = R.ajax_saveGL, method = RequestMethod.POST)
    @ResponseBody
    public JSON ajax_createGL(Guidelines guidelines) throws Exception{
        AjaxResult result = new AjaxResult();
        JSON json = null;

        System.out.print(guidelines.getGuidelinesTitle());
        System.out.println(guidelines.getGuidelinesContent());

       try{
            // 给创建的domain添加时间属性    用Date哦
            guidelines.setCreateTime(new JDateTime().convertToDate());
            guidelinesService.createGL(guidelines);   // 执行此处遇到问题了，进入catch代码块
            result.setStatus(true);
        }catch(Exception e){                        // 请求处理到这里来了
            e.printStackTrace();
            throw new Exception("创建指导方针失败");
        }
        json = JSONObject.fromObject(result);
        return json;
    }


    /**
     * 跳转到编辑指导方针页面,  需要id哦！           这里的请求参数入参！需要好好的理解哦！
     */
    @RequestMapping(value = R.EditGL)
    public ModelAndView toModifyMenu(Integer id){
        ModelAndView mad =new ModelAndView();
        try{
            mad.addObject("guidelines", guidelinesService.select(id));
//            mad.addObject("R",r);
        }catch (Exception e){
            mad.addObject("message","获取单个菜单信息失败!");
        }
        mad.setViewName("/vm-backend/guidelines/guidelines.edit");

        return  mad;
    }


    /**
     * 编辑指导方针     走ajax哦
     * @return
     */
    @RequestMapping(value = R.ajax_editGL,method = RequestMethod.POST)
    @ResponseBody
    public JSON  ajax_edit(Guidelines guidelines) throws  Exception{
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;
        try{
            guidelines.setCreateTime(new JDateTime().convertToDate());
            guidelinesService.updateGL(guidelines);
            ajaxResult.setStatus(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        json = JSONObject.fromObject(ajaxResult);
        return json;
    }


    /**
     *  删除
     */
    @RequestMapping(value = R.ajax_deleteGL,method = RequestMethod.POST)
    @ResponseBody
    public JSON ajax_deleteGL(Integer id){      // ajax的data传递参数
        AjaxResult ajaxResult = new AjaxResult();
        JSON json = null;
        try{
            guidelinesService.deleteGL(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        json = JSONObject.fromObject(ajaxResult);
        return  json;
    }



}
