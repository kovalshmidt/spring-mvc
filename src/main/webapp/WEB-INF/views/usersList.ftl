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
                    <a class="nav-link active" href="/users">Phone Directory</a>
                    <a class="nav-link" role="button" href="/uploadPage">Upload File</a>
                </#if>
                <a class="nav-link" role="button" href="/user/${loggedUserId}">User phone Info</a>
            </div>
            <div class="navbar-right">
                <a class="" role="button" href="/logout">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Additional buttons -->
    <div class="row margins-top-bottom justify-content-end">
        <div class="col-md-2">
            <div class="align-content-end">
                <label for="selectItems">Users on page</label>
                <select name="numberOfItems" id="selectItems">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div>
        </div>
        <div class="col-md-2">
            <div class="align-content-end">
                <label for="selectItems">Order</label>
                <select name="numberOfItems" id="selectItems">
                    <option value="asc">ASC</option>
                    <option value="desc">DESC</option>
                </select>
            </div>
        </div>
        <div class="col-md-2">
            <div class="float-md-right">
                <a class="btn btn-info btn-sm" role="button" href="/getUsersPdf">Download PDF</a>
            </div>
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
                    <#assign sizePhones = userInfo.getPhoneInfoSet()?size>
                    <#list userInfo.phoneInfoSet as phoneInfo>     <#--  Phone - Company-->
                        <tr>
                        <#if countPhones == 0 >
                            <th scope="row">${count}</th>
                            <td>${userInfo.name} ${userInfo.surname}</td>
                            <#assign count = count + 1>
                        <#else>
                            <th scope="row"></th>
                            <td></td>
                        </#if>
                        <td>${phoneInfo.phoneNumber}</td>
                        <td>${phoneInfo.phoneCompany}</td>
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
