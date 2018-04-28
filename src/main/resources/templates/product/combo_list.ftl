<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单套餐管理</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
    <style>/*样式*/
    .laytable-cell-1-comboIcon { /*最后的pic为字段的field*/
        height: 100%;
        max-width: 100%;
    },
    .layui-table tr {
        height: 100px;/*数值按需更改*/
    }

    </style>
</head>
<body class="layui-layout-body">

<div class="layui-btn-group addTable">
    <button class="layui-btn" data-type="getAddProduct">添加商品套餐类目</button>
</div>



    <table class="layui-table" id="comboTableId" lay-filter="comboTable">
        <thead>
        </thead>
    </table>




<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="imgTpl">
    <img class="layui-upload-img" height="100" src="${base}/sys/file/imgShow?src={{d.comboIcon}}"/>
</script>
<script type="text/html" id="dateTpl">
    {{formatdate(d.createTime)}}
</script>

<script type="text/html" id="dishesTpl">
    {{ infoFormat(d.dishes)}}
</script>
<script type="text/html" id="soupsBrothsTpl">
    {{ infoFormat(d.soupsBroths)}}
</script>
<script type="text/html" id="stapleFoodTpl">
    {{ infoFormat(d.stapleFood)}}
</script>


<#--js 加载-->
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/my/formatDate.js" language="JavaScript"></script>
<script>
    var limitcount = 10;
    var curnum = 1;

    layui.use(['table','laypage'], function () {
        var table = layui.table;
        var layer = layui.layer;
        laypage = layui.laypage;

       var comboTable = table.render({
             id:'comboTableId'
            ,elem: '#comboTableId'
            , url: '${base}/combo/comboGrid'
            , height: 'full-60'
            , cols: [[

                {title: '名称', field: 'comboName', width: 140, height: 100,}
                , {title: '单价', field: 'comboStock', width: 100, height: 100, sort: true}
                , {title: '图片', field: 'comboIcon', width: 120, height: 100, sort: true,templet: '#imgTpl'}
                , {title: '菜品', field: 'dishes', width: 170, height: 100, sort: true,templet:'#dishesTpl'}
                , {title: '汤品', field: 'soupsBroths', width: 170, height: 100,templet:'#soupsBrothsTpl'}
                , {title: '主食', field: 'stapleFood', width: 170,height: 100,sort: true,templet:'#stapleFoodTpl'}
                , {title: '创建时间',field: 'createTime',width: 150,height: 100,sort: true,templet: '#dateTpl' }
                , {title: '修改时间',field: 'updateTime',width: 150,height: 100,sort: true,templet: '#dateTpl'}
                , {title: '操作', width: 150, height: 100, align: 'center', toolbar: '#barDemo'}
            ]]
            , page:true

        });


        table.on('tool(comboTable)', function (obj) {

            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    //deleteProduct(data.productId);

                    $.ajax({
                        type: 'POST',
                        url:  '${base}/combo/delete',
                        data: {id: data.id},
                        dataType: "json",
                        async: false,
                        success: function (data) {

                            if (200 == data.code) {
                                layer.alert(data.msg);

                            }
                            if(400 == data.code) {
                                layer.alert(data.msg);
                            }

                        }

                    });

                    comboTable.reload();

                });
            } else if (obj.event === 'edit') {

                layer.open({
                     type: 2 //此处以iframe举例
                    , title: '编辑商品套餐信息'
                    , area: ['700px', '500px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/combo/toEdit/' + data.id
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {


                        // layer.setTop(layero); //重点2
                        //comboTable.reload();
                    }
                    ,end:function () {
                        comboTable.reload();
                        
                    }
                });

            }
        });


        var $ = layui.$, active = {
            getAddProduct: function () { //获取选中数据

                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '添加商品信息'
                    , area: ['700px', '500px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/combo/toAdd'
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        // layer.setTop(layero); //重点2
                        table.reload();
                    }
                });
            }

        };
        $('.addTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });



    function infoFormat(id) {

        var a ="";
        $.ajax({
            type: 'POST',
            url: '${base}/productInfo/getProductInfo',
            data: {id: id},
            dataType: "json",
            async: false, //
            success: function (data) {

                a = data.data;
            }

        });
        return a;

    }


</script>
</body>
</html>
