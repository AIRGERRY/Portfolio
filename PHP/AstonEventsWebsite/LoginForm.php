<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Login Form</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <div class="nav">
        <a class="heading" href="http://astonevents.me/MainDashboard.php">Aston Events ®</a>
        <a href="http://astonevents.me/EventsList.php">Events</a>
        <a href="#contacts">Contacts</a>
        <a href="#about">About</a>
    </form>
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
                    <div class="checkbox">
                        <label><input type="checkbox" name="remember"> Remember me?</label>
                    </div>
                    <button type="submit" id="sign" name="loginbtn" class="btn btn-default">Login</button>
                    <button type="button" id="sub" name="registerbtn" class="btn btn-default" onclick="window.location='RegisterForm.php'">
                        Sign-up to become an event organiser</button>
            </div>
        </div>
    </div>

</body>
</html>
