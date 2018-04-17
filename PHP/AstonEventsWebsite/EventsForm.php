<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Register Form</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/MainDashboard.php">Aston Events ®</a>
    <a href="http://astonevents.me/MainStudentDashboard.php">Events</a>
    <a href="#contacts">Contacts</a>
    <a href="#about">About</a>
    <form class="form-wrapper">
        <?php
        include("LoginConfig.php");
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Go back to Dashboard</a>';
        }
        ?>
        <a href="LogoutConfig.php" id="logout" name="logoutbtn">
            <?php
            if($_SESSION['logged_in'] == true) {
                echo "Logout";
            } else {
                echo "Sign-in";
            }
            ?>
        </a>
    </form>
</div>
<form method="post" action="EventsAdd.php" enctype="multipart/form-data">
    <div class="container">
        <div class="jumbotron">
            <div class="container" id="form">
                <h2>Event Organiser : Add Event Form</h2>
                <div class="form-group">
                    <label for="category">Category:</label>
                    <select name="category" id="category" required>
                        <option value="Sports">Sports</option>
                        <option value="Culture">Culture</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                    <label for="eventname">Event Name:</label>
                    <input type="text" class="form-control" id="eventname" name="eventname" placeholder="Event name.." required>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <input type="text" class="form-control" id="description" name="description" placeholder="Describe your event.." required>
                </div>
                    <label for="time">Event Time:</label>
                    <input type="text" class="form-control" id="time" name="time" placeholder="Time of event.." required>
                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="date" class="form-control" id="date" name="date" placeholder="Select the date of event.." required>
                <div class="form-group">
                    <label for="location">Location:</label>
                    <input type="text" class="form-control" id="location" name="location" placeholder="Location of your event..">
                </div>
                    <label for="image">Image:</label>
                    <input type="file" id="image" name="image" placeholder="Browse your image..">
                </div>
                    <input type="hidden" name="submitted" value="true" />
                    <input type="submit" name="sumit" id="sign" value="Submit">
            </div>
        </div>
    </div>
</form>
</body>
</html>