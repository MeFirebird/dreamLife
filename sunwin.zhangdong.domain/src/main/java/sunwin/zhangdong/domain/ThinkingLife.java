package sunwin.zhangdong.domain;

import java.util.Date;

public class ThinkingLife {
    private Integer id;

    private String thinkingTitle;

    private String thinkingContent;

    private String thinkingType;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThinkingTitle() {
        return thinkingTitle;
    }

    public void setThinkingTitle(String thinkingTitle) {
        this.thinkingTitle = thinkingTitle == null ? null : thinkingTitle.trim();
    }

    public String getThinkingContent() {
        return thinkingContent;
    }

    public void setThinkingContent(String thinkingContent) {
        this.thinkingContent = thinkingContent == null ? null : thinkingContent.trim();
    }

    public String getThinkingType() {
        return thinkingType;
    }

    public void setThinkingType(String thinkingType) {
        this.thinkingType = thinkingType == null ? null : thinkingType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}