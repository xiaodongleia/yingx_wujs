<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        //初始化一个表格
        $("#feedbackTable").jqGrid({
            url : "${path}/feedback/queryByPager", //接收    page:当前页   rows：每页展示条数
            editurl:"${path}/feedback/edit",
            datatype : "json",              //返回      page:当前页   rows：数据(List）  total：总页数   records:总条数
            rowNum : 2,
            rowList : [ 2,5,10, 20, 30 ],
            pager : '#feedbackPage',  //工具栏
            viewrecords : true,   //是否显示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'ID', '标题', '内容', '用户id', '反馈时间' ],
            colModel : [
                {name : 'id',width : 30},
                {name : 'title',width : 80},
                {name : 'content',width : 80,align:"center"},

                {name : 'userId',width : 80,align : "right"},
                {name : 'saveDate',editable:true,width : 80,align : "right"}

            ]
        });

        // //表格工具栏
        // $("#feedbackPage").jqGrid('navGrid', '#feedbackPage',
        //     {edit : true,add : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
        //     {
        //
        //     },   //修改之后的额外操作
        //     {
        //         closeAfterAdd:true,  //关闭对话框
        //
        //     },   //添加
        //     {}    //删除
        // );

    });



</script>

<%--初始化一个面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>


    <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">用户信息</a></li>
    </div>

    <%--按钮--%>
    <button class="btn btn-info">导出用户信息</button>

    <%--初始化表单--%>
    <table id="feedbackTable" />

    <%--分页工具栏--%>
    <div id="feedbackPage" />

</div>