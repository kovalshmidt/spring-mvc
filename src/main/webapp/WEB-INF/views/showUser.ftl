<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
<h1>User Info</h1>
<#assign count = 0>
<table>
    <tr>
        <th>FullName</th>
        <th>PhoneNumber</th>
        <th>PhoneCompany </th>
    </tr>

    <#list phoneUserInfo.phoneInfo as key, value>
        <tr>
            <#if count = 0 >
                <td>${phoneUserInfo.fullName}</td>
                <#assign count = count + 1>
            <#else>
                <td></td>
            </#if>

            <td>${key}</td>
            <td>${value}</td>
        </tr>
    </#list>


</table>
<br>
<a href="/users">back</a>
</body>
</html>
