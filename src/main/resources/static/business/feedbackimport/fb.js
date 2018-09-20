function queryBankOnLoad(batchId) {
    $("#search_batchId").attr("value", batchId);

    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 315
            ,url: 'feedBackInfoImport/queryfeedBackInfoImportDetail' //数据接口
            ,method:'post'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                ,{field: 'applyName', title: '客户姓名'}
                ,{field: 'applyMobile', title: '客户手机号'}
                ,{field: 'applyBank', title: '商户'}
                ,{field: 'applyProduct', title: '产品名称'}
                ,{field: 'applyCardDate', title: '商户审批日期'}
                ,{field: 'auditStatus', title: '申请状态'}
                ,{field: 'commission', title: '渠道总佣金'}
                ,{field: 'applyIdCard', title: '身份证号码'}

            ]]
            ,where : {
                method : $('#search_batchId').val()
            }
        });

    });

}
function queryVisitOnLoad(batchId) {
    $("#search_batchId").attr("value", batchId);

    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 315
            ,url: 'feedVisitackInfoImport/queryBatchDetailInfo' //数据接口
            ,method:'post'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                ,{field: 'applyName', title: '客户姓名'}
                ,{field: 'applyMobile', title: '客户手机号'}
                ,{field: 'applyBank', title: '商户'}
                ,{field: 'applyProduct', title: '产品名称'}
                ,{field: 'applyCardTime', title: '用户申请时间'}
                ,{field: 'auditStatus', title: '申请状态'}
                ,{field: 'businessPeopleCode', title: '业务人员工号'}
                ,{field: 'businessPeopleName', title: '业务人员姓名'}

            ]]
            ,where : {
                method : $('#search_batchId').val()
            }
        });

    });

}


