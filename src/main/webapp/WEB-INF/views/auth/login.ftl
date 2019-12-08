<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form action="/login/process" method="post">
    <div>
        Email: <input name="email" type="email">
    </div>
    <div>
        Password: <input name="password" type="password">
    </div>
    <div>
        Remember Me: <input type="checkbox" name="remember-me"/>
    </div>
    <input type="submit">
    <#if error??>
        <p style="color: red">Bad credentials</p>
    </#if>
</form>

<div>
    <button><a href="/sign_up">Sign Up</a></button>
</div>
</body>
</html>
