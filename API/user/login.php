<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 20:07
 */
include_once "../DBManager.php";

header("Content-Type: application/json");

$dbManager = DBManager::getInstance();

$input = json_decode(file_get_contents('php://input'));

$users = new users(-1,"","","","","");

if (!empty($input->username) && !empty($input->password)){


    if($dbManager->searchUser($input->username) != null ){

        $users = $dbManager->loginUser($input->username, $input->password);
        $dbManager->closeConnection();

        echo json_encode($users);

    }
    //else{
//
//        $failureData = new users(-1, "","Not found" , "", "","");
//
  //      echo json_encode($failureData);

    //}


}
?>