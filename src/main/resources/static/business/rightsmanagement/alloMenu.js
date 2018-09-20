function queryOnLoad(id) {
    layui.use('table', function () {
        var table = layui.table;

        $.ajax({
            url: "operator/queryMenuByRoleId",
            async: true,
            type: "get",
            dataType: "json",
            data: {
                "id": $('#id').val()
            },
            success: function (result) {
                $("#demo").html("");
                var dataFirst = '';
                var dataSecond='';
                var data = result.data;
                var listById = data.mkCloudMenuListVOS;
                var listAllSecond = data.allMenuSecondListVo;
                  for (var j in listAllSecond) {
                      dataSecond=dataSecond+"<tr><td><input class='dd' id="+listAllSecond[j].id+" type='checkbox'></td><td>" + listAllSecond[j].menuId + "</td><td>" + listAllSecond[j].menuName + "</td><td>" + listAllSecond[j].parentMenuId +"</td><td>"+listAllSecond[j].parentMenuName+"</td></tr>";
                 }

                // $("#demo").append("<table class='layui-table'> <thead> <tr> <th id='payxf'></th> <th>菜单id</th><th>菜单名</th> </tr> </thead> <tbody>"+dataFirst+"</tbody></table>");
                $("#demo2").append("<table class='layui-table'> <thead> <tr> <th ></th>  <th>菜单id</th><th>菜单名</th><th>父级菜单id</th><th>父级菜单名</th> </tr> </thead> <tbody>"+dataSecond+"</tbody></table>");
             for (var t in listById) {
                    $("#"+listById[t].id).attr('checked',true);
                }
            },
            error: function () {
                alert("请求失败");
            },
        });

        //第一个实例
        /*    table.render({
         elem: '#demo'
         , height: 315
         , url: 'operator/queryMenuByRoleId' //数据接口
         , method: 'get'
         , page: false //开启分页
         , cols: [[ //表头
         {type:'checkbox',field: 'menuId', title: '用户code'}
         , {field: 'menuName', title: '用户名'}
         ]]
         , where: {
         id: $('#id').val()
         }
         });*/
    });

}





