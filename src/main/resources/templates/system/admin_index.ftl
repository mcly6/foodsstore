<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>管理后台</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
    <#--<link href="Resource/jQuery.Validate/jquery.validate.css" rel="stylesheet" />
    <link href="css/admin.css" rel="stylesheet" />-->
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 头部导航区域 -->
    <div class="layui-header">
        <div class="layui-logo">营养订餐系统 · 系统管理</div>
        <#--<ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="javascript:;" data-id="1" data-url="http://www.baidu.com" class="menuItem">用户管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;">字典管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;">地址管理</a></li>

        </ul>-->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${base}/static/img/headicon.png" class="layui-nav-img">
                    超级管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${base}/logout">退出</a></li>
        </ul>
    </div>

    <!-- 左侧导航区域 -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <dd><a href="javascript:;" data-id="2" data-url="${base}/user/toUserList" class="menuItem">用户管理</a></dd>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;" data-id="3" data-url="${base}/role/toRoleList.html" class="menuItem">角色管理</a>

                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"  data-id="5" data-url="${base}/menu/list" class="menuItem">资源管理</a>

                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-id="4" data-url="${base}/dict/toList" class="menuItem"">字典管理</a>

                </li>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layui-tab" lay-filter="tab_menu" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" id="tab_menu_first">管理首页<i class="layui-icon layui-unselect layui-tab-close layui-hide" onclick="return false;">ဆ</i></li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">首页内容</div>
            </div>
        </div>
    </div>

    <!-- 底部固定区域 -->
        <#include "../common/bottom.ftl">
</div>
<script type="text/javascript" src="${base}/static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/layui.js" language="JavaScript"></script>
<script type="text/javascript" src="${base}/static/js/lib.js" language="JavaScript"></script>

<script>
    layui.use('element', function () {
        var $ = layui.jquery
                , element = layui.element;

        //窗口高度
        var windowHeight = $(window).height();
        var headerHeight = $(".layui-header").height();
        var tabMenuHeight = $(".layui-tab-title").height();
        var footerHeight = $(".layui-footer").height();
        var iframeHeight = windowHeight - headerHeight - tabMenuHeight - footerHeight-50;

        //创建选项卡
        $("a.menuItem").on("click", function () {
            var id = $(this).data("id");
            var hasId = $(".layui-tab-title li[lay-id='" + id + "']");
            if (hasId.length == 0) {
                var title = $(this).html();
                var url = $(this).data("url");
                var content = '<iframe src="' + url + '" width="100%" height="' + iframeHeight + '" frameborder="0" scrolling="yes"  onload="lib.closeDialog();"></iframe>';
                lib.load();
                element.tabAdd('tab_menu', {
                    title: title,
                    content: content,
                    id: id
                });
            }

            //切换到选项卡
            element.tabChange('tab_menu', id);
        });
    });
</script>
</body>
</html>