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


if (!empty($input->postId) && !empty($input->userId)){


    if($dbManager->checkIfLikeExists($input->postId, $input->userId) === true ){
        echo "true";
        // TODO: remover like
         $dbManager->removeLike($input->postId, $input->userId);

        // echo json_encode();

    }else{
        echo "false";
        // TODO: Registar like
         $dbManager->insertLike($input->postId, $input->userId);
        // echo json_encode($failureData);

    }

    $dbManager->closeConnection();
}
?>