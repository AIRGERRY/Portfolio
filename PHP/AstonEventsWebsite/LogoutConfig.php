<?php
/**
 * Created by PhpStorm.
 * User: GERRY
 * Date: 10/04/2018
 * Time: 14:53
 */
    session_start();
    session_destroy();
    header("Location: LoginForm.php");
?>
