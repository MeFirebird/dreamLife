package sunwin.zhangdong.domain;

public class Dream {
    private Integer id;    // 包装类型

    private String name;

    private Integer age;

    private String dream;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream == null ? null : dream.trim();
    }
}