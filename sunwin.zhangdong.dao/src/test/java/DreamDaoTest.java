import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import sunwin.zhangdong.dao.DreamMapper;
import sunwin.zhangdong.domain.Dream;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Administrator on 2016/11/7.
 *
 * Spring的单元测试框架
 */

@ContextConfiguration("/sunwin.yoms.dao.test.xml")
public class DreamDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DreamMapper dreamMapper;

    @Test   // 增:每执行一次，就向数据库表里增加一个字段
    public void add(){
        Dream dream = new Dream();
//        dream.setId(6);
        dream.setAge(24);
        dream.setDream("hello shenzhen! here I come!");
        int result =  dreamMapper.insert(dream);
        assertEquals(1,result);
    }

    @Test  // 增
    public void addByCondition(){
        Dream dream = new Dream();
        dream.setName("NB");
        int result =  dreamMapper.insertSelective(dream);
        assertEquals(1,result);
    }

    @Test  // 删:
    public void delete(){
        dreamMapper.deleteByPrimaryKey(8);
    }

    @Test  // 查
    public void find(){
        Dream dream = dreamMapper.selectByPrimaryKey(5);
        System.out.println(dream.getAge() + " " + dream.getName() + " " + dream.getDream());
    }


    @Test   // 改
    public void update(){
        Dream dream = dreamMapper.selectByPrimaryKey(4);
        dream.setName("张冬");
        dreamMapper.updateByPrimaryKey(dream);
    }

    @Test  // 局部改
    public void updateyByCondition(){
        Dream dream = dreamMapper.selectByPrimaryKey(1);
        dream.setName("火影卡卡西");
        dreamMapper.updateByPrimaryKeySelective(dream);
    }
}
