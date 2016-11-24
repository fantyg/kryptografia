<?php

require_once ("connection.php");

$login = isset ($_POST['login']) ? $_POST['login'] : '';
$password = isset ($_POST['password']) ? $_POST['password'] : '';
$rPassword = isset ($_POST['rPassword']) ? $_POST['rPassword'] : '';
$name = isset ($_POST['name']) ? $_POST['name'] : '';
$acc_nr = isset ($_POST['acc_nr']) ? $_POST['acc_nr'] : '';

if ($login === '' || $password === '' || $rPassword === '' || $name === '' || $acc_nr === '') {
    setcookie("error", "Every field is required!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/register.php");
    exit();
}

if ($password !== $rPassword) {
    setcookie("error", "Passwords don't match!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/register.php");
    exit();
}

$db = createConnection ();
$query = "SELECT login FROM accounts WHERE login = '$login'";
$acc = sqlSelect ($db, $query);
if (count ($acc) > 0) {
    setcookie("error", "User with this username already exists!", time()+15*60, "/");
    header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/register.php");
    exit();

}

$hash = password_hash ($password, PASSWORD_DEFAULT);
$query = "INSERT INTO accounts (login, password, name, acc_num) VALUES ('$login', '$hash', '$name', '$acc_nr')";
$res = sqlQuery ($db, $query);
setcookie("message", "Your account has been created!", time()+15*60, "/");
header("location: http://" . $_SERVER['HTTP_HOST'] . "/bank/login.php");