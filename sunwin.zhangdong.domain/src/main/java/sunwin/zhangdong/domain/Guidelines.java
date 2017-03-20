package sunwin.zhangdong.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Guidelines {
    private Integer id;

    private String guidelinesTitle;

    private Date createTime;

    private String guidelinesContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getGuidelinesTitle() {
        return guidelinesTitle;
    }

    public void setGuidelinesTitle(String guidelinesTitle) {
        this.guidelinesTitle = guidelinesTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGuidelinesContent() {
        return guidelinesContent;
    }

    public void setGuidelinesContent(String guidelinesContent) {
        this.guidelinesContent = guidelinesContent == null ? null : guidelinesContent.trim();
    }
}