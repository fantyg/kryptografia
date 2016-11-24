<?php
if (    !isset($_POST['name']) || !isset($_POST['acc_num']) ||
        !isset($_POST['amount']) || !isset($_POST['title']) ||
        !isset($_POST['send_num']) || !isset($_POST['send_name'])) {
    setcookie("error", "Every field is required!");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/make.php");
    return;
}

$name = $_POST['name'];
$acc_num = $_POST['acc_num'];
$amount = $_POST['amount'];
$title = $_POST['title'];
$send_num = $_POST['send_num'];
$send_name = $_POST['send_name'];

require_once ("connection.php");
$db = createConnection();
$query = "INSERT INTO transfers (sen_num, sen_nam, rec_num, rec_nam, title, amount) VALUES "
    . "('$send_num', '$send_name', '$acc_num', '$name', '$title', '$amount')";
sqlQuery($db, $query);
header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/home.php");