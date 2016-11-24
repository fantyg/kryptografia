<?php
require_once ("php/checkLogged.php");
checkLogged();
require_once ("php/connection.php");
if (    !isset($_POST['name']) || !isset($_POST['acc_num']) ||
        !isset($_POST['amount']) || !isset($_POST['title'])     ) {
    setcookie("error", "Every field is required!");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/make.php");
    return;
}

$name = $_POST['name'];
$acc_num = $_POST['acc_num'];
$amount = $_POST['amount'];
$title = $_POST['title'];
$send_num = $_COOKIE['acc_num'];
$send_name = $_COOKIE['name'];


?>
<label>Name: <?php echo $name ?></label><br>
<label>Account number: <?php echo $acc_num ?></label><br>
<label>Amount: <?php echo $amount ?></label><br>
<label>Title: <?php echo $title ?></label><br>

<form method="POST" action="php/send.php">
    <input type="hidden" name="name" value="<?php echo $name ?>">
    <input type="hidden" id="acc_num" name="acc_num" value="<?php echo $acc_num ?>">
    <input type="hidden" name="amount" value="<?php echo $amount ?>">
    <input type="hidden" name="title" value="<?php echo $title ?>">
    <input type="hidden" name="send_num" value="<?php echo $send_num ?>">
    <input type="hidden" name="send_name" value="<?php echo $send_name ?>">
    <input type="submit" value="Send">
</form>

<script type="text/javascript" src="js/replace.js"></script>