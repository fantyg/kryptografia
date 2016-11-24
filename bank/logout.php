<?php
if(isset($_COOKIE['login'])) {
    unset($_COOKIE['login']);
    setcookie("login", "", time() - 3600, "/");
}
if(isset($_COOKIE['acc_num'])) {
    unset($_COOKIE['acc_num']);
    setcookie("acc_num", "", time() - 3600, "/");
}
if(isset($_COOKIE['name'])) {
    unset($_COOKIE['name']);
    setcookie("name", "", time() - 3600, "/");
}
header("Location: http://" . $_SERVER['HTTP_HOST'] . "/bank/login.php");