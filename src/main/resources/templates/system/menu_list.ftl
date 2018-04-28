<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单管理</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
    <style>/*样式*/

    </style>
</head>
<body class="layui-layout-body">

<div class="layui-btn-group addTable">
    <button class="layui-btn" data-type="getAddMenu">添加菜单</button>

</div>


<div id="maingrid">
    <table class="layui-hide" id="menutable"  lay-filter="edittool"></table>
</div>

<#--js 加载-->
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var layer = layui.layer;


        table.render({
            elem: '#menutable'
            ,url:'${base}/menu/menuGrid'
            //,height:auto
            //,width:1000
            ,cols: [[
                 {field:'id', width:80, align:'center',title: 'ID', sort: true}
                ,{field:'username', width:120,align:'center', title: '资源名称'}
                ,{field:'url', width:600,align:'center', title: '资源路径', sort: true}
                ,{field:'sortno', width:120, align:'center',title: '资源排序'}
                ,{field:'belong', width:120, align:'center',title: '适用范围' }
                ,{field:'type', width:120,align:'center', title: '资源类型', sort: true}
                ,{field:'remark', width:200, align:'center',title: '资源描述', sort: true}
                ,{field:'remark', width:200,align:'center', title: '操作' ,align:'center',toolbar: '#barDemo', fixed: 'right'}
            ]]
            ,page:true

        });

        table.on('tool(edittool)', function(obj){

            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){

               // layer.alert('编辑行：<br>'+ JSON.stringify(data))
                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加菜单'
                    ,area: ['670px', '450px']
                    ,skin:'layui-layer-molv'
                    ,shade: 0
                    ,maxmin: true
                    ,offset:'auto'/* [ //为了演示，随机坐标
                        Math.random()*($(window).height()-300)
                        ,Math.random()*($(window).width()-390)
                    ]*/
                    ,content: '${base}/menu/toAdd'
                    ,btn: ['确认', '取消'] //只是为了演示
                    ,yes: function(){
                        layer.closeAll();
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                       // layer.setTop(layero); //重点2
                        table.reload();
                    }
                });

            }
        });


        var $ = layui.$, active = {
            getAddMenu: function(){ //获取选中数据

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加菜单'
                    ,area: ['670px', '500px']
                    ,skin:'layui-layer-molv'
                    ,shade: 0
                    ,maxmin: true
                    ,offset:'auto'
                    ,content: '${base}/menu/toAdd'
                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        // layer.setTop(layero); //重点2
                        table.reload();
                    }
                });
            }

        };
        $('.addTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });





</script>
<#--工具栏-->
<script type="text/html" id="barDemo">
    <a  data-method="setTop" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>
</body>
</html>
