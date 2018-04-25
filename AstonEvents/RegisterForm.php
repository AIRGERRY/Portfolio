<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Register Form</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>

    <form class="form-wrapper">
        <a href="http://astonevents.me/LoginForm.php" id="account">Sign-in</a>
    </form>
</div>
    <form method="post" action="RegisterConfig.php" enctype="multipart/form-data">
    <div class="container">
            <div class="jumbotron">
                <div class="container" id="form">
                    <h2>Students : Register Form</h2>
                    <h4>Note: * are important fields and must be filled in!</h4>
                    <div class="form-group">
                    <label for="firstname">First Name: *</label>
                    <input type="text" class="form-control" id="fname" name="firstname" placeholder="Your name.." required>
                    </div>
                    <label for="lastname">Last Name: *</label>
                    <input type="text" class="form-control" id="lname" name="lastname" placeholder="Your last name.." required>
                    <div class="form-group">
                    <label for="email">Email: *</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Your email.." pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                    </div>
                    <label for="password">Password: *</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Your password.." pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                           title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
                    <div class="form-group">
                    <label for="confirmpassword">Confirm Password: *</label>
                    <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="Confirm your password.." required>
                    </div>
                    <label for="pnumber">Mobile Number: *</label>
                    <input type="text" class="form-control" id="number" name="pnumber" placeholder="Your mobile number.." required>
                    <div class="form-group">
                    <label for="degree">Degree Course:</label>
                    <input type="text" class="form-control" id="degree" name="degree" placeholder="Your degree.." required>
                    </div>
                    <input type="hidden" name="submitted" value="true" />
                    <input type="submit" id="sign" value="Submit">
            </div>
            </div>
    </div>
    </form>
</body>
</html>