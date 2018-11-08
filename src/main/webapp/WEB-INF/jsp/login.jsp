<%@ page import="java.nio.file.Path" %>
<%--
  Created by IntelliJ IDEA.
  User: crossoverJie
  Date: 2016/7/14
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh" class="no-js">

<head>
    <meta charset="utf-8">
    <title>后台登录</title>
</head>

<body oncontextmenu="return false">
    <div class="page-container">
        <input type="hidden" id="error" value="${error}"/>
        <h1>Login</h1>
        <form action="${pageContext.request.contextPath }/login/v1/admin" method="post">
            <div>
                <input type="text" name="name" class="username" placeholder="Username" autocomplete="off"/>
            </div>
            <div>
                <input type="password" name="password" class="password" placeholder="Password" oncontextmenu="return false"
                       onpaste="return false" />
            </div>
            <button id="submit" type="submit">Sign in</button>
        </form>
    </div>
</body>

</html>
