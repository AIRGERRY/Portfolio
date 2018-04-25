<?php
include("connect.php");
include("LoginConfig.php");

$category = $_POST['category'];
$event_name = $_POST['eventname'];
$description = $_POST['description'];
$event_time = $_POST['time'];
$event_date = $_POST['date'];
$location = $_POST['location'];
$get_username = $_SESSION['first_name'];

//Upload Image to Database.
$image = $_FILES['upload_image']['name'];
$tmp_dir = $_FILES['upload_image']['tmp_name'];
$image_size = $_FILES['upload_image']['size'];
$upload_dir = "uploads/";
$imgExt = strtolower(pathinfo($image, PATHINFO_EXTENSION));
$valid_extensions = array('jpeg', 'jpg', 'png');
$image_ = rand(1000, 1000000) . "." . $imgExt;
move_uploaded_file($tmp_dir, $upload_dir . $image_);

try{
    // use the form data to create a insert SQL and  add an account record
    $sth=$db->prepare("INSERT INTO events(category, event_name, description, event_time, organiser_name, event_date, location, image) 
                      VALUES(?,?,?,?,?,?,?,?)");
    $sth->execute(array($category, $event_name, $description, $event_time, $get_username, $event_date, $location, $image_));
    #echo "<br> Well Done, you just added a new event into the Aston Events database! <br><br>";
    header("Location: index.php");
} catch (PDOException $ex){
//this catches the exception when it is thrown
    echo "Sorry, a database error occurred when trying to insert a new event record. Please try again.<br> ";
    echo "Error details:". $ex->getMessage();
}
?>
