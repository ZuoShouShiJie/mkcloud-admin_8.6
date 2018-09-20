function queryOn(businessPeopleId,state,applyCardTime) {
        layui.use('table', function () {
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                , height: 315
                , url: 'applicationImportDetail/selectByApplicationImport' //数据接口
                , method: 'post'
                , page: true //开启分页
                , cols: [[ //表头
                    // {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                    /*{field: 'applyId', title: '客户编码'}*/
                     {field: 'applyName', title: '客户姓名'}
                    , {field: 'applyMobile', title: '客户手机号'}
                    , {field: 'applyBank', title: '办卡银行'}
                    , {field: 'applyProduct', title: '信用卡卡种'}
                    , {field: 'applyCardTime', title: '客户推广日期'}
                    , {field: 'applyCardTime', title: '申请通过日期'}
                    , {field: 'auditStatus', title: '办卡状态'}
                    /*, {field: '', title: '信用卡卡号'}*/
                ]]
                , where: {
                    businessPeopleId: $('#business_people_code').val(),
                    state: $('#state').val(),
                    applyCardTime:applyCardTime
                }
            });
        });
        /*$.get("question/queryQuesttionById", {
            id : id
        }, function(result) {
            if (true) {
                $("#qaid").attr("value",id);
                if (result!=null && result.data!=null) {
                    $("#title").attr("value", result.data.title);
                    ue.setContent(result.data.content);
                    $("#seqNo").attr("value", result.data.seqNo);
                }
            } else {
                alert("请求失败");
            }
        });*/


        /*$.ajax({
            url : "question/queryQuesttionById",
            async : true,
            type : "GET",
            data : {
                "id" : id
            },
            success : function(data) {
                $("#qaid").attr("value",id);
                if (data!=null && data.data!=null) {
                    $("#title").attr("value", data.data.title);
                    ue.setContent(data.data.content);
                }
            },
            error : function() {
                alert("请求失败");
            },
            dataType : "json"
        });*/


}


function queryOnLoad(businessPeopleId,state) {
    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            , height: 315
            , url: 'applicationImportDetail/selectByApplicationImport' //数据接口
            , method: 'post'
            , page: true //开启分页
            , cols: [[ //表头
                // {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                // {field: 'applyId', title: '客户编码'}
                {field: 'applyName', title: '客户姓名'}
                , {field: 'applyMobile', title: '客户手机号'}
                , {field: 'applyBank', title: '办卡银行'}
                , {field: 'applyProduct', title: '信用卡卡种'}
                , {field: 'applyCardTime', title: '客户推广日期'}
                , {field: 'applyCardTime', title: '申请通过日期'}
                , {field: 'auditStatus', title: '办卡状态'}
                /*, {field: '', title: '信用卡卡号'}*/
            ]]
            , where: {
                businessPeopleId: $('#business_people_code').val(),
                state: $('#state').val()

            }
        });

    });
}
    function advancePayment(beginDate,endDate) {
        layui.use('table', function () {
            var table = layui.table;
            //第一个实例
            table.render({
                elem: '#demo'
                , height: 315
                , url: 'PrepaymentRuleConfiguration/applicationImportDetailList' //数据接口
                , method: 'post'
                , page: true //开启分页
                , cols: [[ //表头
                    // {field: 'seqNo', title: '序号', width:60,type:'numbers'}
                    // {field: 'applyId', title: '客户编码'}
                    {field: 'cusName', title: '客户姓名'}
                    , {field: 'cusTel', title: '客户手机号'}
                    , {field: 'institutionName', title: '办卡银行'}
                    , {field: 'productName', title: '信用卡卡种'}
                    , {field: 'applyCardDate', title: '申请日期'}
                    , {field: 'auditStatus', title: '办卡状态'}
                    /*, {field: '', title: '信用卡卡号'}*/
                ]],
                where: {
                    beginDate: $('#beginDate').val(),
                    endDate: $('#endDate').val()
                }
            });
        });


    /*$.get("question/queryQuesttionById", {
        id : id
    }, function(result) {
        if (true) {
            $("#qaid").attr("value",id);
            if (result!=null && result.data!=null) {
                $("#title").attr("value", result.data.title);
                ue.setContent(result.data.content);
                $("#seqNo").attr("value", result.data.seqNo);
            }
        } else {
            alert("请求失败");
        }
    });*/


    /*$.ajax({
        url : "question/queryQuesttionById",
        async : true,
        type : "GET",
        data : {
            "id" : id
        },
        success : function(data) {
            $("#qaid").attr("value",id);
            if (data!=null && data.data!=null) {
                $("#title").attr("value", data.data.title);
                ue.setContent(data.data.content);
            }
        },
        error : function() {
            alert("请求失败");
        },
        dataType : "json"
    });*/
}


