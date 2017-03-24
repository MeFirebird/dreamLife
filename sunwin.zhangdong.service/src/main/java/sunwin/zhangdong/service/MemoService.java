package sunwin.zhangdong.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunwin.zhangdong.comm.YogmsPage;
import sunwin.zhangdong.dao.MemoMapper;
import sunwin.zhangdong.domain.Memo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
@Service
public class MemoService {

    @Autowired
    private MemoMapper memoMapper;


    /**
     *
     * @param memo
     * @param yogmsPage     分页和条件查询
     * @return
     */
    public List<Memo> getMemos(Memo memo, YogmsPage yogmsPage){
        // 获取总页数
        com.github.pagehelper.Page total = PageHelper.startPage(yogmsPage.getCurrent(), yogmsPage.getPageSize(), true);
//        PageHelper.orderBy("");
        // 分页拦截器：查询语句必须在上面哦！
        List<Memo> memos =  memoMapper.getMemos(memo);
        yogmsPage.setTotalPage(total.getTotal() % yogmsPage.getPageSize() == 0 ? total.getTotal() / yogmsPage.getPageSize() : total.getTotal() / yogmsPage.getPageSize() + 1);
        return memos;
    }

    /**
     * 查询所有的类型哦
     * @return
     */
    public List<String> getTypes(){
        return memoMapper.getTypes();
    }


    /**
     * 创建domain
     * @param memo
     * @return
     */
    public int saveMemo(Memo memo){
        memo.setId(memoMapper.getMaxId() + 1);
        return memoMapper.insertSelective(memo);
    }


    /**
     * 查询一条memo
     */
    public Memo getMemoById(int id){
        return memoMapper.getMemoById(id);
    }


    /**
     *   编辑后保存memo
     * @param memo
     * @return
     */
    public void updateMemo(Memo memo) throws Exception{

          if(memoMapper.update(memo) != 1){
             throw new Exception("编辑更新失败！");
          }
    }

    /**
     *
     * @param ids
     */
    @Transactional
    public void deleteMemo(Integer[] ids){
        memoMapper.deleteMemo(ids);
    }


}
