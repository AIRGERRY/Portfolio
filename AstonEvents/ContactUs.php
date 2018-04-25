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
        <form class="form-wrapper">
            <a href="index.php" id="account">Go Back</a>
        </form>
</div>
<form method="post" action="ContactUs.php" enctype="multipart/form-data">
    <div class="container">
        <div class="jumbotron">
            <div class="container" id="login">
                <h2>Contact Us: Form</h2>
                <table>
                    <thead>
                    <tr>
                        <th> Organisers Name </th>
                        <th> Organiser Email </th>
                        <th> Mobile Number </th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    include("connect.php");
                    $sql = ("SELECT firstname, email, mobilenumber FROM accounts");
                    $result = $db->prepare($sql);
                    $result->execute();
                    for($i=0; $row = $result->fetch(); $i++){
                        ?>
                        <tr class="record">
                                <td><?php echo $row['firstname']; ?></td>
                                <td><?php echo $row['email']; ?></td>
                                <td><?php echo $row['mobilenumber']; ?></td>
                        </tr>
                        <?php
                        }
                        ?>
                    </tbody>
                </table>
                    <a href="mailto:someone@gmail.com">
                        <input type="button" id="sub" name="contact" value="Mail">
                    </a>
            </div>
        </div>
    </div>
</body>

</body>
</html>