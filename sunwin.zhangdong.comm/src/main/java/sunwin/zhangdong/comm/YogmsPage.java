package sunwin.zhangdong.comm;

import org.springframework.stereotype.Component;

/**
 * Created by mt on 2015/7/29
 */
@Component
public class YogmsPage {
    /**
     * 当前页码
     */
    private int current = 1;
    /**
     * 查询开始位置                 数据库中的表的条数来决定
     */
    private int startIndex;
    /**
     * 结束位置
     */
    private int endIndex;
    /**
     * 查询条数
     */
    private int pageSize = 10;
    /**
     * 排序名称
     */
    private String orderColumn;
    /**
     * 排序顺序
     */
    private String orderDir;
    /**
     * 总数量
     */
    private long total;
    /**
     * 总页数
     */
    private long totalPage;


    //  无参构造器
    public YogmsPage() {
    }

    public YogmsPage(int startIndex, int pageSize) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.endIndex = startIndex + pageSize;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}