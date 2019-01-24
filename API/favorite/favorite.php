<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 23/01/2019
 * Time: 12:35
 */
include_once "../DBManager.php";

header("Content-Type: application/json");

$dbManager = DBManager::getInstance();

$input = json_decode(file_get_contents('php://input'));


if (!empty($input->userId) && !empty($input->postId)){


    if($dbManager->checkIfFavExists($input->userId, $input->postId) === true ){
        // TODO: remover like
        $dbManager->removeFav($input->userId, $input->postId);
        $dbManager->closeConnection();
        // echo json_encode();

    }else{
        // TODO: Registar like
        $dbManager->insertFav($input->postId, $input->userId);
        // echo json_encode($failureData);

    }
}
?>