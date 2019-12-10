<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Info</title>

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
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="/homePage">Home</a>
                    <#if admin == true>
                        <a class="nav-link" href="/users">Phone Directory</a>
                        <a class="nav-link" role="button" href="/uploadPage">Upload File</a>
                    </#if>
                    <a class="nav-link active" role="button" href="/user/${phoneUserInfo.phoneUserId}">User phone Info</a>
                    <a class="nav-link justify-content-end" role="button" href="/logout">Logout</a>
                </div>
            </div>
        </nav>
    </div>

    <!-- Table -->
    <div class="row margins-top-bottom">
        <#if hasPhones == true>
            <table class="table table-bordered" style="border: 1px">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">PhoneNumber</th>
                    <th scope="col">PhoneCompany</th>
                </tr>
                <#assign count = 0>
                <#list phoneUserInfo.phoneInfo as key, value>
                    <tr>
                        <#if count == 0 >
                            <th scope="row">${count}</th>
                            <td>${phoneUserInfo.fullName}</td>
                            <#assign count = count + 1>
                        <#else>
                            <th scope="row"></th>
                            <td></td>
                        </#if>
                        <td>${key}</td>
                        <td>${value}</td>
                    </tr>
                </#list>

            </table>
        <#else>
            <p>
                There are no phones listed behind this user
            </p>
        </#if>
    </div>

</div>
</body>
</html>
