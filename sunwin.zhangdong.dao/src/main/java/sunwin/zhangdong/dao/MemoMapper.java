package sunwin.zhangdong.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.Memo;

import java.util.List;


@Repository
public interface MemoMapper {

    int delete(Memo memo);
    int deleteMemo(Integer[] id);

    int insert(Memo memo);

    int getMaxId();

    // 查询所有的domain(条件和分页查询)
    List<Memo> getMemos(@Param("memo") Memo memo);

    //查询所有的备忘录类型
    List<String> getTypes();


    int insertSelective(Memo memo);

    Memo getMemoById(int id);

    int update(Memo memo);

}