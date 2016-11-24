<?php
if(isset($_COOKIE['message'])) {
    echo "<div id='message'>" . $_COOKIE['message'] . "</div>";
    setcookie("message", "", time()-3600, "/");
}