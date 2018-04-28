<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加菜单</title>

    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css"  media="all">
    <style>
        .layui-textarea {
            min-height: 100px;
            height: auto;
            padding: 6px 10px;
            resize: vertical;
            width: 500px;
        }
        .urlclass {
            width: 500px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>菜单信息</legend>
</fieldset>

<form class="layui-form" action="" id="menuinfo">
    <div class="layui-form-item">

            <label class="layui-form-label">资源名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入资源名称" class="layui-input">
            </div>

            <label class="layui-form-label">资源代码</label>
            <div class="layui-input-inline">
                <input type="tel" name="code" lay-verify="required" placeholder="请输入唯一标示" autocomplete="off" class="layui-input">
            </div>

    </div>
    <div class="layui-form-item">

            <label class="layui-form-label">资源类型</label>
            <div class="layui-input-inline">
                <select name="type" lay-filter="type" lay-verify="required">
                    <option value="">请选择资源类型</option>
                    <option value="0">菜单</option>
                    <option value="1">功能</option>
                </select>
            </div>

            <label class="layui-form-label">上级菜单</label>
            <div class="layui-input-inline">
                <select name="belong" lay-filter="belong" >
                    <option value="">请选择上级菜单</option>
                    <option value="0">写作</option>
                    <option value="1">阅读</option>
                </select>
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">适用范围</label>
        <div class="layui-input-inline">
            <select name="belong" lay-filter="belong" >
                <option value="">请选择适用范围</option>
                <option value="0">写作</option>
                <option value="1">阅读</option>
            </select>
        </div>
        <label class="layui-form-label">资源排序</label>
        <div class="layui-input-inline">
            <input type="tel" name="sortno" lay-verify="required" placeholder="请设置资源排序" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">资源路径</label>
        <div class="layui-input-block">
            <input type="text" name="uri" lay-verify="required" placeholder="请输入资源路径" autocomplete="off" class="layui-input urlclass">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-inline">
            <select name="icon" lay-filter="icon" id="icon" >
                <option value="">请选择适用图标</option>
                <option value="http://files.jb51.net/file_images/article/201508/201588111102886.jpg">菜单</option>
                <option value="1">阅读</option>
            </select>
        </div>

        <label class="layui-form-label">资源排序</label>
        <div class="layui-input-inline">
            <input type="text" name="seq" id="date" lay-verify="required"  placeholder="请输入资源路径" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入" class="layui-textarea"></textarea>
            </div>
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
            /*layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;*/
            $.ajax({
                async: false,
                cache: false,
                type: 'POST',
                url: '${base}/menu/add',// 请求的action路径
                data: {},//JSON.stringify(data.field),
                success: function (data) {
                    console.log("成功")
                }
            });
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

</body>
</html>