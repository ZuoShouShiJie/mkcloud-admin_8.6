
function queryUserId(id,orgCode){
    layui.use('table', function () {
        var table = layui.table;
        $.ajax({
            url: "operator/queryRoleByUserId",
            async: true,
            type: "POST",
            dataType: "json",
            data: {
                "id": $('#id').val(),
                "orgCode": $("#orgCode").val()
            },
            success: function (result) {
                $("#demo").html("");
                var dataFirst = '';
                var data = result.data;
                var listById = data.mkCloudRoleInfoList;
                var listAllFirst = data.allRoleInfo;
                for (var o in listAllFirst) {
                    dataFirst = dataFirst + "<tr><td><input class='role' id="+listAllFirst[o].id+" type='checkbox'></td><td>" + listAllFirst[o].id + "</td>"+ "<td>" + listAllFirst[o].roleName + "</td>" + "</tr>"
                }
                // $("#demo").append("<table class='layui-table'> <thead> <tr> <th id='payxf'></th> <th>菜单id</th><th>菜单名</th> </tr> </thead> <tbody>"+dataFirst+"</tbody></table>");
                $("#demo2").append("<table class='layui-table'> <thead> <tr> <th ></th>  <th>角色id</th><th>角色名</th> </tr> </thead> <tbody>"+dataFirst+"</tbody></table>");

                for (var t in listById) {
                    $("#"+listById[t].id).attr('checked',true);
                }
                /*
                for (var j in listAllSecond) {
                    $('#'+listAllSecond[j].menuId).on('click',function() {

                        if (this.checked == true) {
                            $('#'+listAllSecond[j].parentMenuId).prop('checked', true);
                        }
                    });
                }*/





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

function queryUserTest(){
    layui.use('form', function () {
    var form = layui.form;
    var $ = layui.$;//获取jquery组件
    form.render();
    });
    }



