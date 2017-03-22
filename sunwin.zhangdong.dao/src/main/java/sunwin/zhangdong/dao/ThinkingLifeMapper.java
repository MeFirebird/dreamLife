package sunwin.zhangdong.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.ThinkingLife;

import java.util.Date;
import java.util.List;


@Repository
public interface ThinkingLifeMapper {

    // 插入
    int insert(ThinkingLife record);
    // 有选择插入
    int insertSelective(ThinkingLife record);


    // 删除by id     只有id是可以唯一标识一条记录的哦
    int deleteByPrimaryKey(Integer id);


    // 修改
    int updateByPrimaryKeySelective(ThinkingLife record);
    // 有选择修改
    int updateByPrimaryKey(ThinkingLife record);



    // 条件查询
    List<ThinkingLife> getDomainCollectionExtend(
            @Param("domain") ThinkingLife domain,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );
    // 查询by id
    ThinkingLife selectByPrimaryKey(Integer id);
    // 查询总数
    int  getCount();

}