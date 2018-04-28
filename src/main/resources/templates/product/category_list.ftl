<#assign base=request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商品类目</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
    <style>/*样式*/
    .layui-layout-body {
        overflow: none !important;
    }
    </style>
</head>
<body class="layui-layout-body">

<div class="layui-btn-group addTable">
    <button class="layui-btn" data-type="getAddProduct">添加商品类目</button>
</div>

<table class="layui-table" id="categorTable" lay-data="{height:450, url:'${base}/category/categoryGrid',  id:'idTest'}"
       lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{field:'categoryId', width:80, sort: true, fixed: true}">类目ID</th>
        <th lay-data="{field:'categoryName', width:150}">类目名称</th>
        <th lay-data="{field:'categorySerial', width:80, sort: true}">排序ID</th>
        <th lay-data="{field:'categoryType', width:160 , templet: '#typeTpl'}">类目分类</th>
        <th lay-data="{field:'categoryModel', width:160 , templet: '#modelTpl'}">类型</th>
        <th lay-data="{field:'createTime', width:160,templet: '#dateTpl'}">创建时间</th>
        <th lay-data="{field:'updateTime', width:160,templet: '#dateTpl'}">修改时间</th>

        <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}">操作</th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="typeTpl">

    {{# if(d.categoryType == 1 ){
    return '精致正餐';
    }else if(d.categoryType ==2){
    return '小炒菜品';
    }
    }}

</script>
<script type="text/html" id="modelTpl">

    {{# if(d.categoryModel == 1 ){
    return '菜品';
    }else if(d.categoryModel ==2){
    return '套餐';
    }
    }}

</script>
<script type="text/html" id="dateTpl">
    {{formatdate(d.createTime)}}
</script>



<script type="text/javascript" src="${base}/static/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/my/formatDate.js" language="JavaScript"></script>
<script>


    layui.use('table', function () {
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {

                layer.confirm('真的删除行吗?', function (index) {

                    layer.close(index);

                    $.ajax({
                        type: 'POST',
                        url: '${base}/category/delete',
                        data: {id: data.categoryId},
                        dataType: "json",
                        success: function (data) {

                            if (200 == data.code) {
                                obj.del();
                                layer.msg(data.msg, {
                                    time: 2000
                                });
                                return true;
                            } else {

                                layer.msg(data.msg, {
                                    time: 2000
                                });
                                return false;
                            }

                        }

                    });
                    table.reload();


                });
            } else if (obj.event === 'edit') {


                layer.open({
                    type: 2
                    , title: '编辑商品类目'
                    , area: ['390px', '400px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/category/toEdit/'+data.categoryId

                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        // layer.setTop(layero); //重点2
                        table.reload();
                    }
                });


            }
        });

        var $ = layui.$, active = {
            getAddProduct: function () { //获取选中数据
                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '添加商品类目'
                    , area: ['390px', '400px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/category/toAdd'
                    /*, btn: ['确认', '取消']
                    , yes: function () {
                        layer.closeAll();
                    }
                    , btn2: function () {
                        layer.closeAll();
                    }*/
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        // layer.setTop(layero); //重点2
                        table.reload();
                    },
                    end: function () {
                        table.reload('idTest')
                    }
                });
            }
        };

        $('.addTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


    });
</script>

</body>
</html>