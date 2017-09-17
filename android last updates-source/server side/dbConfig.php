<?php
		#-----database access credentials (database name and table prefix-[ifany])-----#
		global $con;
		
		$server_name	=	"localhost";		# Database Server IP/URL/Name
		$db_username	=	"id2830844_bins";				# Database User Name-chaounor_user
		$db_password	=	"12345678";					# Database Passwrod-chaoun324
		$databasename	=	"id2830844_bins";			# Database Name-chaounor_db
				
		$con=mysqli_connect($server_name,$db_username,$db_password,$databasename) ;
		if(!$con){
		echo ' Database Connection Error..!
					  Please Review Database access credentials in /app_configurations/db_settings.php';
		
		}
?>