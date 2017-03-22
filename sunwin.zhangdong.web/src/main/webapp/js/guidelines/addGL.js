
function saveMenu(type){

    var name = $("#name").val();   // 获取模块名称
    var title = $("#title").val(); // 获取模块标题
    var imgbase64 = $("#fimg").attr("src"); //   获取图片的src属性
    alert(imgbase64);
    // 发送ajax请求
    var url=$('#saveUrl').val();
    alert(url);
    if(name == ''){
        alert("请输入模块名称");
        return false;
    }else if(title == ''){
        alert('请输入模块标题');
        return false;
    }
    var param = {'imgbase64':imgbase64};
    savePhotoData(url,"saveFrom",param,type,$('#toListUrl').val());
}
