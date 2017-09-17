<?php
include_once('dbConfig.php');
header('Content-type: application/json');


if($_SERVER['REQUEST_METHOD'] == "POST" )
{
	
	$query = "SELECT * FROM `bins`";
	$rs=mysqli_query($con,$query);
    if(mysqli_num_rows($rs))
    {
    	while($r=mysqli_fetch_assoc($rs))
    		{
    			extract($r);
        		$result[] = array("name" => $r['name'], 
								  "latitude" => $r['latitude'],
								  "longitude" => $r['longitude'],
								  "full" => $r['full']);
    		}
    	$json=array("status"=>1,"info"=>$result);
    }
    else
    {
    	$json=array("status"=>0,"info"=>'No Data');
    }
}else{
		$json=array("status"=>2,"info"=>"Fail");
	}
	echo json_encode($json);




?>