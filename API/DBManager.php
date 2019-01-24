<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 18:22
 */

include_once "model/Favorites.php";
include_once "model/Likes.php";
include_once "model/Posts.php";
include_once "model/Users.php";

class DBManager
{
    private $servername = "localhost";
    private $username = "twdm";
    private $password = "password";
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

       // password_verify(password,//)
        /* create a prepared statement */
        $stmt = $this->conn->prepare("SELECT * FROM users WHERE username = ? AND password = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ss", $username,$password);

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
        $stmt = $this->conn->prepare("INSERT INTO users ( username, password, email, picture, token) VALUES (?, ?, ?, ?, ?)");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $username = $user->getUsername();
            $password = $user->getPassword();
            $email = $user->getEmail();
           $picture = $user->getPicture();
            $stmt->bind_param("sssss", $username, $password, $email, $picture, $token);
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

    public function getAllPosts(){
        $stmt = $this->conn->prepare("SELECT * FROM posts");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {

            $data = [];
            /* execute query */
            $stmt->execute();

            // Extract result set and loop rows
            $result = $stmt->get_result();

            while($row = $result->fetch_assoc()) {
                $post = new Posts($row['post_id'], $row['title'], $row['description'], $row['image'], $row['user_id'], $row['flag_active'], $row['type']);
                array_push($data, $post);
            }
            $stmt->close();
            return $data;
        }
    }

    public function checkIfLikeExists($postId, $userId){
        $stmt = $this->conn->prepare("SELECT * FROM likes WHERE id_post = ? AND id_user = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll",$postId, $userId);
            /* execute query */
            $stmt->execute();

            $result = $stmt->get_result();
            /* close statement */
            $stmt->close();
            if ($result->fetch_assoc()) {
                return true;
            }
            return false;
        }
    }

    public function insertLike($postId, $userId){
        $stmt = $this->conn->prepare("INSERT INTO likes (post_id, user_id) VALUES (?, ?)");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll", $postId, $userId);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();
        }
    }

    public function removeLike($postId, $userId){
        $stmt = $this->conn->prepare("DELETE FROM likes WHERE post_id = ? AND user_id = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll",$postId, $userId);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();
        }
    }


    public function checkIfFavExists($userId, $postId){
        $stmt = $this->conn->prepare("SELECT * FROM favorites WHERE id_user = ? AND id_post = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll",$userId, $postId);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();
        }
    }

    public function insertFav($userId, $postId){
        $stmt = $this->conn->prepare("INSERT INTO favorites (user_id, post_id) VALUES (?, ?)");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll", $userId, $postId);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();
        }
    }

    public function removeFav($postId, $userId){
        $stmt = $this->conn->prepare("DELETE FROM favorites WHERE user_id = ? AND post_id = ?");
        if (false===$stmt ) {
            die('prepare() failed: ' . htmlspecialchars($this->conn->error));
        } else {
            /* bind parameters for markers */
            $stmt->bind_param("ll",$userId, $postId);
            /* execute query */
            $stmt->execute();
            /* close statement */
            $stmt->close();
        }
    }


    public function closeConnection()
    {
        $this->conn->close();
    }
}