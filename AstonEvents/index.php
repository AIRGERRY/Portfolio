<?php
include ("LoginConfig.php");
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Main Dashboard</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <!--Navigation Bar and its components-->
    <div class="nav">
        <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>
        <a href="ContactUs.php">Contact Us</a>
    <form class="form-wrapper" id="dashbtn">
        <?php
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Dashboard</a>';
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
    <!--Autoplay Slideshow and Clickable buttons to display events-->
    <div class="container">
        <img class="mySlides" src="assets/sports.png" alt="Sports" width="100%"/>
        <img class="mySlides" src="assets/culture.png" alt="Culture" width="100%"/>
        <img class="mySlides" src="assets/music.png" alt="Music" width="100%"/>
        <img class="mySlides" src="assets/photography.png" alt="Photography" width="100%"/>
        <div class="jumbotron">
            <div class="container" id="table">
                <button type="button" id="listfunction" name="event_list" onclick="location.href='http://astonevents.me/EventsList.php';">All Upcoming Aston Events</button>
                <button type="button" id="listfunction" name="sports_events" onclick="location.href='http://astonevents.me/ListSportsEvents.php';">All Aston Sports Events</button>
                <button type="button" id="listfunction" name="culture_events" onclick="location.href='http://astonevents.me/ListCultureEvents.php';">All Aston Culture Events</button>
                <button type="button" id="listfunction" name="other_events" onclick="location.href='http://astonevents.me/ListOtherEvents.php';">All Other Events</button>
                <button type="button" id="listfunction" name="most_liked" onclick="location.href='http://astonevents.me/MostLikedEvents.php';">Most Liked Events</button>
            </div>
        </div>
    </div>
    }
    <!--Back-end logic for the autoplay slideshow-->
<script>
    var slideIndex = 0;
    carousel();

    function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > x.length) {slideIndex = 1}
    x[slideIndex-1].style.display = "block";
    setTimeout(carousel, 2000); // Change image every 2 seconds
    }
</script>
</body>
</html>