package sunwin.zhangdong.comm;

/**
 * Created by wangjs on 2016/11/30.
 */
public class PageEntity {
    //分页地址
    private String pageUrl;
    //页码
    private int pageNo;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
