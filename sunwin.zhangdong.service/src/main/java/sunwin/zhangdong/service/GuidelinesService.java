package sunwin.zhangdong.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.dao.GuidelinesMapper;
import sunwin.zhangdong.domain.Guidelines;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class GuidelinesService {
    @Autowired
//    @Qualifier("guidelinesMapper")
    private GuidelinesMapper guidelinesMapper;

    /**
     * select   查询单条by id
     *
     * @param id
     * @return
     */
    public Guidelines select(Integer id) {
        Guidelines guidelines = guidelinesMapper.selectByPrimaryKey(id);
        return guidelines;
    }

    /**
     * select
     * 查所有的domain
     *
     * @return List
     */
    public List<Guidelines> findGL(Guidelines guidlinesDomain, YogmsPage yogmsPage, Date startTime, Date endTime) throws Exception {

        // 获取总页数
        com.github.pagehelper.Page total = PageHelper.startPage(yogmsPage.getCurrent(), yogmsPage.getPageSize(), true);
        PageHelper.orderBy("hot_degree desc");
        List<Guidelines> guidelines = guidelinesMapper.getDomainCollectionExtend(guidlinesDomain, startTime, endTime);
//        try {
//            for (Guidelines result : guidelines) {
//                String guidelinesTitle = result.getGuidelinesTitle();
//                String guidelinesContent = result.getGuidelinesContent();
//                if (guidelinesTitle.length() > 20) {
//                    guidelinesTitle = guidelinesTitle.substring(0, 20) + "...";
//                    //  这里有错哦，字符串长度为4，你取50个，是啊，StringIndexOutOfBoundsException
////                    guidelinesContent = guidelinesContent.substring(0,50) + "...";
//                    // 必须给domain设置才行吧!  哦哦
//                    result.setGuidelinesTitle(guidelinesTitle);
//
//                }
//                if(guidelinesContent.length() > 50){
//                    guidelinesContent = guidelinesContent.substring(0,50) + "...";
//                    result.setGuidelinesContent(guidelinesContent);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        yogmsPage.setTotalPage(total.getTotal() % yogmsPage.getPageSize() == 0 ? total.getTotal() / yogmsPage.getPageSize() : total.getTotal() / yogmsPage.getPageSize() + 1);

        return guidelines;
    }






//    public List<Guidelines> findPageGL(YomsPage yomsPage) throws  Exception{
//
//        com.github.pagehelper.Page total = PageHelper.startPage(yomsPage.getPageIndex(), yomsPage.getPageSize(), true);
//        PageHelper.orderBy("hot_degree desc");
//        List<Guidelines> guidelines = guidelinesMapper.getDomainCollectionExtend()
//    }




    /**
     * create
     * 创建
     *
     * @return int
     */
    public int createGL(Guidelines guidelines) throws Exception {
        return guidelinesMapper.insertSelective(guidelines);
    }

    /**
     * update
     * 更新
     */
    @Transactional
    public int updateGL(Guidelines guidelines) throws Exception {
        int result = guidelinesMapper.updateByPrimaryKey(guidelines);
        if (result != 1) {
            System.out.println("更新失败");
        }
        return result;
    }

    /**
     * delete
     * 删除
     */
    @Transactional
    public int deleteGL(Integer id) throws Exception {
        int result = guidelinesMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            System.out.println("删除失败");
            throw new Exception("删除失败");
        }
        return result;
    }
}
