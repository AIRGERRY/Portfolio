<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title><?php include("LoginConfig.php"); echo $_SESSION['first_name']."'s' managed events"?></title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>

    <form class="form-wrapper">
        <?php
        if(!isset($_SESSION['first_name'])){
            header("Location:LoginForm.php");
        }
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Go back to Dashboard</a>';
        }
        ?>
    </form>
</div>
<div class="jumbotron">
    <h1><?php echo $_SESSION['first_name']."'s" ?> Organised Events</h1>
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
                    <td><a href="ExtraContent.php?event_id=<?php echo $row['id']; ?>" id="infobtn" name="infobtn">Click for More!</a></td>
                </tr>
                <?php
            }
            ?>
            </tbody>
        </table>
    </div>
</div>
</div>

</body>
</html>
