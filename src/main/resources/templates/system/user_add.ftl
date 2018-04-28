<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户</title>

    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css"  media="all">
    <style>
        .layui-input, {
            display: block;
            width: 86%;
            padding-left: 10px;
        }
        .layui-form-item .layui-input-inline {
            display: block;
            float: none;
            left: -3px;
            width: auto;
            margin: 0 46px 10px 112px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户信息</legend>
</fieldset>

<form class="layui-form" action="${base}/user/add" id="userInfo">
    <div class="layui-form-item">

            <label class="layui-form-label">昵称</label>
            <div class="layui-input-inline">
                <input type="text" name="nickname" lay-verify="required" autocomplete="off" placeholder="请输入用户昵称" class="layui-input">
            </div>
    </div>
    <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-inline">
                <input type="tel" name="username" lay-verify="required" placeholder="请输入账号名" autocomplete="off" class="layui-input">
            </div>

    </div>
    <div class="layui-form-item">
            <label class="layui-form-label">用户类型</label>
            <div class="layui-input-inline">
                <select name="type" lay-filter="type" lay-verify="required">
                    <#list typeList as type>
                        <option value="${type.code}">${type.message}</option>
                    </#list>
                </select>
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">住院号</label>
        <div class="layui-input-inline">
            <input type="tel" name="hosNum"  placeholder="请设置资源排序" autocomplete="off" class="layui-input">
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
                ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });


        //监听提交
        form.on('submit(demo1)', function(data){
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