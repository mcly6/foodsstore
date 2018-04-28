<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>订餐后台</title>
    <link rel="stylesheet" href="${base}/static/js/layui/css/layui.css" media="all" type="text/css"/>
<#--<link href="Resource/jQuery.Validate/jquery.validate.css" rel="stylesheet" />
<link href="css/admin.css" rel="stylesheet" />-->
    <style>

    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 头部导航区域 -->
    <div class="layui-header">
        <div class="layui-logo">营养订餐系统 · 订餐管理</div>
    <#--<ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a href="javascript:;" data-id="1" data-url="http://www.baidu.com" class="menuItem">用户管理</a></li>
        <li class="layui-nav-item"><a href="javascript:;">字典管理</a></li>
        <li class="layui-nav-item"><a href="javascript:;">地址管理</a></li>

    </ul>-->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${base}/static/img/headicon.png" class="layui-nav-img">
                   餐厅
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${base}/sys/login/logout">退出</a></li>
        </ul>
    </div>

    <!-- 左侧导航区域 -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">订单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="2" data-url="${base}/user/touserlist?type=1" class="menuItem">列表</a></dd>
                        <dd><a href="javascript:;" data-id="3" data-url="${base}/user/touserlist?type=1" class="menuItem">操作</a></dd>
                    </dl>

                </li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="4" data-url="${base}/category/list" class="menuItem">商品类目管理</a></dd>
                        <dd><a href="javascript:;" data-id="5" data-url="${base}/productInfo/list" class="menuItem">商品信息管理</a></dd>
                        <#--<dd><a href="javascript:;" data-id="6" data-url="${base}/combo/list" class="menuItem">商品套餐管理</a></dd>-->
                    </dl>

                </li>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div class="layui-body" style="overflow: hidden!important;">
        <div class="layui-tab" lay-filter="tab_menu" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" id="tab_menu_first">管理首页<i class="layui-icon layui-unselect layui-tab-close layui-hide" onclick="return false;">ဆ</i></li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">首页内容</div>
            </div>
        </div>



    <!-- 底部固定区域 -->
    <#include "common/bottom.ftl">
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

        console.log(iframeHeight)

        //创建选项卡
        $("a.menuItem").on("click", function () {
            var id = $(this).data("id");
            var hasId = $(".layui-tab-title li[lay-id='" + id + "']");
            if (hasId.length == 0) {
                var title = $(this).html();
                var url = $(this).data("url");
                var content = '<iframe src="' + url + '" width="100%"   height="' + iframeHeight + '" frameborder="0" scrolling="yes"  onload="lib.closeDialog();"></iframe>';
//                lib.load();
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