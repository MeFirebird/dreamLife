package sunwin.zhangdong.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.dao.ThinkingLifeMapper;
import sunwin.zhangdong.domain.ThinkingLife;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
@Service
public class ThinkingLifeService {
    @Autowired
    private ThinkingLifeMapper thinkingLifeMapper;

    /**
     * 查
     */
    public List<ThinkingLife> findTL(ThinkingLife thinkingLife, YogmsPage yogmsPage, Date startTime, Date endTime)
            throws  Exception{

        com.github.pagehelper.Page total = PageHelper.startPage(yogmsPage.getCurrent(), yogmsPage.getPageSize(), true);
        PageHelper.orderBy("create_time");
        List<ThinkingLife> thinkingLifes = thinkingLifeMapper.getDomainCollectionExtend(thinkingLife,startTime,endTime);

        yogmsPage.setTotalPage(total.getTotal() % yogmsPage.getPageSize() == 0 ? total.getTotal() / yogmsPage.getPageSize() : total.getTotal() / yogmsPage.getPageSize() + 1);
        return thinkingLifes;
    }

    /**
     *  查询单条By id
     */
    public ThinkingLife selectById(Integer id){
        return thinkingLifeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查总条数
     */
    public int getCount(){
        return thinkingLifeMapper.getCount();
    }


    /**
     * update
     * 更新
     */
    @Transactional
    public int updateGL(ThinkingLife thinkingLife) throws Exception {
        int result = thinkingLifeMapper.updateByPrimaryKey(thinkingLife);
        if (result != 1) {
            System.out.println("更新失败");
        }
        return result;
    }


    /**
     * 增
     */
    public int addTL(ThinkingLife thinkingLife){
        return thinkingLifeMapper.insert(thinkingLife);
    }

    /**
     * 删
     */
    @Transactional
    public int deleteGL(Integer id) throws Exception {
        int result = thinkingLifeMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            System.out.println("删除失败");
            throw new Exception("删除失败");
        }
        return result;
    }
}
