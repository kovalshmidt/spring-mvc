<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Page</title>

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
                    <a class="nav-link active" role="button" href="/uploadPage">Upload File</a>
                </#if>
                <a class="nav-link" role="button" href="/user/${loggedUserId}">User phone Info</a>
            </div>
            <div class="navbar-right">
                <a class="" role="button" href="/logout">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Upload Form -->
    <div class="row margins-top-bottom">
        <div class="centered-form">
            <form method="POST" enctype="multipart/form-data" action="/uploadFile">
                <div class="input-group mb-3">
                    <div class="custom-file">
                        <input name="file" type="file" class="custom-file-input">
                        <label class="custom-file-label">Choose file</label>
                    </div>
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Upload</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
