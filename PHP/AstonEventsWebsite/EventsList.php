<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../stupidtable.js?dev"></script>
    <title>List of Events</title>
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
        <input type="submit" value="Search" />
        <a href="MainDashboard.php" id="account">Go Back</a>
    </form>
</div>
    <div class="jumbotron" >
        <h1>All Events at Aston!</h1>
        <form action="">
        <div>
        <select name="filter_drop" id="filter">
            <option value="Category">Category</option>
            <option value="Likes">Number of Likes</option>
            <option value="Date">Date</option>
        </select>
        </div>
        <div>
        <input type="submit" value="Sort Table" id="sort" name="sortbtn">
        </div>
        </form>
        <div class="container" id="table">
            <table border="0">
                <thead>
                <tr>
                    <th><a href='?sort=category'> Category </a></th>
                    <th> Event Name </th>
                    <th> Description </th>
                    <th> Event Time </th>
                    <th> Organiser Name</th>
                    <th><a href='?sort=date'<?php $order = "event_date DESC" ;?>>Event Date</a></th>
                    <th> Location</th>
                    <th><a href='?sort=likes'> Likes </a></th>
                    <th> Information </th>
                </tr>
                </thead>
                <tbody>
                <?php
                include("connect.php");
                $order = "event_date ASC";
                $sql = ("SELECT * FROM events ORDER BY $order");
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
                        <td><?php echo $row['like_ranking']." "; ?>
                            <input type="button" id="likebtn" name="likebtn" value="like">
                        </td>
                        <td><input type="button" onclick="PopWindow()" id="infobtn" name="infobtn" value="Click For More"></td>
                        </form>
                    </tr>

                    <?php
                    }
                    ?>
                </tbody>
            </table>
        </div>
    </>
<script>
    function PopWindow()
    {
        window.open('ExtraContent.php','','toolbar=no, status=no, resizable=1, scrollbars=1,' +
            '        menubar=no, location=no, width=500, height=250');
    }
</script>
</body>
</html>
