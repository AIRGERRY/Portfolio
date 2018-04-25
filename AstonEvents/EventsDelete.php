<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Events Delete Form</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>

    <form class="form-wrapper">
        <?php
        include("LoginConfig.php");
        if(!isset($_SESSION['first_name'])){
            header("Location:LoginForm.php");
        }
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
<div class="container" id="table">
    <table border="0" cellspacing="10" cellpadding="10" >
        <thead>
        <tr>
            <?php
            $field = 'event_date';
            $sort = 'ASC';
            if(isset($_GET['sorting']))
            {
                if($_GET['sorting']=='ASC')
                {
                    $sort='DESC';
                }
                else { $sort='ASC'; }
            }
            if(empty($_GET['field'])) {
                echo "";
            }
            elseif($_GET['field']=='id')
            {
                $field = "id";
            }
            elseif($_GET['field']=='category')
            {
                $field = "category";
            }
            elseif($_GET['field']=='event_date')
            {
                $field="event_date";
            }
            ?>
            <th><a href="OrganiserManagedEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'id';?>"> ID </th>
            <th><a href="OrganiserManagedEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'category';?>"> Category </th>
            <th> Event Name </th>
            <th> Description </th>
            <th> Event Time </a></th>
            <th> Organiser Name</th>
            <th><a href="OrganiserManagedEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'event_date';?>"> Event Date </th>
            <th> Location</th>
            <th> Information </th>
        </tr>
        </thead>
        <tbody>
        <?php
        include('connect.php');
        $result = $db->prepare("SELECT * FROM events WHERE organiser_name = '".$_SESSION['first_name']."' ORDER BY $field $sort");
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
                <td><input type="button" onclick="PopWindow()" id="infobtn" name="infobtn" value="Click For More"></td>
            </tr>
            <?php
        }
        ?>
        </tbody>
    </table>
</div>

<div class="container">
    <div class="jumbotron">
        <div class="container" id="eventsdelete">
                <form action="EventsDelete.php" method="post">
                <h2>Delete Event Form</h2>
                    <h4>Note: Event Organiser can only delete their own organised events!</h4>
                    <div class="form-group">
                    <label for="idnumber">ID Number:</label>
                    <input type="text" class="form-control" id="delete_id" name="delete_id" placeholder="Specify ID number of event.." required>
                    </div>
                    <div class="form-group">
                    <label for="eventname">Event Name:</label>
                    <input type="text" class="form-control" id="delete_name" name="delete_event" placeholder="Type event name.." required>
                    </div>
                    <input type="hidden" name="submitted" value="true" />
                    <button type="submit" id="deletebtn" name="deletebtn">Delete Event</button>
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
