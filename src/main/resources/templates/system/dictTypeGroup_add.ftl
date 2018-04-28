<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加字典类型</title>

    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css"  media="all">
    <style>
        .layui-form-label {
            float: left;
            display: block;
            padding: 9px 15px;
            width: 91px;
            font-weight: 400;
            text-align: right;
        }
        .layui-input, .layui-textarea {
            display: block;
            width: 80%;
            padding-left: 10px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>菜单信息</legend>
</fieldset>

<form class="layui-form" action="${base}/dict/addType" id="menuinfo">
    <div class="layui-form-item">

            <label class="layui-form-label">字典类型名称</label>
            <div class="layui-input-inline">
                <input type="text" name="typegroupname" lay-verify="required" autocomplete="off" placeholder="请输入字典类型名称" class="layui-input">
            </div>
    </div>
    <div class="layui-form-item">
            <label class="layui-form-label">字典类型编码</label>
            <div class="layui-input-inline">
                <input type="tel" name="typegroupcode" lay-verify="required" placeholder="字典类型编码" autocomplete="off" class="layui-input">
            </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>


<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit

        //监听提交
        form.on('submit(demo1)', function(data){

            //window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

</body>
</html>