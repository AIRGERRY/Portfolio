<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>All Events</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>

    <form class="form-wrapper">
        <a href="index.php" id="account">Go Back</a>
    </form>
</div>
    <div class="jumbotron">
        <h1>All Upcoming Events at Aston!</h1>
        <h3>Note: Events can be sorted based on Category, Organiser Name and Event Date!</h3>
        <div class="container" id="table">
            <table id="myTable" border="0">
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
                    elseif($_GET['field']=='event_date')
                    {
                        $field = "event_date";
                    }
                    elseif($_GET['field']=='category')
                    {
                        $field = "category";
                    }
                    elseif($_GET['field']=='organiser_name')
                    {
                        $field="organiser_name";
                    }
                    ?>
                    <th><a href="EventsList.php?sorting=<?php echo $sort;?>&field=<?php echo 'category';?>"> Category </th>
                    <th> Event Name </th>
                    <th> Description </th>
                    <th> Event Time </a></th>
                    <th><a href="EventsList.php?sorting=<?php echo $sort;?>&field=<?php echo 'organiser_name';?>"> Organiser Name</th>
                    <th><a href="EventsList.php?sorting=<?php echo $sort;?>&field=<?php echo 'event_date';?>"> Event Date </th>
                    <th> Location</th>
                    <th> Likes </th>
                    <th> Information </th>
                </tr>
                </thead>
                <tbody>
                <?php
                include("connect.php");
                $sql = ("SELECT * FROM events ORDER BY $field $sort");
                $result = $db->prepare($sql);
                $result->execute();
                for($i=0; $row = $result->fetch(); $i++){
                    ?>
                    <tr class="record">
                        <td><?php echo $row['category'];?></td>
                        <td><?php echo $row['event_name'];?></td>
                        <td><?php echo $row['description'];?></td>
                        <td><?php echo $row['event_time'];?></td>
                        <td><?php echo $row['organiser_name'];?></td>
                        <td><?php echo $row['event_date']; ?></td>
                        <td><?php echo $row['location']; ?></td>
                        <td><?php echo $row['like_ranking'];?>
                            <a href="EventsList.php?like_id=<?php echo $row['id'];?>" id="likebtn" name="like_btn">Like</a></td>
                        <td><a href="ExtraContent.php?event_id=<?php echo $row['id']; ?>" id="infobtn" name="infobtn">Click for More!</a></td>
                    </tr>
                <?php
                }
                ?>
                </tbody>
            </table>
            <?php
            if(isset($_GET['like_id'])) {
                $id = $_GET['like_id'];
                $sql = ("UPDATE events SET like_ranking = like_ranking + 1 WHERE id = '$id'");
                $result = $db->prepare($sql);
                $result->execute();
                header("Location: EventsList.php");
            }
            ?>
        </div>
</body>
</html>
