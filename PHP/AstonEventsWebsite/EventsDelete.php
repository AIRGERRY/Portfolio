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
    <a href="#ccontacts">Contacts</a>
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

<div class="container">
    <div class="jumbotron">
        <div class="container" id="eventsdelete">
                <form action="EventsDelete.php" method="post">
                <h2>Delete Event Form</h2>
                    <h4>Note: Event Organiser can only delete their own organised events not others!</h4>
                    <div class="form-group">
                    <label for="idnumber">ID Number:</label>
                    <input type="text" class="form-control" id="delete_id" name="delete_id" placeholder="Specify ID number of event.." required>
                    </div>
                    <div class="form-group">
                    <label for="eventname">Event Name:</label>
                    <input type="text" class="form-control" id="delete_name" name="delete_event" placeholder="Type event name.." required>
                    </div>
                    <input type="hidden" name="submitted" value="true" />
                    <button type="submit" id="deletebtn">Delete Event</button>
                </form>
                <?php
                include("connect.php");
                try {
                    $stmt = $db->prepare("DELETE FROM events WHERE event_name = :eventname AND id = :id AND organiser_name = '".$_SESSION['first_name']."' ");
                    $stmt->bindParam(':eventname',$_POST['delete_event'],PDO::PARAM_INT);
                    $stmt->bindParam(':id', $_POST['delete_id'], PDO:: PARAM_INT);
                    $stmt->execute();
                } catch  (PDOException $ex){
                    //this catches the exception when it is thrown
                    echo "Sorry, a database error occurred when trying to remove an existing record. Please try again.<br> ";
                    echo "Error details:". $ex->getMessage();
                }
                ?>
        </div>
    </div>
</div>
</body>
</html>
