package sunwin.zhangdong.web.controller.backend;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.comm.AjaxResult;
import sunwin.zhangdong.comm.StringUtil;
import sunwin.zhangdong.comm.YoisVelocityTools;
import sunwin.zhangdong.domain.Module;
import sunwin.zhangdong.service.common.ModuleService;
import sunwin.zhangdong.web.comm.FileHelper;
import sunwin.zhangdong.web.comm.HandlerPhoto;
import sunwin.zhangdong.web.comm.R;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

@Controller
public class ModuleController {


    @Autowired
    private ModuleService moduleService;

    @Autowired
    private HandlerPhoto handlerPhoto;

    @Autowired
    private YoisVelocityTools tools;

    /**
     * 模块列表页面
     *
     * @return
     */
    @RequestMapping(value = R.moduleList)
    public ModelAndView moduleList() {
        ModelAndView mav = new ModelAndView();
        List<Module> modules = moduleService.getAllModules();
        mav.addObject("tools",tools);
        mav.addObject("modules",modules);
        mav.setViewName("vm-backend/hobby_m/modules.list");
        return mav;
    }


    /**
     * 模块创建页
     *
     * @return
     */
    @RequestMapping(value = R.moduleAdd)
    public ModelAndView moduleAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("vm-backend/hobby_m/modules.add");
        return mav;
    }


    /**
     * 保存创建的模块
     *
     * @param request
     * @param imgbase64
     * @param module
     * @return
     */
    @RequestMapping(value = R.ajax_saveModule)
    @ResponseBody
    public JSON saveModuel(HttpServletRequest request, String imgbase64, Module module) throws Exception {
        JSON json = null;
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String photoName = null;
            //如果有上传图片，保存图片
            if (imgbase64.contains("data:image/")) {
                String root = request.getServletContext().getRealPath("/");   // 图片保存路径
                //先删除原有的图片
                if(module.getId() != null){
                    String photoUrl = moduleService.getOneModule(module.getId()).getImg();
                    if (StringUtil.isNotEmpty(photoUrl)) {
                        FileHelper.delFile(root + "/" + photoUrl);   // 删除图片文件  嗯嗯！
                    }
                }

                // 图片保存到服务器上
                photoName = handlerPhoto.GenerateImage(request, imgbase64, "modulePhoto");
                // 给domain设置图片的url
                module.setImg(photoName);
                ajaxResult.setStatus(true);
            }
            // 原来没有图片，直接保存就OK
//            module.setImg(photoName);
            if (module.getId() == null) {
                module.setCreateTime(new Date());
                moduleService.saveModule(module);    // 保存
            } else {
                moduleService.modifyModule(module);  // 修改
            }
            ajaxResult.setStatus(true);
        } catch (Exception e) {
            e.getMessage();
            ajaxResult.setStatus(false);
            throw new Exception("保存青奥简介信息失败!");
        }
        json = JSONObject.fromObject(ajaxResult);
        return json;
    }



    /**
     * 模块编辑页
     *
     * @return
     */
    @RequestMapping(value = R.moduleEdit)
    public ModelAndView moduleEdit(int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("module",moduleService.getOneModule(id));
        mav.setViewName("vm-backend/hobby_m/modules.edit");
        return mav;
    }



//    @RequestMapping(value= R.ajax_delIntroduce,method = RequestMethod.POST)
//    @ResponseBody
//    public JSON ajax_delIntroduce(HttpServletRequest request,String ids) throws Exception{
//        AjaxResult result = new AjaxResult();
//        JSON json = null;
//        try {
//            if(StringUtil.isNotEmpty(ids)){
//                introduceService.delIntroduce(request, StringUtils.split(ids,","));
//            }
//        }catch (Exception e){
//            LogUtil.getInstance().error(e);
//            throw new Exception("删除数据失败,请稍后重试!");
//        }
//        json= JSONObject.fromObject(result);
//        return json;
//    }

    /**
     *  删除模块
     * @param request
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = R.ajax_deleteModule)
    @ResponseBody
    public JSON ajax_deleteModule(HttpServletRequest request,Integer[] ids) throws Exception{
        AjaxResult result = new AjaxResult();
        JSON json = null;
        try{
           moduleService.deleteModule(request,ids);
        }catch (Exception e){
            result.setStatus(false);
            e.printStackTrace();
            throw new Exception("删除数据失败!");
        }
        json = JSONObject.fromObject(result);
        return  json;
    }


}
