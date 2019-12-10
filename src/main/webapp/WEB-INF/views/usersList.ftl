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

<#assign count = 1>
<#assign countPhones = 0>

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
                        <a class="nav-link active" href="/users">Phone Directory</a>
                        <a class="nav-link" role="button" href="/uploadPage">Upload File</a>
                    </#if>
                    <a class="nav-link" role="button" href="/user/${loggedUserId}">User phone Info</a>
                    <a class="nav-link justify-content-end" role="button" href="/logout">Logout</a>
                </div>
            </div>
        </nav>
    </div>

    <!-- Additional buttons -->
    <div class="row margins-top-bottom">
        <div class="col-md-4">
            <a class="btn btn-success btn-sm" role="button" href="/getUsersPdf">Download PDF</a>
        </div>
    </div>

    <!-- Table -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered" style="border: 1px">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">PhoneNumber</th>
                    <th scope="col">PhoneCompany</th>
                    <th scope="col">Info</th>
                </tr>
                </thead>
                <#list phoneUserInfos as userInfo>     <#--User-->
                    <#assign sizePhones = userInfo.getPhoneInfo()?size>
                    <#list userInfo.phoneInfo as key, value>     <#--  Phone - Company-->
                        <tr>
                        <#if countPhones == 0 >
                            <th scope="row">${count}</th>
                            <td>${userInfo.fullName}</td>
                            <#assign count = count + 1>
                        <#else>
                            <th scope="row"></th>
                            <td></td>
                        </#if>
                        <td>${key}</td>
                        <td>${value}</td>
                        <#if countPhones == 0>
                            <td rowspan= ${sizePhones}>
                                <a class="btn btn-outline-info" role="button" href="/user/${userInfo.phoneUserId}">Get
                                    info</a>
                            </td>
                        </#if>
                        <#assign countPhones = countPhones + 1>
                    </#list>

                    <#assign countPhones = 0>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>

</body>
</html>
