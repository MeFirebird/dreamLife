package sunwin.zhangdong.comm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class PageUtil {
    /**
     * 分页                     从当前页码开始遍历10个分页button的值哦（pageNo  pageUrl）
     * @param yogmsPage         代码逻辑有问题！
     *
     *                          纠正：应该是 当前页码/10，  多创建些记录就知道问题所在了！
     * @param url
     * @param type 类型可选     有的内容模块有类型    有的没有类型哦！
     * @return
     */
    public List<PageEntity> getPageData(YogmsPage yogmsPage,String url,String type){
        List<PageEntity> pageList=new ArrayList<PageEntity>();  // 集合泛型哦
        long pageCount=yogmsPage.getTotalPage();   // 总页数
        int current=yogmsPage.getCurrent();        // 当前页码       注意这里哦！
        int num=(current-1)/10;
        int startNum=num*10+1;//起始页
        //  循环遍历10条记录哦！  结束：1.遍历了10条   2.不足10条。
        for(int i=startNum;i<=pageCount;i++){
            PageEntity pageEntity=new PageEntity();
            if(StringUtil.isNotEmpty(type)){
                // url拼接规则哦
                pageEntity.setPageUrl(url+"?type="+type+"&current="+i);   //  设置url
            }else{
                pageEntity.setPageUrl(url+"?current="+i);       // 设置url
            }
            pageEntity.setPageNo(i);    //  设置页码
            pageList.add(pageEntity);   // 添加到集合中
            if(i%10==0) break;
        }
        return pageList;
    }
}
