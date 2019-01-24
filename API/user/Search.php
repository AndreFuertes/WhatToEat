<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 20:14
 */
include_once "../DBManager.php";

header("Content-Type: application/json; charset=utf-8");

$failureData = ["request-type" => "register", "result" => "failure", "errorType" => "username already registered"];

$dbManager = DBManager::getInstance();

$input = json_decode(file_get_contents('php://input'));

if(!empty($input->username) && !empty($input->email) && !empty($input->password)) {

    if($dbManager->searchUser($input->username)->username == null){

        $users = $dbManager->searchUser($input->username);

        $dbManager->closeConnection();

        echo json_encode($user);

    }else{

        echo json_encode($failureData);

    }

}

?>