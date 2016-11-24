<?php

function createConnection () {
	$connect = new mysqli ('localhost', 'bankAdmin', '1234', 'bank');
	if ($connect->connect_errno) {
		echo "Błąd bazy danych!\n";
		echo "Błąd: " . $connect->connect_error;
		return false;
	} else {
		$connect->set_charset("utf8");
		return $connect;
	}
}

function sqlQuery ($connection, $query) {
	$result = $connection->query($query);
	if (!$result) {
		echo "Błąd bazy danych!\n";
		echo "Błąd: " . $connection->error;
	}
	return $result;
}

function sqlSelect ($connection, $query) {
	$arr = array();
	$result = $connection->query($query);
	if (!$result) {
		echo "Błąd bazy danych!\n";
		echo "Błąd: " . $connection->error;
		return false;
	} else {
		while ($tab = $result->fetch_assoc()) {
			$arr[] = $tab;
		}
		return $arr;
	}
}
