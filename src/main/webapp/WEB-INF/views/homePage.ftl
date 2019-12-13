<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home page</title>

    <#--    Styles-->
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <#--     Scripts-->
    <script src="/resources/js/jquery-3.4.1.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

<div class="container">
    <!-- Nav -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/homePage">
                <img src="/resources/img/phone_book.png" width="30" height="30" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" href="/homePage">Home</a>
                    <#if admin == true>
                        <a class="nav-link" href="/users">Phone Directory</a>
                        <a class="nav-link" role="button" href="/uploadPage">Upload File</a>
                    </#if>
                    <a class="nav-link" role="button" href="/user/${loggedUserId}">User phone Info</a>
                </div>
                <div class="navbar-right">
                    <a class="" role="button" href="/logout">Logout</a>
                </div>
            </div>
        </nav>

    <!-- Welcome -->
    <div class="row margins-top-bottom justify-content-center">
        <h1 class="text-center"> Welcome
            <#if phoneUserInfo.name?has_content>
                ${phoneUserInfo.name} ${phoneUserInfo.surname}
            </#if>
            <#if admin == true>
            to Administration page</h1>
        <#else>
            to the User Home Page</h1>
        </#if>
    </div>
</div>
</body>
</html>
