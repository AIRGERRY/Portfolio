<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Update Events</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
        <a class="heading" href="http://astonevents.me/MainDashboard.php">Aston Events ®</a>
        <a href="http://astonevents.me/EventsList.php">Events</a>
        <a href="#ccontacts">Contacts</a>
        <a href="#about">About</a>

    <form class="form-wrapper">
        <input type="text" placeholder="Events, Music, Sports.." required />
        <input type="submit" value="Search" name="searchbtn"/>
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
    <table border="1">
        <thead>
        <tr>
            <th> ID </th>
            <th> Category </th>
            <th> Event Name </th>
            <th> Description </th>
            <th> Event Time </th>
            <th> Organiser Name</th>
            <th> Event Date</th>
            <th> Location</th>
            <!--<th> Picture </th>-->
        </tr>
        </thead>
        <tbody>
        <?php
        include('connect.php');
        $result = $db->prepare("SELECT * FROM events WHERE organiser_name = '".$_SESSION['first_name']."' ORDER BY id DESC");
        $result->execute();
        for($i=0; $row = $result->fetch(); $i++){
            ?>
            <tr class="record">
                <td><?php echo $row['id']; ?></td>
                <td><?php echo $row['category']; ?></td>
                <td><?php echo $row['event_name']; ?></td>
                <td><?php echo $row['description']; ?></td>
                <td><?php echo $row['event_time']; ?></td>
                <td><?php echo $row['organiser_name']; ?></td>
                <td><?php echo $row['event_date']; ?></td>
                <td><?php echo $row['location']; ?></td>
                <!--<td><?php echo $row['picture']; ?></td>-->
            </tr>
            <?php
        }
        ?>
        </tbody>
    </table>
    <div class="jumbotron">
        <div class="container" id="eventsupdate">
                <form action="EventsUpdate.php" method="post">
                <h2>Update Event Form</h2>
                    <h5>Note: Event Organiser can only update their own organised events not others!</h5>
                    <h5>Note: All input fields must be filled in!</h5>
                    <h5>Note: Refresh page to update the table.</h5>
                <label for="id">Update event based on ID Number: </label>
                    <div class="form-group">
                        <input type="text" class="form-control" id="id" name="id" placeholder="Must specify ID number of event.." required>
                    </div>
                        <label for="category">Category:</label>
                        <input type="text" class="form-control" id="category" name="category" placeholder="Update category..">
                    <div class="form-group">
                        <label for="eventname">Event Name:</label>
                        <input type="text" class="form-control" id="update_event" name="update_event" placeholder="Update event name..">
                    </div>
                        <label for="eventname">Description:</label>
                        <input type="text" class="form-control" id="description" name="description" placeholder="Update description..">
                    <div class="form-group">
                        <label for="event_time">Event Time:</label>
                        <input type="text" class="form-control" id="event_time" name="event_time" placeholder="Update event time..">
                    </div>
                        <label for="eventname">Event Date:</label>
                        <input type="date" class="form-control" id="date" name="date" placeholder="Update event date..">
                    <div class="form-group">
                        <label for="location">Location:</label>
                        <input type="text" class="form-control" id="location" name="location" placeholder="Update event location..">
                    </div>
                    <div class="form-group">
                        <label for="picture">Image:</label>
                        <input type="file" id="image" name="image">
                    </div>
                    <button type="submit" id="updatebtn">Update Event</button>

                    <?php
                    include("connect.php");
                    try {
                        $stmt = $db->prepare("UPDATE events SET event_name = :eventname, category = :category,
                                                        description = :description, event_time = :event_time,
                                                        event_date = :event_date, location = :location WHERE id = :id AND organiser_name = '".$_SESSION['first_name']."' ");
                        $stmt->bindParam(':eventname',$_POST['update_event'],PDO::PARAM_STR);
                        $stmt->bindParam(':category',$_POST['category'],PDO::PARAM_STR);
                        $stmt->bindParam(':description',$_POST['description'],PDO::PARAM_STR);
                        $stmt->bindParam(':event_time',$_POST['event_time'],PDO::PARAM_STR);
                        $stmt->bindParam(':event_date',$_POST['date'], PDO::PARAM_STR);
                        $stmt->bindParam(':location',$_POST['location'],PDO::PARAM_STR);

                        $stmt->bindParam(':id',$_POST['id'],PDO::PARAM_INT);
                        $stmt->execute();
                    } catch  (PDOException $ex){
                        //this catches the exception when it is thrown
                        echo "Sorry, a database error occurred when trying to update an existing record. Please try again.<br> ";
                        echo "Error details:". $ex->getMessage();
                    }
                    ?>
                </form>
        </div>
    </div>
</div>



</body>
</html>