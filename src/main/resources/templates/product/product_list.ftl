<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商品信息管理</title>
        <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>

    <style>/*样式*/
    .laytable-cell-1-productIcon { /*最后的pic为字段的field*/
        height: 100%;
        max-width: 100%;
    }

    </style>
</head>
<body class="layui-layout-body">

<div class="layui-btn-group addTable">
    <button class="layui-btn" data-type="getAddProduct">添加商品信息</button>
    <button class="layui-btn layui-btn-normal" data-type="getAddCombo">添加套餐信息</button>
</div>
<#--Grid 表格-->
    <table class="layui-table" id="productTableId" lay-filter="productTable">
        <thead>
        </thead>
    </table>




<script type="text/html" id="barDemo">
    {{# if(d.ifCombo == 0) { }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{# }else{ }}
        <a class="layui-btn layui-btn-xs" lay-event="editCombo">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    {{#} }}
</script>
<script type="text/html" id="imgTpl">
    <img class="layui-upload-img" height="100" src="${base}/sys/file/imgShow?src={{d.productIcon}}"/>
</script>
<script type="text/html" id="dateTpl">
    {{formatdate(d.createTime)}}
</script>

<script type="text/html" id="categoryTpl">
    {{ categoryFormat(d.categoryType)}}
</script>
<script type="text/html" id="statusTpl">

    {{#  if(d.productStatus === 0){ }}
            <a class="layui-btn  layui-btn-sm layui-btn-normal"  onclick="onStataus('{{d.productId}}')">上架</a>
    {{#  }  else { }}

            <a class="layui-btn   layui-btn-sm layui-btn-warm" onclick="onStataus('{{d.productId}}')">下架</a>
    {{#  } }}



</script>





<#--js 加载-->
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/my/formatDate.js" language="JavaScript"></script>
<script>
    var limitcount = 10;
    var curnum = 1;
    var layer ,table,productTable;

    layui.use(['table','laypage'], function () {
         table = layui.table;
         layer = layui.layer;
        laypage = layui.laypage;

        productTable = table.render({
             id:'productTableId'
            ,elem: '#productTableId'
            , url: '${base}/productInfo/productGrid'
            , height: 'full-60'
            , cols: [[

                {title: '名称', field: 'productName', width: 140, height: 80,}
                , {title: '图片', field: 'productIcon', width: 120, height: 80,  templet: '#imgTpl'}
                , {title: '单价', field: 'productPrice', width: 95, height: 80, sort: true}
                , {title: '库存', field: 'productStock', width: 90, height: 80, sort: true}
                , {title: '描述', field: 'productDescription', width: 160, height: 80}

                , {
                    title: '类目',
                    field: 'categoryType',
                    width: 95,
                    height: 80,
                    sort: true,
                    templet: '#categoryTpl'
                }
                , {
                    title: '创建时间',
                    field: 'createTime',
                    width: 170,
                    height: 80,
                    sort: true,
                    templet: '#dateTpl'
                }
                , {
                    title: '修改时间',
                    field: 'updateTime',
                    width: 170,
                    height: 80,
                    sort: true,
                    templet: '#dateTpl'
                }
               , {title: '状态',
                   field: 'productStatus',
                   width: 95,
                   height: 80,
                   sort: true,
                   align: 'center',
                   templet: '#statusTpl'}
                , {title: '操作', field:'ifCombo', width: 200, height: 80, align: 'center', toolbar: '#barDemo'}
            ]]
            , page:true

        });


        table.on('tool(productTable)', function (obj) {

            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    //deleteProduct(data.productId);

                    $.ajax({
                        type: 'POST',
                        url:  '${base}/productInfo/delete',
                        data: {id: data.productId},
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

                    productTable.reload();

                });
            } else if (obj.event === 'edit') {

                    layer.open({
                type: 2 //此处以iframe举例
                        , title: '添加菜单'
                        , area: ['700px', '500px']
                        , skin: 'layui-layer-molv'
                        , shade: 0.3
                        , maxmin: true
                        , offset: 'auto'
                        , content: '${base}/productInfo/toEdit/' + data.productId
                        , zIndex: layer.zIndex //重点1
                        , success: function (layero) {

                    // layer.setTop(layero); //重点2
                    productTable.reload();
                }
            });
            }else if (obj.event === 'editCombo') {
                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '编辑商品套餐信息'
                    , area: ['700px', '500px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/combo/toEdit/' + data.productId
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
            getAddProduct: function () { //添加商品信息

                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '添加商品信息'
                    , area: ['700px', '570px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/productInfo/toAdd'
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        // layer.setTop(layero); //重点2
                        productTable.reload();
                    }
                    ,end: function () {
                        productTable.reload();
                    }
                });
            },
            getAddCombo: function () { //添加套餐信息

                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '添加商品信息'
                    , area: ['700px', '600px']
                    , skin: 'layui-layer-molv'
                    , shade: 0.3
                    , maxmin: true
                    , offset: 'auto'
                    , content: '${base}/combo/toAdd'
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        // layer.setTop(layero); //重点2

                    },
                    end: function () {
                        productTable.reload();
                    }

                });
                
            }


        };
        $('.addTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



    });

    var a;

    function categoryFormat(type) {


        $.ajax({
            type: 'POST',
            url: '${base}/category/getcategory',
            data: {id: type},
            dataType: "json",
            async: false, //
            success: function (data) {
                a = data;
            }

        });
        return a.categoryName;

    }

    function onStataus(id) {

        $.ajax({

            url:'${base}/productInfo/editStatus',
            type:'post',
            dataType:'json',
            data:{id:id},
            success:function (data) {

                if (200 == data.code) {
                    productTable.reload();
                    layer.msg('修改上架状态成功', {
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
