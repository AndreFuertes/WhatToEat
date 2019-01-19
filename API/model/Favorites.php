<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 18:24
 */

class Favorites implements JsonSerializable
{

    public $favorite_id, $user_id, $post_id;

    /**
     * Favorites constructor.
     * @param $favorite_id
     * @param $user_id
     * @param $post_id
     */
    public function __construct($favorite_id, $user_id, $post_id)
    {
        $this->favorite_id = $favorite_id;
        $this->user_id = $user_id;
        $this->post_id = $post_id;
    }

    /**
     * @return mixed
     */
    public function getFavoriteId()
    {
        return $this->favorite_id;
    }

    /**
     * @param mixed $favorite_id
     */
    public function setFavoriteId($favorite_id)
    {
        $this->favorite_id = $favorite_id;
    }

    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * @param mixed $user_id
     */
    public function setUserId($user_id)
    {
        $this->user_id = $user_id;
    }

    /**
     * @return mixed
     */
    public function getPostId()
    {
        return $this->post_id;
    }

    /**
     * @param mixed $post_id
     */
    public function setPostId($post_id)
    {
        $this->post_id = $post_id;
    }

    public function jsonSerialize()
    {
      return[
          'favorite_id' => $this->favorite_id,
          'user_id' => $this->user_id,
          'post_id' => $this->post_id
      ];
    }


}