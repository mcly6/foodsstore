<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单信息管理</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
    <link rel="stylesheet" href="${base}/static/css/global.css" media="all" type="text/css"/>
    <link rel="stylesheet" href="${base}/static/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">

    <style>/*样式*/
    .laytable-cell-1-productIcon { /*最后的pic为字段的field*/
        height: 100%;
        max-width: 100%;
    }
    .site-fix .site-tree {
        position: fixed;
        top: 0;
        bottom: 0;
        z-index: 666;
        min-height: 0;
        overflow: auto;
        background-color: #fff;
    }
    .layui-input, .layui-textarea {
        display: block;
        width: 97%;
        padding-left: 10px;
    }
    </style>
</head>
<body class="layui-layout-body">

<div class=" site-inline">

    <div class="site-tree">
        <input type="text" id="key" value="" class="empty layui-input" placeholder="请输入类型名称" /><br/>
        <ul id="treeDemo" class="ztree"></ul>
    </div>

    <div class="site-content">
        <div class="demoTable addTable ">
            <button class="layui-btn" data-type="getAddDictType" id="getAddDictType">添加字典类目</button>
            <button class="layui-btn layui-btn-normal" data-type="getEditDictType" id="getEditDictType">修改字典类目</button>
            <button class="layui-btn layui-btn-danger" data-type="getDeleteDictType" id="getDeleteDictType">删除字典类目</button>
            <button class="layui-btn layui-btn-warm" data-type="getAddDictInfo" id="getAddDictInfo">添加字典信息</button>
            搜索名称：
            <div class="layui-inline">
                <input class="layui-input" name="dictName" id="dictName" placeholder="请输入字典名称" autocomplete="off" onkeyup="searchenter(event)">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>

        <table class="layui-table" id="dictTypeTableId" lay-filter="dictTypeTable" style="widows: 100%;">
            <thead>
            </thead>
        </table>

    </div>


</div>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="imgTpl">
    <img class="layui-upload-img" height="100" src="${base}/sys/file/imgShow?src={{d.productIcon}}"/>
</script>
<script type="text/html" id="createDateTpl">
    {{formatdate(d.createTime,null)}}
</script>
<script type="text/html" id="updateDateTpl">
    {{formatdate(d.updateTime,null)}}
</script>

<script type="text/html" id="typeGroupTpl">
    {{ typeGroupFormat(d.dictType)}}
</script>





<#--js 加载-->
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/my/formatDate.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/zTree/js/jquery.ztree.core.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/zTree/js/jquery.ztree.exedit.js" language="JavaScript"></script>

