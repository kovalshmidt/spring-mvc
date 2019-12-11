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
                <a class="nav-link" href="/homePage">Home</a>
                <#if admin == true>
                    <a class="nav-link" href="/users">Phone Directory</a>
                    <a class="nav-link" role="button" href="/uploadPage">Upload File</a>
                </#if>
                <a class="nav-link active" role="button" href="/user/${loggedUserId}">User phone Info</a>
            </div>
            <div class="navbar-right">
                <a class="" role="button" href="/logout">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Table -->
    <#if hasPhones == true>
        <div class="row margins-top-bottom">
            <table class="table table-bordered" style="border: 1px">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">PhoneNumber</th>
                    <th scope="col">PhoneCompany</th>
                </tr>
                </thead>
                <#assign count = 1>
                <#list phoneUserInfo.phoneInfo as key, value>
                    <tr>
                        <#if count == 1 >
                            <th scope="row">${count}</th>
                            <td>${phoneUserInfo.name} ${phoneUserInfo.surname}</td>
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
        </div>
    <#else>
        <div class="row margins-top-bottom justify-content-center">
            <p>
                There are no phones listed behind this user
            </p>
        </div>
    </#if>

</div>
</body>
</html>
