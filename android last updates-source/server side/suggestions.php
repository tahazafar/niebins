<?php
include_once('dbConfig.php');
header('Content-type: application/json');
if($_SERVER['REQUEST_METHOD'] == "POST" )
{
	$sql = "SELECT * FROM `bins`";
        $rs = mysqli_query($con, $sql);
        if(mysqli_num_rows($rs)){
           while($r=mysqli_fetch_assoc($rs))
    		{
                   $result = array("lat"=>$r['latitude'], "lng"=>$r["longitude"]);
                }
           $json=array("status"=>1,"info"=>$result);
       }else
       {
    	$json=array("status"=>0,"info"=>'No Data');
       }
}else{
		$json=array("status"=>2,"info"=>"Fail");
	}
	echo json_encode($json);