package sunwin.zhangdong.web.controller.backend;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.comm.AjaxResult;
import sunwin.zhangdong.comm.PageUtil;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.comm.YoisVelocityTools;
import sunwin.zhangdong.domain.Memo;
import sunwin.zhangdong.service.MemoService;
import sunwin.zhangdong.web.comm.R;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class MemoEController {

    @Autowired
    private MemoService memoService;

    @Autowired
    private YoisVelocityTools tools;

    @Autowired
    private PageUtil pageUtil;


    /**
     * 访问备忘录列表页     条件和分页查询哦    这两个必须在一起配合使用哦！
     *
     * @return
     */
    @RequestMapping(value = R.memoList)
    public ModelAndView memoList(Memo memo,
                                 @RequestParam(required = false, defaultValue = "1") int current) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/vm-backend/memo/memo.list");
        // 查询数据
        YogmsPage yogmsPage = new YogmsPage();
        yogmsPage.setCurrent(current);
        yogmsPage.setPageSize(9);
        List<Memo> memos = memoService.getMemos(memo, yogmsPage);
        List<String> types = memoService.getTypes();   // 所有的类型
        mav.addObject("types", types);
        mav.addObject("memos", memos);
        mav.addObject("yogmsPage", yogmsPage);  // 页面分页
        mav.addObject("tools", tools);      //解析日期哦
        mav.addObject("pages", pageUtil.getPageData(yogmsPage, R.memoList, null));//分页按钮控制
        return mav;
    }


    /**
     * 备忘录创建页面
     *
     * @return
     */
    @RequestMapping(value = R.memoAdd)
    public ModelAndView addMemo() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/vm-backend/memo/memo.add");
        return mav;
    }


    /**
     * 备忘录创建
     *
     * @param memo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = R.ajax_saveMemo)
    @ResponseBody
    public JSON ajaxSaveMemo(Memo memo) throws Exception {
        AjaxResult result = new AjaxResult();
        JSON json = null;
        try {
            memo.setCreateTime(new Date());
            memoService.saveMemo(memo);
            result.setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
        }
        json = JSONObject.fromObject(result);
        return json;
    }

    /**
     * @param id 编辑页面
     * @return
     */
    @RequestMapping(value = R.memoEdit)
    public ModelAndView editMemo(int id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/vm-backend/memo/memo.edit");
        mav.addObject("memo", memoService.getMemoById(id));
        return mav;
    }


    /**
     * 保存编辑后的备忘录
     *
     * @param memo
     * @return
     */
    @RequestMapping(value = R.ajax_updateMemo)
    @ResponseBody
    public JSON updateMemo(Memo memo) {
        JSON json = null;
        AjaxResult result = new AjaxResult();
        try {
            memoService.updateMemo(memo);
            result.setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
        }
        json = JSONObject.fromObject(result);
        return json;
    }


    /**
     *    删除备忘录
     * @param ids
     * @return
     */
    @RequestMapping(value = R.ajax_deleteMemo)
    @ResponseBody
    public JSON deleteMemo(Integer[] ids) {
        JSON json = null;
        AjaxResult result = new AjaxResult();
        try{
            memoService.deleteMemo(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        json = JSONObject.fromObject(result);
        return  json;
    }


}
