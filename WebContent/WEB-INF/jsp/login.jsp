<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
登录页面
<form action="/ssm/login/authentization">
<label>用户名</label>
<input type="text" name="name">
<label>密码</label>
<input type="password" name="password" >
<label>是否记住</label>
<input type="checkbox" name="rememberMe">
<br>
<input type="submit" value="提交">
</form>
</body>
</html>