package sunwin.zhangdong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sunwin.zhangdong.comm.NameAndValueModel;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.domain.Guidelines;
import sunwin.zhangdong.service.GuidelinesService;
import sunwin.zhangdong.web.comm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
public class GuideLinesController {

    @Autowired
    private GuidelinesService guidelinesService;


    /**
     *
     * @param modelMap
     * @param pix            如何找到视图的呢？
     * @return
     * @throws Exception
     */
    @RequestMapping(value = R.GuideLines)
    public ModelMap  guideLines(ModelMap modelMap,
                                 @RequestParam(required = false, defaultValue = "1") int pix) throws  Exception{
        YogmsPage yomsPage = new YogmsPage();
        yomsPage.setPageSize(3);
        yomsPage.setCurrent(pix);
        modelMap.addAttribute("pix",pix);
        Guidelines guidelines = new Guidelines();
        // 分页查询
        modelMap.addAttribute("guidelines",guidelinesService.findGL(guidelines,yomsPage,null,null));
        modelMap.addAttribute("count",yomsPage.getTotalPage());

        List<NameAndValueModel> pages = new ArrayList<>(0);
        for (int i = 0; i < 15; i++) {       // pageCount 总页数
            NameAndValueModel mavm = new NameAndValueModel();
            mavm.setValue(String.format("/%s?pix=%s", R.GuideLines, i + 1));
            mavm.setName(i + 1 + "");      // 这里
            pages.add(mavm);              // 将domain放入集合中去
            if (i == 9) {                 // 这是为何     最多分9页哦
                break;
            }
        }

        return modelMap;
    }
}
