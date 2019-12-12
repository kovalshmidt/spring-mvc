<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
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
        <form name="user" action="sign_up" method="post">
            <h2 class="text-center">Sign Up</h2>
            <div class="form-group">
                <input name="name" type="text" class="form-control" placeholder="Name" required="required">
            </div>
            <div class="form-group">
                <input name="surname" type="text" class="form-control" placeholder="Surname" required="required">
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

</body>
</html>
