<?php
require_once ("php/checkLogged.php");
require_once ("php/errorHandler.php");
checkLogged();
?>
<form method="POST" action="confirmation.php">
    <label>Name</label><input type="text" name="name" required><br>
    <label>Account number</label><input type="number" name="acc_num" minlength="26"
                                        maxlength="26" required><br>
    <label>Amount</label><input type="number" name="amount" required><br>
    <label>Title</label><input type="text" name="title" required><br>
    <input type="submit" value="Send">
</form>
