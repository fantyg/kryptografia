<?php
require_once ("checkLogged.php");
require_once "connection.php";

$login = isset($_POST['login']) ? $_POST['login'] : '';
$password = isset($_POST['password']) ? $_POST['password'] : '';

if ($login === '' || $password === '') {
    setcookie("error", "Login and password cannot be empty!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/login.php");
    exit();
}

$db = createConnection();
$query = "SELECT acc_num, name, password FROM accounts WHERE login='$login'";
$acc = sqlSelect($db, $query);
if (count($acc) == 0) {
    setcookie("error", "Bad login!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/login.php");
    exit();
}

$acc = $acc[0];
$hash = $acc['password'];
if (!password_verify($password, $hash)) {
    setcookie("error", "Bad password!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/login.php");
    exit();
}

setcookie("login", $login, time()+15*60, "/");
setcookie("acc_num", $acc['acc_num'], time()+15*60, "/");
setcookie("name", $acc['name'], time()+15*60, "/");
header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/home.php");
