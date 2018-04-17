<?php
/**
 * Created by PhpStorm.
 * User: GERRY
 * Date: 03/04/2018
 * Time: 12:28
 */
include("connect.php");
include("LoginConfig.php");

$category = $_POST['category'];
$event_name = $_POST['eventname'];
$description = $_POST['description'];
$event_time = $_POST['time'];
$event_date = $_POST['date'];
$location = $_POST['location'];
$image = $_FILES['image'];
$get_username = $_SESSION['first_name'];
try{
    // use the form data to create a insert SQL and  add an account record
    $sth=$db->prepare("INSERT INTO events(category, event_name, description, event_time, organiser_name, event_date, location, image) 
                      VALUES(?,?,?,?,?,?,?,?)");
    $sth->execute(array($category, $event_name, $description, $event_time, $get_username, $event_date, $location, $image));
    #echo "<br> Well Done, you just added a new event into the Aston Events database! <br><br>";
    header("Location: MainDashboard.php");
} catch (PDOException $ex){
//this catches the exception when it is thrown
    echo "Sorry, a database error occurred when trying to insert a new event record. Please try again.<br> ";
    echo "Error details:". $ex->getMessage();
}
?>
