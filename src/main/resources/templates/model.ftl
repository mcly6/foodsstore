<html>
   <head>
       <link rel="stylesheet" href="/static/js/layui/css/layui.css" media="all" type="text/css"/>
       <script type="text/javascript" src="/static/js/jquery/jquery-3.2.1.min.js"></script>
       <script type="text/javascript" src="/static/js/layui/layui.js" language="JavaScript"></script>

   </head>

    <body>
        <table class="layui-table">
            <tr>
                <td {field:'createTime', width:100}>


            <#list list as info>
                ${info.createTime} <br>
            </#list>
                </td>
            </tr>
        </table>

       <#-- <table class="layui-table" id="productTableId" lay-data="{height:450, url:'/productInfo/productGrid',page:true,  id:'idTest'}" lay-filter="productTable">
            <thead>
            <tr>
            &lt;#&ndash;<th lay-data="{field:'productId', width:140, sort: true, fixed: left}">商品id</th>&ndash;&gt;
                <th lay-data="{field:'productName', width:140, height:100,sort: true, fixed: true}">名称</th>
                <th lay-data="{field:'productIcon', width:108,height:108, sort: true, fixed: true, templet: '#imgTpl'}">图片</th>
                <th lay-data="{field:'productPrice', width:100,height:100, sort: true, fixed: true}">单价</th>
                <th lay-data="{field:'productStock', width:100,height:100, sort: true, fixed: true}">库存</th>
                <th lay-data="{field:'productDescription', width:140, height:100,sort: true, fixed: true}">描述</th>
                <th lay-data="{field:'categoryType', width:140, height:100,sort: true, fixed: true}">类目</th>
                <th lay-data="{field:'createTime', width:140, height:100,sort: true, fixed: true}">创建时间</th>
                <th lay-data="{field:'updateTime', width:140, height:100,sort: true, fixed: true}">修改时间</th>
                <th lay-data="{fixed: 'right', width:178,height:100, align:'center', toolbar: '#barDemo'}">操作</th>
            </tr>
            </thead>
        </table>-->


    </body>
</html>