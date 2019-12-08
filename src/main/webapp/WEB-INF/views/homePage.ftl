<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<h1> Welcome ${phoneUserInfo.fullName}
<#if admin == true>
    to Administration page</h1>
<#else>
    to the User Home Page</h1>
</#if>

<#if admin == true>
    <div>
        <p>
            To open a table with Phone Users with ability to download a pdf file with all the Phone Users data.
            Click ->
            <button>
                <a href="/users">Show phone Users</a>
            </button>
        </p>

    </div>

    <div>
        <p>
            To upload a file with Phone Users into the system.
            Click ->
            <button>
                <a href="/uploadPage">Upload phone users</a>
            </button>
        </p>
    </div>

</#if>
<div>
    <p>
        <button>
            <a href="/user/${phoneUserInfo.phoneUserId}">User phone Info</a>
        </button>
        Phone info about the logged user
    </p>
</div>

<div>
    <button>
        <a href="/logout">Logout</a>
    </button>
</div>

</body>
</html>
