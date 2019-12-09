<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
<#--    TODO: add bootstrap-->
</head>
<body>
<div class="container">
    <div class="col-2">
        <div class="login-form">
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
</div>
</body>
</html>
