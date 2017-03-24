package sunwin.zhangdong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.comm.YoisVelocityTools;
import sunwin.zhangdong.domain.Memo;
import sunwin.zhangdong.service.MemoService;
import sunwin.zhangdong.web.comm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class MemoController {

    @Autowired
    private MemoService memoService;

    @Autowired
    private YoisVelocityTools tools;


    /**
     * 备忘录前端首页    条件：类别     分页查询哦！
     *
     * @return
     */
    @RequestMapping(value = R.Memo)
    public ModelAndView memo(@RequestParam(required = false, defaultValue = "1") String typeId,
                             @RequestParam(required = false, defaultValue = "1") int current) {
        ModelAndView mav = new ModelAndView();
        Memo memo = new Memo();

        //  将typeId  ——————》  type      地址栏参数匹配原则哦！
        memo.setType(getTypeById(typeId));      // 构造参数哦！
        mav.setViewName("memo");
        mav.addObject("tools",tools);
        YogmsPage yogmsPage = new YogmsPage();
        yogmsPage.setPageSize(9);
        yogmsPage.setCurrent(current);
        List<Memo> memos = memoService.getMemos(memo, yogmsPage);     //集合拆分哦！ 前提必须大于3
        List<Memo> dangers = new ArrayList<>();    // 红色警告信息
        if (memos.size() <= 3) {
            mav.addObject("dangers", memos);  // memos的个数小于等于3，直接添加到dangers集合
        } else {                                // 否认，取memos的前三条，然后剔除memos的前三条
            dangers.add(memos.get(0));
            dangers.add(memos.get(1));
            dangers.add(memos.get(2));
            mav.addObject("dangers", dangers);
            // 删除memos中的前三条哦
            memos.remove(0);
            memos.remove(0);
            memos.remove(0);
            mav.addObject("memos", memos);
        }
        return mav;
    }


    /**
     * typeId 转换成 type： 目的这样就可以公用同一个service方法、dao方法、mapper文件了
     */
    public String getTypeById(String typeId) {
        String type = "";                  // java局部变量必须初始化哦！系统是不会给你初始化的哦！
        switch (typeId) {
            case "1":
                type = "周目标";
                break;
            case "2":
                type = "月目标";
                break;
            case "3":
                type = "年目标";
                break;
            case "4":
                type = "待分配目标";
                break;
            default:
                type = "周目标";
                break;
        }

        return type;
    }

}
