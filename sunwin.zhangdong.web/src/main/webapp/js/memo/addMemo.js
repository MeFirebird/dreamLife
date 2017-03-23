

function saveMemo(type){

    // 前端非空判断提醒
    var title = $("#title").val();
    var content = $("#content").val();
    var url = $("#saveUrl").val();
    var listUrl = $("#toListUrl").val();
    if(title == ''){
        alert("请输入备忘标题");
        return;
    }else if(content = ''){
        alert("请输入备忘内容");
        return;
    }
    // 发起ajax请求
    saveData(url,"saveFrom",listUrl);

}