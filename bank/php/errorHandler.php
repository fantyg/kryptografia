<?php
if(isset($_COOKIE['error'])) {
    echo "<div id='error'>" . $_COOKIE['error'] . "</div>";
    setcookie("error", "", time()-3600, "/");
}