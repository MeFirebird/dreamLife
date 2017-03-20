package sunwin.zhangdong.web.comm;

import org.springframework.stereotype.Component;

/**  系统所有的请求URL
 * Created by Administrator on 2016/11/8.
 *
 *                           静态变量为何还需要get方法呢？  这是为何？
 */
@Component
public class R {
    // 前台界面   begin    这里定义的是请求路径
    public static final String Index = "dream-heaven.html";
    public static final String Career = "career.html";
    public static final String GuideLines = "guidelines.html";
    public static final String Hobby = "hobby.html";
    public static final String ThinkingLife = "thinking-life.html";

    public static String getIndex(){
        return Index;
    }
    public static String getCareer(){
        return Career;
    }
    public static String getGuideLines() {
        return GuideLines;
    }
    public static String getHobby() {
        return Hobby;
    }
    public static String getThinkingLife() {
        return ThinkingLife;
    }



    /**************  后台 begin  ***********************/

    //用户管理
    public static final String  UserList = "user.list.html";    // 用户列表页
    public static final String Login = "login.html";            // 登录界面
    public static final String LoginCheck = "loginCheck.html";  // 登录验证
    public static final String logout="logout.html";            // 登出
    public static final String register = "register.html";      // 注册
    public static final String userEdit = "user.edit.html";     // 修改个人资料页面
    public static final String ajaxEditName = "ajax.edit.name.json"; // 修改用户名请求url
    public static final String confirmPwd = "ajax.confirm.pwd.json"; // 确认密码
    public static final String ajaxEditPwd = "ajax.edit.pwd.json";  //  修改密码请求url
    // 后台首页
    public static final String DreamEdit = "dream-edit.html";
    // 指导方针
    public static final String FindGL = "guidelines.find.html";      //  指导方针列表页
    public static final String AddGL = "guidelines.create.html";              // 跳转到创建页面
    public static final String ajax_saveGL = "Ajax.guidelines.create.json";    // 保存指导方针
    public static final String ajax_deleteGL = "Ajax.guidelines.delete.json";
    public static final String EditGL = "guidelines.edit.html";               //跳转到编辑页面
    public static final String ajax_editGL = "Ajax.guidelines.edit.json";     //编辑并保存指导方针
    //关于工作
    public static final String FindCareer = "career.find.html";      // 关于工作列表页
    public static final String AddCareer = "career.create.html";     // 跳转到创建页面
    public static final String ajax_saveCareer = "Ajax.career.create.json";
    public static final String ajax_deleteCareer = "Ajax.career.delete.json";
    public static final String EditCareer = "career.edit.html";      // 跳转到编辑页面
    public static final String ajax_editCareer = "Ajax.career.edit.json";
    //兴趣爱好
    public static final String moduleList = "module.list.html";  //模块列表页
    public static final String moduleAdd = "module.add.html";    //模块创建页
    public static final String moduleEdit = "module.edit.html";   //模块编辑页
    public static final String ajax_saveModule = "Ajax.save.module.json";  // 保存模块
    public static final String ajax_deleteModule = "Ajax.delete.module.json";//删除模块


    // 生活随想录
    public static final String FindTL = "thinking.life.find.html";   // 记录页
    public static final String ajax_FindTL = "Ajax.thinkinglife.find.json";   // 条件查找
    public static final String AddTL = "thinking.life.create.html";  // 创建页
    public static final String ajax_saveTL = "Ajax.thinkinglife.create.json"; // 创建提交url
    public static final String ajax_deleteTL = "Ajax.thinkinglife.delete.json"; //  删除
    public static final String editTL = "Ajax.thinkinglife.html";     // 编辑
    public static final String ajax_editTL = "Ajax.thinkinglife.edit.json"; // 编辑并保存
    // 心路历程
    public static final String devProcess = "develop.process.html";



    // 用户管理
    public static String getLogin() {
        return Login;
    }
    public static String getLoginCheck() {
        return LoginCheck;
    }
    public static String getRegister() {
        return register;
    }
    public static String getUserList() {
        return UserList;
    }
    public static String getLogout() {
        return logout;
    }
    public static String getUserEdit() {
        return userEdit;
    }
    public static String getAjaxEditName() {
        return ajaxEditName;
    }
    public static String getConfirmPwd() {
        return confirmPwd;
    }
    public static String getAjaxEditPwd() {
        return ajaxEditPwd;
    }

    public static String getDreamEdit(){return DreamEdit;}


    // 指导方针
    public static String getAjax_saveGL() {
        return ajax_saveGL;
    }
    public static String getAddGL() {
        return AddGL;
    }
    public static String getAjax_deleteGL() {
        return ajax_deleteGL;
    }
    public static String getEditGL() {
        return EditGL;
    }
    public static String getAjax_editGL() {
        return ajax_editGL;
    }
    public static String getFindGL() {
        return FindGL;
    }

    //兴趣爱好
    public static String getModuleList() {
        return moduleList;
    }
    public static String getModuleAdd() {
        return moduleAdd;
    }
    public static String getModuleEdit() {
        return moduleEdit;
    }
    public static String getAjax_saveModule() {
        return ajax_saveModule;
    }

    public static String getAjax_deleteModule() {
        return ajax_deleteModule;
    }

    //生活随想录
    public static String getFindTL() {
        return FindTL;
    }
    public static String getAjax_FindTL() {
        return ajax_FindTL;
    }
    public static String getAddTL() {
        return AddTL;
    }
    public static String getAjax_saveTL() {
        return ajax_saveTL;
    }
    public static String getAjax_deleteTL() {
        return ajax_deleteTL;
    }
    public static String getEditTL() {
        return editTL;
    }
    public static String getAjax_editTL_() {
        return ajax_editTL;
    }

    // 心路历程
    public static String getDevProcess() {
        return devProcess;
    }


}
