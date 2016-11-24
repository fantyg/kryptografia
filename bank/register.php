<?php
require_once ("php/errorHandler.php");
?>
<form method="post" action="php/register.php">
    <label>Login:</label><input name="login" type="text" required><br>
    <label>Password:</label><input name="password" type="password" required><br>
    <label>Repeat password:</label><input name="rPassword" type="password" required><br>
    <label>Name:</label><input name="name" type="text" required><br>
    <label>Account number:</label><input name="acc_nr" type="text" minlength="26" maxlength="26" required><br>
    <input type="submit" value="Sign in">
</form>
