package sunwin.zhangdong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunwin.zhangdong.dao.DreamMapper;
import sunwin.zhangdong.domain.Dream;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/2.
 */
@Service
public class DreamService {
    @Autowired
    private DreamMapper dreamMapper;

    /**
     * 增：  创建domain的目的是为了给dao层的方法传递参数哦！
     * @throws Exception
     */
    public void addDomain() throws Exception{
        Dream dream = new Dream();
        dream.setAge(24);
        dream.setDream("修行不易，坚持挺住；梦想就在不远处哦！");
        int result =  dreamMapper.insert(dream);
        if(result <= 0){
            System.out.println("增加数据记录失败。。。");
        }
    }

    public Dream select(int id) throws IOException {
        Dream dream = dreamMapper.selectByPrimaryKey(id);
        return  dream;
    }
}
