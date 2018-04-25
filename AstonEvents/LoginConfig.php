<?php
/**
 * Created by PhpStorm.
 * User: GERRY
 * Date: 07/04/2018
 * Time: 13:01
 */
session_start();
require 'connect.php';
if(isset($_POST['loginbtn'])) {
    $email = !empty($_POST['email']) ? trim($_POST['email']) : null;
    $pwdAttempt = !empty($_POST['pwd']) ? trim($_POST['pwd']) : null;

    $sql = "SELECT email, password, firstname, id FROM accounts WHERE email=:email";
    $stmt = $db->prepare($sql);

    $stmt->bindValue(":email", $email);
    $stmt->execute();

    $retrieve = $stmt->fetch(PDO::FETCH_ASSOC);
    $retrievePwd = $retrieve['password'];
    $retrieveFirst = $retrieve['firstname'];
    $retrieveId = $retrieve['id'];
    $retrieveEmail = $retrieve['email'];

    if ($retrieve === false) {
        $message = "Email does not exist in our system. \\nGo back to sign-up or try again.";
        echo "<script type='text/javascript'>alert('$message');
        window.location.replace('http://astonevents.me/LoginForm.php');</script>";

    } else {
        if (password_verify($pwdAttempt, $retrievePwd)) {
            $_SESSION['first_name'] = $retrieveFirst;
            $_SESSION['logged_in'] = time();
            $_SESSION['user_id'] = $retrieveId;
            $_SESSION['email'] = $retrieveEmail;
            header("Location: OrganiserDashboard.php");
            exit;
        } else {
            $message = "The login combination does not exists. \\nTry again.";
            echo "<script type='text/javascript'>alert('$message');
                    window.location.replace('http://astonevents.me/LoginForm.php');</script>";
        }
    }
}
?>