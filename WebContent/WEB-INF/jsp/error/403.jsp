<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>layui403</title>
<link rel="stylesheet" type="text/css"
	href="/ssm/Plug/layui-v2.4.5/layui/css/layui.css" />
<script src="/ssm/Plug/layui-v2.4.5/layui/layui.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<h4
		style="text-align: center; font-family: arial; color: #009688; font-size: 200px; letter-spacing: 30px; font-weight: bold;">403</h4>
	<br>
	<h4
		style="text-align: center; font-family: arial; color: #009688; font-size: 70px;">
		对不起您暂时没有权限， <span class="m"></span> 秒后跳转首页
	</h4>
	<h4
		style="text-align: center; font-family: arial; color: #009688; font-size: 70px;">
		<a href="/ssm/index">没有跳转请点击</a>
	</h4>

</body>
<script src="/ssm/Plug/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
		var t=10;
		var c;
		timedCount();
		function timedCount()
		{
        $(".m").text(t);
		t=t-1;
		c=setTimeout("timedCount()",1000);
		if(t<0){
			clearTimeout(c);
			location.href="/ssm/index";
		}
		}
		</script>
</html>