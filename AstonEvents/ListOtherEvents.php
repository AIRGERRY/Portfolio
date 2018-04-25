<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Other Events</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>
    <form class="form-wrapper">
        <a href="index.php" id="account">Go Back</a>
    </form>
    <form class="form-wrapper">
        <?php
        include("LoginConfig.php");
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Go back to Dashboard</a>';
        }
        ?>
    </form>
</div>
<div class="jumbotron">
    <h1>All Other Organised Events</h1>
    <div class="container" id="table">
        <table border="0" cellspacing="10" cellpadding="10" >
            <thead>
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
            elseif($_GET['field']=='event_date')
            {
                $field = "event_date";
            }
            elseif($_GET['field']=='organiser_name')
            {
                $field="organiser_name";
            }
            ?>
            <th> Category </th>
            <th> Event Name </th>
            <th> Description </th>
            <th> Event Time </a></th>
            <th><a href="ListOtherEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'organiser_name';?>"> Organiser Name</th>
            <th><a href="ListOtherEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'event_date';?>"> Event Date </th>
            <th> Location</th>
            <th> Likes </th>
            <th> Information </th>
            </thead>
            <tbody>
            <?php
            include('connect.php');
            $result = $db->prepare("SELECT * FROM events WHERE category = 'Other' ORDER BY $field $sort");
            $result->execute();
            for($i=0; $row = $result->fetch(); $i++){
                ?>
                <tr class="record">
                    <td><?php echo $row['category']; ?></td>
                    <td><?php echo $row['event_name']; ?></td>
                    <td><?php echo $row['description']; ?></td>
                    <td><?php echo $row['event_time']; ?></td>
                    <td><?php echo $row['organiser_name']; ?></td>
                    <td><?php echo $row['event_date']; ?></td>
                    <td><?php echo $row['location']; ?></td>
                    <td><?php echo $row['like_ranking'];?>
                        <a href="ListOtherEvents.php?like_id=<?php echo $row['id'];?>" id="likebtn" name="like_btn">Like</a></td>
                    <td><a href="ExtraContent.php?event_id=<?php echo $row['id']; ?>" id="infobtn" name="infobtn">Click for More!</a></td>
                </tr>
                <?php
            }
            ?>
            </tbody>
        </table>
    </div>
    <?php
    if(isset($_GET['like_id'])) {
        $id = $_GET['like_id'];
        $sql = ("UPDATE events SET like_ranking = like_ranking + 1 WHERE id = '$id'");
        $result = $db->prepare($sql);
        $result->execute();
        header("Location: ListOtherEvents.php");
    }
    ?>
</body>
</html>
