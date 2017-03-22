package sunwin.zhangdong.comm;

import org.springframework.stereotype.Component;

/**
 * Created by mt on 2015-8-22.
 */
@Component
public class NameAndValueModel {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
