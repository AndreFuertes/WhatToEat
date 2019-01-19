<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 20:10
 */
include_once "../DBManager.php";

header("Content-Type: application/json");

$dbManager = DBManager::getInstance();

$input = json_decode(file_get_contents('php://input'));

if(!empty($input->username) && !empty($input->email) &&  !empty($input->password)) {

    if($dbManager->searchUser($input->username) == null){

        $users = new users(-1, $input->username, $input->password, $input->email_utilizador, "","" );
        $newUsers = $dbManager->registerUser($users);
        $dbManager->closeConnection();

        echo json_encode($newUsers);

    }else{

        $failureData = new users(-1, "USERNAME JÁ REGISTADO", "", "", "", "");

        echo json_encode($failureData);


    }

}
?>