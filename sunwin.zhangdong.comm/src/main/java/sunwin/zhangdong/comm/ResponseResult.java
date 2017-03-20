package sunwin.zhangdong.comm;
import java.util.HashMap;

/**
 * 封装返回给页面的响应消息<br/>
 * 用于返回提示信息或数据
 *                              就算提供了静态方法，但类默认有无参数构造器哦！ 可以直接new
 * Created by wangjs on 2016/5/11.
 */
public class ResponseResult extends HashMap<String, Object> {

    // 相当于java类的身份证。主要用于版本控制。
    private static final long serialVersionUID = 2684238112502318611L;

    private static ResponseResult responseResult;

    /**
     * 获取一个新的ResponseResult对象
     *
     * @return
     */
    public static ResponseResult newInstance() {

        return new ResponseResult();
    }

    /**
     * 获取当前已存在的ResponseResult对象，如果不存在则实例化一个新对象
     *
     * @return
     */
    public static ResponseResult getResponseResult() {

        if (responseResult != null) {    // 是否new过
            return responseResult;
        }
        return newInstance();
    }
}
