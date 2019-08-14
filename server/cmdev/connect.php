<?php

	$host="localhost";
	$username="root";
	$password="";
	$database="cm_workshop";
	
	$con = mysqli_connect($host, $username, $password, $database);
	
		if (mysqli_connect_errno())
		{
			echo "Database Connect Failed : " . mysqli_connect_error();
			exit();
		}

	mysqli_query($con,"SET NAMES utf8");
?>