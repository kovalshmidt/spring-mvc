<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <#--    Styles-->
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <#--     Scripts-->
    <script src="/resources/js/jquery-3.4.1.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>

</head>
<body>
<div class="container">
    <div class="centered-form">
        <form action="/login/process" method="post">
            <h2 class="text-center">Log in</h2>
            <div class="form-group">
                <input name="email" type="email" class="form-control" placeholder="Username" required="required">
            </div>
            <div class="form-group">
                <input name="password" type="password" class="form-control" placeholder="Password" required="required">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Log in</button>
            </div>
            <div class="clearfix">
                <label class="pull-left checkbox-inline"><input name="remember-me" type="checkbox"> Remember me</label>
            </div>
            <#if error??>
                <p style="color: red">Bad credentials</p>
            </#if>
        </form>
        <p class="text-center"><a href="/sign_up">Create an Account</a></p>
    </div>
</div>
</body>
</html>
