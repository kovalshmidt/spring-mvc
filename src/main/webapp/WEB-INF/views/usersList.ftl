<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<h1>Phone Directory</h1>

<#assign count = 0>

<table style="border: 1px">
    <tr>
        <th>Fullname</th>
        <th>PhoneNumber</th>
        <th>PhoneCompany</th>
        <th>Info</th>
    </tr>
    <#list phoneUserInfos as userInfo>
        <#list userInfo.phoneInfo as key, value>
            <tr>
            <#if count == 0 >
                <td>${userInfo.fullName}</td>
                <#assign count = count + 1>
            <#else>
                <td></td>
            </#if>
            <td>${key}</td>
            <td>${value}</td>
        </#list>
        <td>
            <button><a href="/user/${userInfo.phoneUserId}">Get info</a></button>
        </td>
        <#assign count = 0>
        </tr>
    </#list>
</table>

<p>
    If you want to download the pdf file of the Phone Directory press:
    <button><a href="/getUsersPdf"> Download</a></button>
</p>
<div>
    <button>
        <a href="/uploadPage">To upload page</a>
    </button>
</div>
<div>
    <button>
        <a href="/logout">Logout</a>
    </button>
</div>


</body>
</html>
