/**
 * 删除记录：点击button，遍历当前选中的记录，一次只能删除一条（暂时）！
 *          然后发送ajax请求与后台交互。
 *          后台拦截——》处理
 */
function toDeleteMemo() {
    var num = 0;  // 计数：被选中的记录
    var idArray = new Array();
    //var id;
    $("td input:checkbox").each(function () {

        if ($(this).is(":checked")) {
            var id = $(this).parent().next().text();
            idArray.push(id);
            num++;
            //id = $(this).parent().next().text();
        }

    });
    if(num < 1) {
        alert("请至少选中一条记录");
        return;
    }
    alert(idArray);

    if (confirm("确认要删除？")) {
        $.ajax({
            url: $("#deleteUrl").val(),
            type: "POST",
            data: "ids=" + idArray,
            dataType: "json",
            cache: false,
            success: function () {
                alert("删除成功!");
                location.reload();    // 知道要做什么，怎么做就有很多路径了！  因为有目的了！
            },
            error: function () {
                alert("删除失败！");
            }
        })
    }

}
