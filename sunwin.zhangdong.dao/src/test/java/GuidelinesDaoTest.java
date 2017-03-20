import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import sunwin.zhangdong.dao.GuidelinesMapper;
import sunwin.zhangdong.domain.Guidelines;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
@ContextConfiguration("/sunwin.yoms.dao.test.xml")
public class GuidelinesDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private GuidelinesMapper guidelinesMapper;

    @Test
    public void selectAll(){
        Guidelines guidelines = new Guidelines();
        List<Guidelines> guidelinesList = guidelinesMapper.getDomainCollectionExtend(guidelines,null,null);

        System.out.println(guidelinesList.get(2).getGuidelinesTitle());
    }
}
