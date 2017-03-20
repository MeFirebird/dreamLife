package sunwin.zhangdong.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunwin.zhangdong.comm.StringUtil;
import sunwin.zhangdong.dao.ModuleMapper;
import sunwin.zhangdong.domain.Module;
import sunwin.zhangdong.web.comm.FileHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */

@Service
public class ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;


    /**
     *  根据id查询module
     * @param id
     * @return
     */
    public Module getOneModule(Integer id){
        return  moduleMapper.getOneDomain(id);
    }


    /**
     *  保存module
     * @param module
     * @return
     */
    public int saveModule(Module module){
        int maxId = moduleMapper.getMaxId();
        module.setId(maxId + 1);
        return moduleMapper.insert(module);   //  这里用insert  不用insertSelective
    }


    /**
     *
     * @param module
     * @return
     */
    public int modifyModule(Module module){
        return moduleMapper.insertSelective(module);
    }

    /**  查询所有的模块
     *
     * @return
     */
    public List<Module> getAllModules(){

        return moduleMapper.getAllModules();
    }



    /**
     * 删除信息
     * @param ids
     * @return                                 事务  方法锁       删除图片文件  删除domain
     * @throws Exception
     */
    @Transactional
    public synchronized void deleteModule(HttpServletRequest request, Integer[] ids) throws Exception{
        //将相关的图片删除
        int length=ids.length;
        String root=request.getServletContext().getRealPath("/");
        for(int i=0;i<length;i++){
            String photoUrl=moduleMapper.getOneDomain(ids[i]).getImg();
            //如果图片存在将其删除
            if(StringUtil.isNotEmpty(photoUrl)){        // 这里要注意路径的一致性问题：上传和删除
                FileHelper.delFile(root+"/"+photoUrl);
            }
        }
        moduleMapper.deleteModules(ids);

    }


}


