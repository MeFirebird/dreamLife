/**
 * Created by Administrator on 2016/11/25.
 */
$(function () {


    (function ($) {
        /**
         * 将选择的图片呈现到img标签中
         * @param img img标签
         * @param alerts 显示错误容器
         */
            //定义一个全局函数能被其他地方引用
        $.fn.showImg = function (img, alerts) {
            //  读取文件信息
            var fileInfo = $(this).getFileInfo();
            //  判断文件是否是图片      完成和失败
            if (/^image\//.test(fileInfo.type)) {
                $.when(readFileIntoDataUrl(fileInfo)).done(function (dataUrl) {
                    $(img).attr("src", dataUrl);
                    alert(dataUrl);
                }).fail(function (e) {
                    console.error("呈现用户选择的图片失败");
                });
            } else {
                $(alerts).showError("上传文件失败", "无法解析文件格式&nbsp;" + fileInfo.type);
                secs5Invoke(function () {
                    $(alerts).empty();     // 5s后错误提示消失。
                });
            }
        }
    })(jQuery);


    /**
     * 根据文件显示图片
     * @param fileInfo
     * @returns {*}
     */
    var readFileIntoDataUrl = function (fileInfo) {
        var loader = $.Deferred(),    // // 生成Deferred对象
            fReader = new FileReader();
        fReader.onload = function (e) {
            loader.resolve(e.target.result);
        };
        //调用后将deferred对象的运行状态变为"已失败"，从而立即触发fail()方法。
        fReader.onerror = loader.reject;
        fReader.onprogress = loader.notify;
        fReader.readAsDataURL(fileInfo);
        return loader.promise();
    };

    /*在指定的时间后执行一段函数  */
    function secs5Invoke(fn) {
        setTimeout(function () {
            if (fn != null) {
                fn();
            }
        }, 5000);
    }

    /**
     * 获取文件信息
     * @returns {*}
     */
    $.fn.getFileInfo = function () {
        var fileType = null;
        $.each(this[0].files, function (idx, fileInfo) {
            fileType = fileInfo;
        });
        return fileType;
    };

    /**
     * 显示错误
     * @param alerts 显示错误的容器
     * @param title 错误标题
     * @param msg 错误消息
     */
    $.fn.showError = function showError(title, msg) {
        $('<div class="alert alert-error"> <button type="button" class="close" data-dismiss="alert">&times;</button><strong>' + title + '</strong> ' + msg + ' </div>').prependTo(this);
        return this;
    };


//加载操作人选择的图片
    $("#ffileInput").change(function () {    // 当上传图片的input组件value改变的时候
        // #fimg:图片容器
        $(this).showImg($("#fimg"), $("#falerts"));
        $(this).val(null);   // 输入框值清零
    });

});