<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 18:22
 */

include_once "model/Favorites";
include_once "model/Likes";
include_once "model/Posts";
include_once "model/Users";

class DBManager
{
    private $servername = "localhost";
    private $username = "root";
    private $password = "";
    private $dbname = "whattoeat";

    private $conn;

    public static function getInstance(){

        static $inst = null;
        if($inst === null){
            $inst = new DBManager();
        }
        return $inst;
    }

    private function __construct()
    {
        //Create connection
        $this->conn = new mysqli($this->servername, $this->username, $this->password, $this->dbname);

        $this->conn->set_charset('utf8mb4');
    }

    public function loginUser($username, $password) {
        $passwordHash = password_hash($password, PASSWORD_BCRYPT);
        /* create a prepared statement */
        $stmt = $this->conn->prepare("SELECT * FROM users WHERE username = ? AND password = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ss", $username, $password);

            /* execute query */
            $stmt->execute();

            // Extract result set and loop rows
            $result = $stmt->get_result();
            $data = $result->fetch_assoc();
            $stmt->close();

            return new users($data['user_id'], $data['username'],"", $data['email'], $data['picture'], $data['token']);
        }
    }




    public function registerUser(users $user){
        $passwordCrypt = password_hash($user->getPassword(), PASSWORD_BCRYPT);
        $token = bin2hex(random_bytes(64));
        /* create a prepared statement */
        $stmt = $this->conn->prepare("INSERT INTO users (user_id, username, password, first_name, last_name, birthday_date, email, picture, token, flag_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $name = $user->getUsername();
            $email = $user->getEmail();
            $password = $user->getPassword();
            $stmt->bind_param("ssss", $username, $email, $password, $token);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();

            $user->setToken($token);

            return $user;
        }
    }


    public function searchUser($username) {

        $stmt = $this->conn->prepare("SELECT * FROM users WHERE username = ? ");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("s", $username);

            /* execute query */
            $stmt->execute();

            // Extract result set and loop rows
            $result = $stmt->get_result();
            $data = $result->fetch_assoc();
            $stmt->close();

            return $data['username'];
        }

    }

    public function closeConnection()
    {
        $this->conn->close();
    }
}