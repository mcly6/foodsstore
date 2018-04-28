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
    <button class="layui-btn" data-type="getAddUser">添加用户</button>

</div>

<div id="mainGrid">
    <table class="layui-hide" id="userTable"  lay-filter="editTool"></table>
</div>
<#--工具栏-->
<script type="text/html" id="barDemo">
    <a  data-method="setTop" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>
<script type="text/html"  id="typeTpl">
    {{#  if(d.type === 1){ }}
            患者
    {{#  }  else if(d.type === 2) { }}

            医师
    {{#  }  else if(d.type === 3) { }}

            食堂
    {{#  }  else if(d.type === 99) { }}

            管理员
    {{#  } }}
</script>

<script type="text/html" id="statusTpl">

    {{#  if(d.status === 0){ }}
        <a class="layui-btn  layui-btn-sm layui-btn-warm"  onclick="onStataus('{{d.id}}')">禁用</a>
    {{#  }  else { }}

         <a class="layui-btn   layui-btn-sm layui-btn-normal" onclick="onStataus('{{d.id}}')">激活</a>
    {{#  } }}



</script>

<#--js 加载-->
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script>
    var userTable ;
    layui.use('table', function(){
        var table = layui.table;
        var layer = layui.layer;

        userTable =  table.render({
            elem: '#userTable'
            ,url:'${base}/user/list'
            //,height:auto
            //,width:1000
            ,cols: [[
                {field:'id', width:80, align:'center',title: 'ID'}
                ,{field:'nickname', width:120, align:'center',title: '昵称'}
                ,{field:'username', width:120,align:'center', title: '用户名(电话)'}
                ,{field:'type', width:120,align:'center', title: '类型',templet:"#typeTpl" }
                ,{field:'hosNum', width:120, align:'center',title: '住院号'}
                ,{field:'BigDecimal', width:120,align:'center', title: '余额'}
                ,{field:'status', width:120, align:'center',title: '账号状态',templet:'#statusTpl' }
                ,{field:'remark', width:200,align:'center', title: '操作' ,align:'center',toolbar: '#barDemo'}
            ]]
            ,page:true


        });

        table.on('tool(editTool)', function(obj){

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
                    ,title: '编辑用户信息'
                    ,area: ['390px', '400px']
                    ,skin:'layui-layer-molv'
                    ,shade: 0
                    ,maxmin: true
                    ,offset:'auto'/* [ //为了演示，随机坐标
                        Math.random()*($(window).height()-300)
                        ,Math.random()*($(window).width()-390)
                    ]*/
                    ,content: '${base}/user/toEdit/'+data.id
                    ,zIndex: layer.zIndex //重点1
                    ,end:function () {
                        userTable.reload();
                    }
                });

            }else if (obj.event === 'disable') {


            }

        });

        var $ = layui.$, active = {
            getAddUser: function(){ //获取选中数据

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加用户'
                    , area: ['390px', '400px']
                    ,skin:'layui-layer-molv'
                    ,shade: 0.3
                    ,maxmin: true
                    ,offset:'auto'
                    ,content: '${base}/user/toAdd'
                    ,zIndex: layer.zIndex //重点1
                    ,end:function () {
                        userTable.reload();
                    }
                });
            }

        };
        $('.addTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


    function onStataus(id) {

        $.ajax({

            url:'${base}/user/disable',
            type:'post',
            dataType:'json',
            data:{id:id},
            success:function (data) {

                if (200 == data.code) {
                    userTable.reload();
                    layer.msg('修改账户状态成功', {
                        time: 1000
                    });
                }
                if ( 400 == data.code) {

                    layer.msg(data.msg, {
                        time: 1000

                    });
                }

            }
        })

    }



</script>

</body>
</html>
