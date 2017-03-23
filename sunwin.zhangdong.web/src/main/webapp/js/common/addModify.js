$(function () {
    $("#reserveContent").click(function () {
        $('#saveFrom')[0].reset();
    });
});


/**
 * 公共方法保存数据                  ajax
 * @param url 添加或修改的url
 * @param formName 表单名
 * @param toListUrl 修改成功后跳转页面url
 */
function saveData(url, formName, toListUrl) {
    $.ajax({
        type: "POST",
        url: url,
        cache: false,	//禁用缓存
        data: $("#" + formName).serialize(),	//传入已封装的参数    String类型
        dataType: "json",
        //contentType:'application/json',
        success: function (data) {        //  date,textStatus
            alert("成功!");
            //$("#" + formName)[0].reset();
            console.log($('#' + formName).serialize());
            window.location.href = "/" + toListUrl;
        },

        error: function (XMLHttpRequest, textStatus, errorObject) {  //XMLHttpRequest对象、错误信息、错误对象
            if (XMLHttpRequest.responseText != '') {
                console.log(xhr);
                console.log($("#" + formName).serialize());
                console.log(textStatus);
                alert("出错了");
                alert(decodeURIComponent(XMLHttpRequest.responseText));
            }
        }
    });
}


/**
 * 保存有上传文件时提交的信息
 * @param url 请求地址
 * @param formName 表单id
 * @param param  参数
 * @param type 类型 1：添加 2：修改
 * @param toListUrl 修改后跳转的url
 */
function savePhotoData(url, formName, param,type,toListUrl) {

    $("#" + formName).ajaxSubmit({
        type: 'post',
        url: url,
        data: param,
        iframe: param.existFile,
        success: function (data, status) {
            console.log(data);
            console.log(data.URL);
            //if (data.URL != undefined) {
            if(type == 1){
                alert("添加成功!");
            }else{
                alert("修改成功！");
            }
            $("#" + formName).resetForm();
            window.location.href =  "/" + toListUrl;
        },
        error: function (XMLHttpRequest) {
            if (XMLHttpRequest.responseText != '') {
                alert(decodeURIComponent(XMLHttpRequest.responseText));
            } else {
                alert("请求服务器失败!");
            }
        }
    });
}


/**
 * ajax验证数据用的函数
 * @param url 请求地址
 * @param param 请求参数
 * @param message 提示信息
 * @param objId 对象id
 */
function validateInfo(url, param, message, objId) {
    $.ajax({
        type: "POST",
        url: url,
        cache: false,	//禁用缓存
        data: param,	//传入已封装的参数
        dataType: "json",
        success: function (data) {
            if (data.status) {
                if (message != null && message != '')
                    alert(message);

                $('#' + objId).val('');
                $('#' + objId).focus();
            }
        },
        error: function (XMLHttpRequest) {
            if (XMLHttpRequest.responseText != '') {
                alert(decodeURIComponent(XMLHttpRequest.responseText));
            } else {
                alert("请求服务器失败!");
            }
        }
    });
}