<?php
include ("LoginConfig.php");
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>MainMenu</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <div class="nav">
        <a class="heading" href="http://astonevents.me/MainDashboard.php">Aston Events ®</a>
        <a href="http://astonevents.me/EventsList.php">Events</a>
        <a href="#contacts">Contacts</a>
        <a href="#about">About</a>

    <form class="form-wrapper" id="dashbtn">
        <?php
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Dashboard</a>';
        } else {
            echo "";
        }
        ?>
        <a href="LogoutConfig.php" id="logout" name="logoutbtn">
            <?php
            if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
                echo "Logout";
            } else {
                echo "Sign-in or Sign-up";
            }
            ?>
        </a>

    </form>
    </div>

    <div id="welcome">
    <h1>Welcome to the Main Dashboard of Aston Events!</h1>
    </div>
    <div class="container">
    <?php
        //an array of 4 images in the direcory images
        $images = array("sports.jpg","culture.jpeg","music.jpg", "photography.jpg");
        $num=count($images);
        //generate a random number
        $randomno = rand()%$num;
        $pic=$images[$randomno];
        ?>
        <img src="assets/<?= $pic?>" alt="<?= $pic?>" width="100%"/>
        <div class="jumbotron">
            <div class="container" id="table">
            <button type="button" id="listfunction" onclick="location.href='http://astonevents.me/EventsList.php';">List All Aston Events</button>
        </div>
        </div>
    </div>
    }
</body>
</html>
