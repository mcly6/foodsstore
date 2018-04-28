<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加商品信息</title>

    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all">

    <style>

        .layui-input, {
            display: block;
            width: 86%;
            padding-left: 10px;
        }

        .layui-upload-img {
            width: 92px;
            height: 92px;
            margin: 0 10px 10px 0;
        }

        .layui-textarea {
            min-height: 97px;
            height: auto;
            padding: 23px 10px;
            resize: vertical;
            width: 514px;
        }

        .layui-input-block {
            margin-left: 110px;
            min-height: 36px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品信息</legend>
</fieldset>

<form class="layui-form" action="${base}/productInfo/add" id="productForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" name="productName" lay-verify="required" autocomplete="off" placeholder="请输商品名称"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品价格</label>
            <div class="layui-input-inline">
                <input type="text" name="productPrice" lay-verify="required|number" placeholder="请输商品名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品库存</label>
            <div class="layui-input-inline">
                <input type="text" name="productStock" lay-verify="required|number" placeholder="请输商品库存" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品类目</label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <select name="categoryType" lay-verify="required" lay-search="">
                        <option value="">请选择</option>

                    <#list categoryMap?keys as key>

                        <#if key == '1'>
                            <optgroup label="精致正餐">

                                <#assign categoryList =categoryMap[key]/>

                                <#list categoryList as list>
                                    <option value="${list.categoryId}">${list.categoryName}</option>
                                </#list>

                            </optgroup>
                        </#if>
                        <#if key =='2'>
                            <optgroup label="小炒菜品">
                                <#assign categoryList =categoryMap[key]/>

                                <#list categoryList as list>
                                    <option value="${list.categoryId}">${list.categoryName}</option>
                                </#list>

                            </optgroup>
                        </#if>
                    </#list>

                    </select>
                </div>
            </div>
        </div>

    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">商品说明</label>
        <div class="layui-input-block">
            <textarea name="productDescription" placeholder="请输入商品说明" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品图片</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="uploadImag">上传图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="productIconDemo">
                        <p id="productIconText"></p>
                        <input name="productIcon" id="productIcon" type="hidden">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div>
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>


</form>


<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate', 'upload'], function () {
        var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;
        var $ = layui.jquery
                , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#uploadImag'
            , url: '${base}/sys/file/uploadFile'
            , exts: 'jpg|png|gif|bmp|jpeg' //上传文件后缀
            , field: 'upFile' //后台接收默认字段名
            , data: {type:"productInfo"}
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#productIconDemo').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {

                //如果上传失败
                if (400 == res.code) {
                    return layer.msg(res.msg);
                }
                //上传成功
                if (200 == res.code) {

                    $("#productIcon").val(res.data)
                    return layer.msg(res.msg)
                }


            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#productIconText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

       /* //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });*/


        //监听提交
        form.on('submit(demo1)', function (data) {

               /* window.parent.location.reload();*/
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);



        });

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

</body>
</html>