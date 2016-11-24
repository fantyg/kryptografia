<?php
require_once ("php/errorHandler.php");
require_once "php/messageHandler.php";
?>

<form method="POST" action="php/login.php">
    <input type="text" name="login" value="">
    <input type="password" name="password" value="">
    <input type="submit" value="Login">
</form>
<a href="register.php">Sign in</a>