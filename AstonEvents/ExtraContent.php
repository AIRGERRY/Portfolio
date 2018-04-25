<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Extra Information</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<div class="nav">
    <a class="heading" href="http://astonevents.me/index.php">Aston Events ®</a>
    <a href="ContactUs.php">Contact Us</a>

    <form class="form-wrapper">
        <a href="index.php" id="account">Go Back</a>
    </form>
</div>
<form method="post" action="ExtraContent.php" enctype="multipart/form-data">
    <div class="container">
        <div class="jumbotron" id="image_container">
            <?php
            include("connect.php");
                $sql = ("SELECT * FROM events WHERE id =" . $_GET['event_id']);
                $result = $db->prepare($sql);
                $result->execute();
                if($result->rowCount() > 0) {
            while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
                extract($row);
            ?>
            <div class="row">
                <div class="column">
                    <img name="image" width="100%" src="uploads/<?php echo $row['image']; ?>">
                </div>
                <div class="row">
                    <div class="column">
                        <table>
                            <thead>
                            <tr>
                                <th> Full Event Information</th>
                            </tr>
                            </thead>
                            <tbody>
                            <?php
                            $sql = ("SELECT firstname, email, mobilenumber FROM accounts WHERE id =" . $_GET['event_id']);
                            $results = $db->prepare($sql);
                            $results->execute();
                                for ($i = 0; $row_acc = $results->fetch(); $i++) {
                                    ?>
                                    <tr class="records">
                                        <td>
                                            <a style="color: red"> Organiser
                                                Name: </a><?php echo $row['organiser_name']; ?><br>
                                            <a style="color: red"> Organiser Contact
                                                Number: </a><?php echo $row_acc['mobilenumber']; ?> <br>
                                            <a style="color: red"> Organiser Email
                                                Address: </a><?php echo $row_acc['email']; ?> <br>
                                            <a style="color: red"> Event Name: </a><?php echo $row['event_name']; ?>
                                            <br>
                                            <a style="color: red"> Event
                                                Category: </a><?php echo $row['category']; ?><br>
                                            <a style="color: red"> Event Date: </a><?php echo $row['event_date']; ?>
                                            <br>
                                            <a style="color: red"> Starts at: </a><?php echo $row['event_time']; ?>
                                            <br>
                                            <a style="color: red"> Description: </a><?php echo $row['description']; ?>
                                            <br>
                                        </td>
                                    </tr>
                                    <?php
                            }
                            ?>
                            </tbody>
                        </table>
                    </div>
                </div>
                <?php
                }}
                ?>
        </div>
    </div>
</form>
</body>
</html>