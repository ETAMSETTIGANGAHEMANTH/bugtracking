<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<form action='login' method = 'POST'>
<div>
    <input type="text" name="username" placeholder="Username"></input><br>
    </div>
    <div>
    <input type="password" name="password" placeholder="Password"></input><br>
    </div>
    <div>
    <button type="submit">Login</button>
    </div>
</form>
</body>
</html>