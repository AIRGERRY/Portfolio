<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Organiser Dashboard</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>
    <form class="form-wrapper">
        <a id="account">
            <?php
            include ("LoginConfig.php");
            if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
                print "Welcome, {$_SESSION['first_name']}";
            } else {
                print "Sign-in";
            }
            ?>
        </a>
        <a href="LogoutConfig.php" id="logout" name="logoutbtn">
            <?php
            if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
                echo "Logout";
            }
            ?>
        </a>
    </form>
</div>

<div class="container">
    <h1>Welcome to the Main Organiser's Dashboard <?php echo $_SESSION['first_name'].'!'; ?></h1>
    <div class="jumbotron">
        <div class="container" id="table">
            <input type="button" id="list" value="List <?php echo $_SESSION['first_name']."'s" ?> Organised Events" onclick="location.href='http://astonevents.me/OrganiserManagedEvents.php';"/>
            <input type="button" id="add" value="Add New Event" onclick="location.href='http://astonevents.me/EventsForm.php';"/>
            <input type="button" id="update" value="Update Existing Event" onclick="location.href='http://astonevents.me/EventsUpdate.php';"/>
            <input type="button" id="remove" value="Remove Existing Event" onclick="location.href='http://astonevents.me/EventsDelete.php';"/>
        </div>
    </div>
</div>

</body>
</html>