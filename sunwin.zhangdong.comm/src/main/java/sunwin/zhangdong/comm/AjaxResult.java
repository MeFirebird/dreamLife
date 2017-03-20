package sunwin.zhangdong.comm;

import org.springframework.stereotype.Component;

/**
 * Created by wangjs on 2016/5/11.
 * 继承自ResponseResult
 * 封装Ajax请求的响应消息
 * 统一规范Ajax响应消息的内容            类的继承链哦
 */
@Component
public class AjaxResult extends ResponseResult{
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态
     *
     * @param status
     */
    public void setStatus(boolean status) {
        super.put("status", status);
    }
    /**
     * 响应消息
     *
     * @param message
     */
    public void setMessage(String message) {
        super.put("message", message);
    }

    /**
     * 响应对象
     *
     * @param obj
     */
    public void setResultObj(Object obj) {
        super.put("resultObj", obj);
    }

    /**
     * 自定义key，value的响应对象
     *
     * @param key
     * @param obj
     */
    public void setResultObj(String key, Object obj) {
        super.put(key, obj);
    }
}
