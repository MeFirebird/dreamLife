package sunwin.zhangdong.service.common;


import org.springframework.stereotype.Service;
import sunwin.zhangdong.comm.YogmsPage;

/**分页处理公共类
 * Created by wangjs on 2016/10/26.
 */
@Service
public class CommonService {

//    public YogmsPage handlerYogmsPage(YogmsPage yogmsPage, String id){
//        if(yogmsPage!=null){
//            int current=yogmsPage.getCurrent();
//            int pageSize=yogmsPage.getPageSize();
//            int startIndex=(current-1)*pageSize;//起始页
//            yogmsPage.setStartIndex(startIndex);
//            if(yogmsPage.getOrderColumn()==null||yogmsPage.getOrderColumn().equals("undefined")){
//                yogmsPage.setOrderColumn(id);
//            }
//            if(yogmsPage.getOrderDir()==null||yogmsPage.getOrderDir().equals("undefined")){
//                yogmsPage.setOrderDir("DESC");
//            }
//        }
//        return yogmsPage;
//    }

    /**
     * 返回到页面的分页
     * @param yogmsPage
     * @param total 数据总量
     * @return
     */
    public YogmsPage resultYogmsPage(YogmsPage yogmsPage,int total){
        //根据信息总条数和每页的size进行分页   获取到有多少页
        int totalPage=total%yogmsPage.getPageSize()==0 ? total/yogmsPage.getPageSize() : (total/yogmsPage.getPageSize()+1);
        yogmsPage.setTotalPage(totalPage);
        // 设置总条数
        yogmsPage.setTotal(total);
        return yogmsPage;
    }
}
