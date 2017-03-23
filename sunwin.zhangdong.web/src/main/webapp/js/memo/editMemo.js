// 跳转到修改页面
var url = $("#editUrl").val();
function toEditMemo(){
    var num = 0;   // 被选中的条目
    var id = "";
    $("td input:checkbox").each(function(){
        if($(this).is(":checked")){
            num ++;
            // 获取选中的记录id
            id = $(this).parent().next().text();
            alert(id);
        }
    });
    if(num !== 1){
        alert("请选择一条记录");
        return;
    }
    window.location.href="/"+url +"?id="+id;
}

