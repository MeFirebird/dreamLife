package sunwin.zhangdong.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/21.
 */
@Component
public class Memo {

    private Integer id;
    private String title;        // 备忘title
    private String content;      // 备忘内容
    private Date createTime;     // 创建时间
    private Date finishTime;     // 完成时间
    // 备忘执行优先级      重要且紧急的domain加红字显示       1:重要且紧急   2.紧急
    private int level;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
