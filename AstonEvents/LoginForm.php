<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Login Form</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <div class="nav">
        <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>
    </div>
    <form method="post" action="LoginConfig.php" enctype="multipart/form-data">
    <div class="container">
        <div class="jumbotron">
            <div class="container" id="login">
                <h2>Events Organiser : Login</h2>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
                    </div>
                    <button type="submit" id="sign" name="loginbtn" class="btn btn-default">Login</button>
                    <button type="button" id="sub" name="registerbtn" class="btn btn-default" onclick="window.location='RegisterForm.php'">
                        Sign-up to become an event organiser</button>
            </div>

        </div>
    </div>


</body>
</html>