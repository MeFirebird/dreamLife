package sunwin.zhangdong.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.Guidelines;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public interface GuidelinesMapper {

    int deleteByPrimaryKey(Integer id);                 // 删除是主键   一个属性字段

    int insert(Guidelines record) throws  Exception;     // 插入当然是domain了
    int insertSelective(Guidelines record);

    Guidelines selectByPrimaryKey(Integer id);           // 主键：属性

    // 查询所有的
    List<Guidelines> getDomainCollectionExtend(          // domain
            @Param("domain") Guidelines domain,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );



    int updateByPrimaryKeySelective(Guidelines record) throws SQLException;     // domain
    int updateByPrimaryKey(Guidelines record);        // domain
}