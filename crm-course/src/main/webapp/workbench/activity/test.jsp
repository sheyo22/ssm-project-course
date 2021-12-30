<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript">
        $(function(){
            alert("jquery")
            $.ajax({
                url:"workbench/activity/getUserList.do",
                type:"get",
                dataType:"json",
                success:function (data) {
                    //每一个n就是一个对象
                    alert("ajax")
                    var html
                    $.each(data,function (i,n) {
                        html+="<option value='"+n.id+"'>"+n.name+"</option>"
                    })
                    $("#create-marketActivityOwner").html(html)
                    $("#createActivityModal").modal("show")
                }
            })
        })
    </script>
</head>
<body>
    <button type="button" class="btn btn-primary" data-toggle="modal" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
    <button type="button" class="btn btn-default" data-toggle="modal" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
    <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
</body>
</html>