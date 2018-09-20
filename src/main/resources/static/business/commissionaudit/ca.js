function queryOnLoad(businessPeopleId,settlementId,state,businessPeopleName,businessPeopleLevelName) {
    $("#search_businessPeopleId").attr("value", businessPeopleId);
    $("#search_settlementId").attr("value",settlementId);
    $("#search_state").attr("value", state);

    $("#businessPeopleId").attr("value", businessPeopleId);
    $("#businessPeopleName").attr("value",businessPeopleName);
    $("#businessPeopleLevel").attr("value", businessPeopleLevelName);

    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 315
            ,url: 'commissionManage/queryCommissionManageDetail' //数据接口
            ,method:'post'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                ,{field: 'applyName', title: '客户姓名'}
                ,{field: 'applyMobile', title: '客户手机号'}
                ,{field: 'applyProduct', title: '产品名称'}
                ,{field: 'commissionCreateDate', title: '佣金生成时间'}
                ,{field: 'commissionAmount', title: '分佣金额'}
            ]]
            ,where : {
                businessPeopleId : $('#search_businessPeopleId').val(),
                settlementId : $('#search_settlementId').val(),
                state : $('#search_state').val()
            }
        });

    });
}


