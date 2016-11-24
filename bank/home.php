<?php
require_once ("php/connection.php");
require_once ("php/checkLogged.php");
checkLogged();

$acc_num = $_COOKIE['acc_num'];

$db = createConnection();
$query = "SELECT sen_num, sen_nam, rec_num, rec_nam, title, amount FROM transfers WHERE sen_num = '$acc_num' "
        . "OR rec_num = '$acc_num'";
$history = sqlSelect($db, $query);
$table = "<table>\n<tr>\n<th>Sender account number</th>"
    . "\n<th>Sender name</th>"
    . "\n<th>Receiver account number</th>"
    . "\n<th>Receiver name</th>"
    . "\n<th>Title</th>"
    . "\n<th>Amount</th>\n</tr>";

foreach ($history as $row) {
    $table .= "\n<tr>";
    foreach ($row as $mem) {
        $table .= "\n<td>$mem</td>";
    }
    $table .= "\n</tr>";
}
$table .= "\n</table>";
?>
<h1>Hi <?php echo $_COOKIE['name'] ?></h1>

<p>Transfers history:</p>
<?php echo $table ?>

<p><a href="make.php">Send transfer</a></p>
<p><a href="logout.php">Logout</a></p>

