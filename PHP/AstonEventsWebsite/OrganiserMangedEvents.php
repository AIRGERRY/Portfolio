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
        <input type="text" placeholder="Events, Music, Sports.." required />
        <input type="submit" value="Search" />
        <?php
        include("LoginConfig.php");
        if(!empty($_SESSION["first_name"]) && $_SESSION["logged_in"] == true) {
            echo '<a href="OrganiserDashboard.php" id="dashbtn">Go back to Dashboard</a>';
        }
        ?>
    </form>
</div>
<div class="jumbotron">
    <h1><?php echo $_SESSION['first_name']."'s" ?> Organised Events</h1>
    <div>
    <select name="filter_drop" id="filter">
        <option value="Category">Category</option>
        <option>ID</option>
        <option>Date</option>
    </select>
    </div>
    <div>
        <input type="button" value="Sort Table" id="sort" name="sortbtn">
    </div>
    <div class="container" id="table">
        <table border="0" cellspacing="10" cellpadding="10" >
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
    </div>
</div>
</div>

</body>
</html>
