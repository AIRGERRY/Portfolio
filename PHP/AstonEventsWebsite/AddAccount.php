<?php
include("connect.php");
/**
 * Created by PhpStorm.
 * User: GERRY
 * Date: 01/04/2018
 * Time: 18:13
 */
//If the POST var "register" exists (our submit button), then we can
//assume that the user has submitted the registration form.
if(isset($_POST['submitted'])) {
    //Retrieve the field values from our registration form.
    $email = !empty($_POST['email']) ? trim($_POST['email']) : null;
    $pwd = !empty($_POST['password']) ? trim($_POST['password']) : null;
    $firstname = !empty($_POST['firstname']) ? trim($_POST['firstname']) : null;
    $lastname = !empty($_POST['lastname']) ? trim($_POST['lastname']) : null;
    //TO ADD: Error checking (email characters, password length, etc).
    //Basically, you will need to add your own error checking BEFORE
    //the prepared statement is built and executed.
    //Now, we need to check if the supplied username already exists.
    //Construct the SQL statement and prepare it.
    $sql  = "SELECT COUNT(email) AS num FROM accounts WHERE email=:email";
    $stmt = $db->prepare($sql);
    $stmt->bindValue(':email', $email);
    $stmt->execute();
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    //If the provided email already exists - display error.
    //TO ADD - Your own method of handling this error. For example purposes,
    //I'm just going to kill the script completely, as error handling is outside
    //the scope of this tutorial.
    if($row['num'] > 0) {
        $message = "That email already exists. \\nGo back to try again.";
        echo "<script type='text/javascript'>alert('$message');</script>";
        exit;
    }
}
$pwdConfirm = $_POST['confirmpassword'];
if($pwd != $pwdConfirm) {
    $message = "Password do not match. \\nGo back to try again.";
    echo "<script type='text/javascript'>alert('$message');</script>";
    exit;
} else {
    $password_OK = $pwd;
}
$hash_password = password_hash($password_OK, PASSWORD_BCRYPT, array("cost" => 12));
$pnumber = $_POST['pnumber'];
$degree = $_POST['degree'];

try{
// use the form data to create a insert SQL and  add an account record
$sth=$db->prepare("INSERT INTO accounts(firstname, lastname, email, password, mobilenumber, degree) VALUES(?,?,?,?,?,?)");
$sth->execute(array($firstname, $lastname, $email, $hash_password, $pnumber, $degree));
header("Location: LoginForm.php");
} catch (PDOException $ex){
//this catches the exception when it is thrown
echo "Sorry, a database error occurred when trying to insert a record. Please try again.<br> ";
echo "Error details:". $ex->getMessage();
}
?>
