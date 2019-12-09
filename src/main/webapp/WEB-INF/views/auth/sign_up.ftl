<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <#--    TODO: add bootstrap-->
</head>
<body>
<div class="container">
    <div class="col-2">
        <div class="login-form">
            <form name="phoneUser" action="sign_up" method="post">
                <h2 class="text-center">Sign Up</h2>
                <div class="form-group">
                    <input name="fullName" type="text" class="form-control" placeholder="Full Name" required="required">
                </div>
                <div class="form-group">
                    <input name="email" type="email" class="form-control" placeholder="Email" required="required">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Password" required="required">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Sign up</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
