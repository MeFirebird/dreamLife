package sunwin.zhangdong.comm;

import jodd.datetime.JDateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mt on 2015-8-24.
 *      日期处理确实是挺强大  而且简捷！！！！！！！！！！！！！！！！！！！！！
 */
@Component
public class YoisVelocityTools {


    private boolean test;

    /**
     * 转换java时间为yyyy-MM-dd HH:mm:ss
     * @param
     * @return
     */
    public String getDate(Date date) {
        JDateTime dateTime = new JDateTime(date);    // 根据Date来创建JDateTime对象
        return dateTime.toString("YYYY-MM-DD hh:mm:ss");
    }

    /**
     * 转换java时间为yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public String getDateTime(Date date){
        JDateTime dateTime = new JDateTime(date);
        return dateTime.toString("YYYY-MM-DD hh:mm");
    }

    /**
     * 转换java时间为hh:mm
     * @param date
     * @return
     */
    public String getTime(Date date){
        JDateTime dateTime=new JDateTime(date);
        return dateTime.toString("hh:mm");
    }

    public String getMonth(Date date){
        JDateTime dateTime=new JDateTime(date);
        return dateTime.toString("MM");
    }
}