<script>
    var limitcount = 10;
    var curnum = 1;

    var table ;

    $(function () {

        var dictTypeGrid ;


        var setting = {
            data: {
                key: {
                    title: "id"
                }
            },
            view: {
                fontCss: getFontCss
            },
            callback:{ //回调函数
                /**
                 * event:鼠标事件
                 * treeId：树的容器id
                 * treeNode：当前点击的节点
                 */
                onClick :function zTreeOnClick(event, treeId, treeNode) {
                    table.reload('dictTypeGridId',{
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            dictType: treeNode.id
                        }
                    });



                }
            }
        };
        var dictName = $("#dictName").val();

        layui.use(['table', 'laypage', 'tree'], function () {
             table = layui.table;
            var layer = layui.layer;
            var laypage = layui.laypage;



             dictTypeGrid = table.render({
                id: 'dictTypeGridId'
                , elem: '#dictTypeTableId'
                , url: '${base}/dict/dictGrid'
                , height: 'full-80'
                , cols: [[

                     {title: '所属类型', field: 'dictType', width: 95, height: 80, sort: true,templet:'#typeGroupTpl'}
                    ,{title: '字典名称', field: 'dictName', width: 140, height: 80,}
                    , {title: '字典编码', field: 'dictCode', width: 120, height: 80, }
                    , {title: '字典说明', field: 'remark', width: 120, height: 80, }
                    , {
                        title: '创建时间',
                        field: 'createTime',
                        width: 170,
                        height: 80,
                        sort: true,
                        templet: '#createDateTpl'
                    }
                    , {
                        title: '修改时间',
                        field: 'updateTime',
                        width: 170,
                        height: 80,
                        sort: true,
                        templet: '#updateDateTpl'
                    }
                    , {title: '操作', width: 178, height: 80, align: 'center', toolbar: '#barDemo'}
                ]]
                , page: true,where :{dictName:dictName,dictType:""}

            });


            table.on('tool(dictTypeTable)', function (obj) {

                var data = obj.data;
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        obj.del();
                        layer.close(index);
                        $.ajax({
                            type: 'POST',
                            url: '${base}/dict/delete',
                            data: {id: data.id},
                            dataType: "json",
                            async: false,
                            success: function (data) {

                                if (200 == data.code) {
                                    layer.alert(data.msg);

                                }
                                if (400 == data.code) {
                                    layer.alert(data.msg);
                                }

                            }

                        });
                        table.reload('dictTypeGridId');

                    });
                } else if (obj.event === 'edit') {

                    layer.open({
                        type: 2 //此处以iframe举例
                        , title: '编辑字典'
                        , area: ['400px', '500px']
                        , skin: 'layui-layer-molv'
                        , shade: 0.3
                        , maxmin: true
                        , offset: 'auto'
                        , content: '${base}/dict/toEdit/' + data.id
                        , zIndex: layer.zIndex //重点1
                        , success: function (layero) {

                            table.reload('dictTypeGridId');
                        }
                    });

                }
            });


            var $ = layui.$, active = {
                getAddDictType: function () {
                    layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '添加字典类型'
                        ,area: ['400px', '300px']
                        ,skin:'layui-layer-molv'
                        ,shade: 0
                        ,maxmin: true
                        ,offset:'auto'
                        ,content: '${base}/dict/toAddType'
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            // layer.setTop(layero); //重点2
                            $.ajax({
                                url:'${base}/dict/getDictTypeGroupList',
                                type:'post',
                                dataType:'json',
                                success:function (data) {
                                    console.log(data)

                                    $.fn.zTree.init($("#treeDemo"), setting,data.data)

                                }
                            });
                        }
                    });

                },
                getEditDictType: function () {
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                            nodes = zTree.getSelectedNodes(),
                            treeNode = nodes[0];
                    if (nodes.length == 0) {
                        layer.alert("请先选择一个节点");
                        return;
                    }

                    layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '添加字典类型'
                        ,area: ['400px', '300px']
                        ,skin:'layui-layer-molv'
                        ,shade: 0
                        ,maxmin: true
                        ,offset:'auto'
                        ,content: '${base}/dict/toEditType/'+treeNode.id
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            // layer.setTop(layero); //重点2
                            $.ajax({
                                url:'${base}/dict/getDictTypeGroupList',
                                type:'post',
                                dataType:'json',
                                success:function (data) {
                                    console.log(data)

                                    $.fn.zTree.init($("#treeDemo"), setting,data.data)

                                }
                            });
                        }
                    });




                },
                getDeleteDictType: function () {
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                            nodes = zTree.getSelectedNodes(),
                            treeNode = nodes[0];
                    if (nodes.length == 0) {
                        layer.alert("请先选择一个节点");
                        return;
                    }


                    $.ajax({
                        url:'${base}/dict/deleteDictTypeGroup/'+treeNode.id,
                        type:'post',
                        dataType:'json',
                        success:function (data) {
                          if (data.code ==200) {
                              layer.alert(data.data);
                              $.ajax({
                                  url:'${base}/dict/getDictTypeGroupList',
                                  type:'post',
                                  dataType:'json',
                                  success:function (data) {
                                      console.log(data)

                                      $.fn.zTree.init($("#treeDemo"), setting,data.data)

                                  }
                              });

                          }
                          if (data.code ==400) {
                              layer.alert(data.msg);
                          }

                        }
                    });



                },
                getAddDictInfo: function () {
                    layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '添加字典信息'
                        ,area: ['400px', '500px']
                        ,skin:'layui-layer-molv'
                        ,shade: 0
                        ,maxmin: true
                        ,offset:'auto'
                        ,content: '${base}/dict/toAddDictType'
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            // layer.setTop(layero); //重点2
                            table.reload('dictTypeGridId');
                        }
                    });
                },
                reload: function(){

                    var dictName = $('#dictName') ;

                    //执行重载
                    table.reload('dictTypeGridId', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {

                                dictName: dictName.val()

                        }
                    });
                }

            };
            $('.addTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });



        });




            //左侧书
            $.ajax({
                url:'${base}/dict/getDictTypeGroupList',
                type:'post',
                dataType:'json',
                success:function (data) {
                    $.fn.zTree.init($("#treeDemo"), setting,data.data)



                }
            });

            //左侧树查询
        key = $("#key");
        key.bind("focus", focusKey)
                .bind("blur", blurKey)
                .bind("propertychange", searchNode)
                .bind("input", searchNode);


    })

    function focusKey(e) {
        if (key.hasClass("empty")) {
            key.removeClass("empty");
        }
    }
    function blurKey(e) {
        if (key.get(0).value === "") {
            key.addClass("empty");
        }
    }
    var nodeList = [];
    function searchNode(e) {

        var zTree = $.fn.zTree.getZTreeObj("treeDemo");

            var value = $.trim(key.get(0).value);
            var keyType = "";

                keyType = "name";

            if (key.hasClass("empty")) {
                value = "";
            }
            if (value === "") return;
            updateNodes(false);
            nodeList = zTree.getNodesByParamFuzzy(keyType, value);

        updateNodes(true);

    }
    function updateNodes(highlight) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        for( var i=0, l=nodeList.length; i<l; i++) {
            nodeList[i].highlight = highlight;
            zTree.updateNode(nodeList[i]);
        }
    }
    function getFontCss(treeId, treeNode) {
        return (!!treeNode.highlight) ? {color:"#FF5722", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
    }

    function typeGroupFormat(id) {
        var a ="";
        $.ajax({
            type: 'POST',
            url: '${base}/dict/getTypeGroupName',
            data: {id: id},
            dataType: "json",
            async: false, //
            success: function (data) {

                a = data;
            }

        });
        return a.typegroupname;

    }
    //回车查询
    function searchenter(event) {
        event = event || window.event;
        if (event.keyCode == 13) {
            if ($('#dictName').val() == '') {
                return false;
            }
            var dictName = $('#dictName') ;
            //执行重载
            table.reload('dictTypeGridId', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {

                    dictName: dictName.val()

                }
            });
        }
    }


</script>
</body>
</html>
