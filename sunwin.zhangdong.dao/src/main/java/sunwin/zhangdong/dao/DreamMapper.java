package sunwin.zhangdong.dao;

import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.Dream;

@Repository
public interface DreamMapper {
    // 删:by id
    int deleteByPrimaryKey(Integer id);     //  属性：主键

    // 增: object
    int insert(Dream record);             //  domain
    // object
    int insertSelective(Dream record);    //  domain

    // 查：id
    Dream selectByPrimaryKey(Integer id);    // 属性：主键

    // 改：object
    int updateByPrimaryKeySelective(Dream record);   // domain

    int updateByPrimaryKey(Dream record);           // domain
}