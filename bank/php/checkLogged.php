<?php
function checkLogged() {
	if(!isset($_COOKIE['login']) || !isset($_COOKIE['acc_num']) || !isset($_COOKIE['name']) ||
        $_COOKIE['login'] === '' || $_COOKIE['acc_num'] === '' || $_COOKIE['name'] === '') {
        echo '<script type="text/javascript">
           window.location = "http://localhost/bank/login.php"
        </script>';
		exit();
	} else {
	    setcookie("login", $_COOKIE['login'], time()+15*60, "/");
        setcookie("acc_num", $_COOKIE['acc_num'], time()+15*60, "/");
        setcookie("name", $_COOKIE['name'], time()+15*60, "/");
    }
}

function goHome() {
    if(isset($_COOKIE['login']) && isset($_COOKIE['acc_num']) && isset($_COOKIE['name'])) {
        echo '<script type="text/javascript">
           window.location = "http://localhost/bank/home.php"
        </script>';
    }
}
