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
    <script src="/resources/js/popper.js" type="text/javascript"></script>
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

    <!-- Additional buttons -->
    <#if hasPhones == true>
    <div class="row margins-top-bottom justify-content-between">
        <div class="col-md-2">
            <div class="float-md-left">
                <div class="dropdown">
                    <a class="btn btn-warning btn-sm dropdown-toggle" href="#" role="button"
                       id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Edit User
                    </a>
                    <div class="dropdown-menu">
                        <#-- Form add new user-->
                        <div class="centered-form">
                            <form name="userViewModel" class="px-4 py-3" action="/updateUser" method="post">
                                <div class="form-group">
                                    <input name="name" type="text" class="form-control" placeholder="Name"
                                           value="${phoneUserInfo.name}"
                                           required="required">
                                </div>
                                <div class="form-group">
                                    <input name="surname" type="text" class="form-control" placeholder="Surname"
                                           value="${phoneUserInfo.surname}"
                                           required="required">
                                </div>
                                <div class="form-group">
                                    <!-- hidden element phone number-->
                                    <input type="hidden" name="email" class="form-control"
                                           value="${phoneUserInfo.email}"/>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">Update</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Table -->
    <div class="row ">
        <div class="col-md-12">
            <table class="table table-bordered" style="border: 1px">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">PhoneNumber</th>
                    <th scope="col">PhoneCompany</th>
                    <th scope="col">Balance</th>
                </tr>
                </thead>
                <#assign count = 1>
                <#list phoneUserInfo.phoneInfoSet as phoneInfo>
                    <tr>
                        <#if count == 1 >
                            <th scope="row">${count}</th>
                            <td>${phoneUserInfo.name} ${phoneUserInfo.surname}</td>
                            <#assign count = count + 1>
                        <#else>
                            <th scope="row"></th>
                            <td></td>
                        </#if>
                        <td>${phoneInfo.phoneNumber}</td>
                        <td>
                            <div class="dropdown">
                                <a class="btn btn-light dropdown-toggle" href="#" role="button"
                                   id="dropdownMenuLink"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${phoneInfo.phoneCompany}
                                </a>
                                <div class="dropdown-menu">
                                    <#-- Form to change phone company-->
                                    <form name="PhoneInfo" class="px-4 py-3" action="/change_operator" method="post">
                                        <p>
                                            <i>Change of operator has a fee of <b>10$</b></i>
                                        </p>
                                        <#if (phoneInfo.balance < 10)>
                                            <p>
                                                <i style="color: red">You don't have enough founds to perform this
                                                    operation</i>
                                            </p>
                                        </#if>

                                        <#-- divider-->
                                        <div class="dropdown-divider"></div>
                                        <div class="form-group">
                                            <!-- hidden element phone number-->
                                            <input type="hidden" name="phoneNumber" class="form-control"
                                                   value="${phoneInfo.phoneNumber}"/>
                                        </div>
                                        <div class="form-group">
                                            <!-- showed element phone operator-->
                                            <label for="selectOperators">New operator</label>
                                            <select name="phoneCompany" class="form-control" id="selectOperators">
                                                <#list operators as operator>
                                                    <option>${operator}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td>${phoneInfo.balance}$</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
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
