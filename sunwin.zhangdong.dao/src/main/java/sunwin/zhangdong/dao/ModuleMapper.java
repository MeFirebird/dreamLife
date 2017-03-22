package sunwin.zhangdong.dao;

import org.springframework.stereotype.Repository;
import sunwin.zhangdong.domain.Module;

import java.util.List;

@Repository
public interface ModuleMapper {

    int insert(Module record);

    int insertSelective(Module record);

    Module getOneDomain(Integer id);

    int getMaxId();

    List<Module> getAllModules();

    int deleteModules(Integer[] id);

    int updateModules(Module module);

}