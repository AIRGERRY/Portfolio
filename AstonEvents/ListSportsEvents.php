<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title> Sports Events </title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>

    <form class="form-wrapper">
        <a href="index.php" id="account">Go Back</a>
    </form>
</div>
<div class="jumbotron" >
    <h1>All Organised Sports Events at Aston!</h1>
    <div class="container" id="table">
        <table id="myTable" border="0">
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
            <th><a href="ListSportsEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'organiser_name';?>"> Organiser Name</th>
            <th><a href="ListSportsEvents.php?sorting=<?php echo $sort;?>&field=<?php echo 'event_date';?>"> Event Date </th>
            <th> Location</th>
            <th> Likes </th>
            <th> Information </th>
            </tr>
            </thead>
            <tbody>
            <?php
            include("connect.php");
            $sql = ("SELECT * FROM events WHERE category = 'Sports' ORDER BY $field $sort");
            $result = $db->prepare($sql);
            $result->execute();
            for($i=0; $row = $result->fetch(); $i++){
                ?>
                <tr class="record">
                    <form action="ExtraContent.php" method="post">
                        <td><?php echo $row['category']; ?></td>
                        <td><?php echo $row['event_name']; ?></td>
                        <td><?php echo $row['description']; ?></td>
                        <td><?php echo $row['event_time']; ?></td>
                        <td><?php echo $row['organiser_name']; ?></td>
                        <td><?php echo $row['event_date']; ?></td>
                        <td><?php echo $row['location']; ?></td>
                        <td><?php echo $row['like_ranking'];?>
                            <a href="ListSportsEvents.php?like_id=<?php echo $row['id'];?>" id="likebtn" name="like_btn">Like</a></td>
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
        header("Location: ListSportsEvents.php");
    }
    ?>
</body>
</html>
