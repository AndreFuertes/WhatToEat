<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 23/01/2019
 * Time: 11:15
 */
include_once "../DBManager.php";

header("Content-Type: application/json");

$dbManager = DBManager::getInstance();

$input = json_decode(file_get_contents('php://input'));

$posts = $dbManager->getAllPosts();
$dbManager->closeConnection();
echo json_encode($posts);

?>